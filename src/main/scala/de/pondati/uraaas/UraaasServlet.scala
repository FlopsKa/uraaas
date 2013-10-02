package de.pondati.uraaas

import org.scalatra._
import scalate.ScalateSupport
import org.slf4j.{Logger, LoggerFactory}
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

case class Message(message: String, from: String)

class UraaasServlet extends UraaasStack with ScalateSupport with JacksonJsonSupport {
  
  protected implicit val jsonFormats: Formats = DefaultFormats

  val logger =  LoggerFactory.getLogger(getClass)

  get("/") {
    contentType="text/html"
    ssp("/index", "layout" -> "WEB-INF/layouts/spec.ssp")
  }

  get("/awesome") {
    val outMessage = Message("You're awesome.", "")

    if(request.getHeader("Accept").contains("application/json")) {
      contentType="application/json"
      outMessage
    } else {
      contentType="text/html"
      ssp("/spec", "message" -> outMessage.message, "from" -> "")
    }
  }

  get("/awesome/:from") {
    val outMessage = Message("You're awesome.", params("from"))

    if(request.getHeader("Accept").contains("application/json")) {
      contentType="application/json"
      outMessage
    } else {
      contentType="text/html"
      ssp("/spec", "message" -> outMessage.message, "from" -> ("- " + outMessage.from))
    }
  }

  get("/awesome/:name/:from") {
    val outMessage = Message("Hey " + params("name") + ", you're awesome.", params("from"))

    if(request.getHeader("Accept").contains("application/json")) {
      contentType="application/json"
      outMessage
    } else {
      contentType="text/html"
      ssp("/spec", "message" -> outMessage.message, "from" -> ("- " + outMessage.from))
    }
  }

  get("/faith") {
    val outMessage = Message("You just revived my faith in humanity.", "")

    if(request.getHeader("Accept").contains("application/json")) {
      contentType="application/json"
      outMessage
    } else {
      contentType="text/html"
      ssp("/spec", "message" -> outMessage.message, "from" -> "")
    }
  }

  get("/faith/:from") {
    val outMessage = Message("You just revived my faith in humanity.", params("from"))

    if(request.getHeader("Accept").contains("application/json")) {
      contentType="application/json"
      outMessage
    } else {
      contentType="text/html"
      ssp("/spec", "message" -> outMessage.message, "from" -> ("- " + outMessage.from))
    }
  }

  get("/yoda") {
    val outMessage = Message("You're my personal yoda.", "")

    if(request.getHeader("Accept").contains("application/json")) {
      contentType="application/json"
      outMessage
    } else {
      contentType="text/html"
      ssp("/spec", "message" -> outMessage.message, "from" -> "")
    }
  }

  get("/yoda/:from") {
    val outMessage = Message("You're my personal yoda.", "")

    if(request.getHeader("Accept").contains("application/json")) {
      contentType="application/json"
      outMessage
    } else {
      contentType="text/html"
      ssp("/spec", "message" -> outMessage.message, "from" -> ("- " + outMessage.from))
    }
  }

  get("/amiawesome") {
    val outMessage = Message("Hell yeah!", "")
    if(request.getHeader("Accept").contains("application/json")) {
      contentType="application/json"
      outMessage
    } else {
      contentType="text/html"
      ssp("/spec", "message" -> outMessage.message, "from" -> "")
    }
  }
}
