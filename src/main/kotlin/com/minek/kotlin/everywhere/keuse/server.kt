package com.minek.kotlin.everywhere.keuse

import com.github.kotlin.everywhere.json.decode.Err
import com.github.kotlin.everywhere.json.decode.Ok
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.minek.kotlin.everywhere.keuse.Crate
import org.eclipse.jetty.http.HttpStatus
import org.eclipse.jetty.server.NetworkConnector
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletHandler
import org.eclipse.jetty.servlet.ServletHolder
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
                gson.toJson(output.data, resp.writer)
            }
            is Err -> resp.sendError(HttpStatus.UNPROCESSABLE_ENTITY_422, output.error)
        }
    }
}

fun Crate.runServer(port: Int = 0, block: (port: Int, join: () -> Unit) -> Unit = { _, join -> join() }) {
    val server = Server(port)
    val handler = ServletHandler()
    server.handler = handler
    handler.addServletWithMapping(ServletHolder(CrateServlet(this)), "/*")
    server.start()

    val localPort = server.connectors.map { it as? NetworkConnector }.filterNotNull().first().localPort
    block(localPort, { server.join() })
    server.stop()
}