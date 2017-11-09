package com.minek.kotlin.everywhere.keuse

import com.minek.kotlin.everywehre.keuson.encode.encode
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

open class CrateServlet(private val crate: Crate) : HttpServlet() {
    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val subUri = req.requestURI.substring(req.contextPath.length)
        val box = crate.findBox(if (subUri.startsWith("/")) subUri.substring(1) else subUri)
        if (box == null) {
            resp.sendError(HttpStatus.NOT_FOUND_404)
            return
        }

        val input = req.inputStream.reader().readText()

        val output = box.handle(HttpEnvironment(req.getHeader("host") ?: ""), input)
        when (output) {
            is Ok -> {
                resp.contentType = "application/json"
                resp.writer.write(encode(output.value))
            }
            is Err -> resp.sendError(HttpStatus.UNPROCESSABLE_ENTITY_422, output.error)
        }
    }
}

fun Crate.runServer(port: Int = 0, contextPath: String = "/", filters: List<Filter> = listOf(), block: (port: Int, join: () -> Unit) -> Unit = { _, join -> join() }) {
    val server = Server(port)
    val handler = ServletContextHandler()
    server.handler = handler
    handler.contextPath = contextPath
    handler.addServlet(ServletHolder(CrateServlet(this)), "/*")
    filters.forEach { handler.addFilter(FilterHolder(it), "/*", EnumSet.of(DispatcherType.REQUEST)) }
    server.start()

    val localPort = server.connectors.map { it as? NetworkConnector }.filterNotNull().first().localPort
    block(localPort, { server.join() })
    server.stop()
}