package de.pondati.uraaas

import org.scalatra._
import scalate.ScalateSupport
import org.slf4j.{Logger, LoggerFactory}
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

case class Message(message: String, from: String) {
  def toJson: String = "{\"message\":\""+message+"\",\"from\":\""+from+"\"}" 
  def toPlainText: String = {
    if(from.length == 0) message
    else message + "- " + from
  }
}

class UraaasServlet extends UraaasStack with ScalateSupport with JacksonJsonSupport {
  
  val logger =  LoggerFactory.getLogger(getClass)

  protected implicit val jsonFormats: Formats = DefaultFormats

  private def generateOutput(message: Message, header: String):String = {
    if(header.contains("application/json")) {
      contentType="application/json"
      message.toJson
    } else if(header.contains("text/plain")) {
      contentType="text/plain"
      message.toPlainText
    } else {
      contentType="text/html"
      ssp("/spec", "message" -> message.message, "from" -> "")
    }
  }

  get("/") {
    contentType="text/html"
    ssp("/index", "layout" -> "WEB-INF/layouts/spec.ssp")
  }

  get("/awesome") {
    val outMessage = Message("You're awesome.", "")
    generateOutput(outMessage, request.getHeader("Accept"))
  }

  get("/awesome/:from") {
    val outMessage = Message("You're awesome.", params("from"))
    generateOutput(outMessage, request.getHeader("Accept"))
  }

  get("/awesome/:name/:from") {
    val outMessage = Message("Hey " + params("name") + ", you're awesome.", params("from"))
    generateOutput(outMessage, request.getHeader("Accept"))
  }

  get("/faith") {
    val outMessage = Message("You just revived my faith in humanity.", "")
    generateOutput(outMessage, request.getHeader("Accept"))
  }

  get("/faith/:from") {
    val outMessage = Message("You just revived my faith in humanity.", params("from"))
    generateOutput(outMessage, request.getHeader("Accept"))
  }

  get("/yoda") {
    val outMessage = Message("You're my personal yoda.", "")
    generateOutput(outMessage, request.getHeader("Accept"))
  }

  get("/yoda/:from") {
    val outMessage = Message("You're my personal yoda.", params("from"))
    generateOutput(outMessage, request.getHeader("Accept"))
  }

  get("/amiawesome") {
    val outMessage = Message("Hell yeah!", "")
    generateOutput(outMessage, request.getHeader("Accept"))
  }
}
