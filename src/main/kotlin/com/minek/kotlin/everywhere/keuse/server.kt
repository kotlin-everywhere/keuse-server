package com.minek.kotlin.everywhere.keuse

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.minek.kotlin.everywhere.kelibs.result.Err
import com.minek.kotlin.everywhere.kelibs.result.Ok
import org.eclipse.jetty.http.HttpStatus
import org.eclipse.jetty.server.NetworkConnector
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.FilterHolder
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import java.util.*
import javax.servlet.DispatcherType
import javax.servlet.Filter
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CrateServlet(private val crate: Crate) : HttpServlet() {
    private val gson = GsonBuilder().create()

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val box = crate.findBox(req.requestURI.substring(1))
        if (box == null) {
            resp.sendError(HttpStatus.NOT_FOUND_404)
            return
        }


        val output = box.handle(JsonParser().parse(req.inputStream.reader()))
        when (output) {
            is Ok -> {
                resp.contentType = "application/json"
                gson.toJson(output.value, resp.writer)
            }
            is Err -> resp.sendError(HttpStatus.UNPROCESSABLE_ENTITY_422, output.error)
        }
    }
}

fun Crate.runServer(port: Int = 0, filters: List<Filter> = listOf(), block: (port: Int, join: () -> Unit) -> Unit = { _, join -> join() }) {
    val server = Server(port)
    val handler = ServletContextHandler()
    server.handler = handler
    handler.contextPath = "/"
    handler.addServlet(ServletHolder(CrateServlet(this)), "/*")
    filters.forEach { handler.addFilter(FilterHolder(it), "/*", EnumSet.of(DispatcherType.REQUEST)) }
    server.start()

    val localPort = server.connectors.map { it as? NetworkConnector }.filterNotNull().first().localPort
    block(localPort, { server.join() })
    server.stop()
}