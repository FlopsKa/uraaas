package de.pondati.uraaas

import org.scalatra._
import scalate.ScalateSupport

case class Message(message: String, from: String)

class UraaasServlet extends UraaasStack with ScalateSupport {

  get("/") {
    contentType="text/html"

    ssp("/index", "layout" -> "WEB-INF/layouts/spec.ssp")
  }

  get("/awesome") {
    contentType="text/html"

    val out = Message("You're awesome.", "")
    ssp("/spec", "message" -> out.message, "from" -> "")
  }
  get("/awesome/:from") {
    contentType="text/html"

    val out = Message("You're awesome.", params("from"))
    ssp("/spec", "message" -> out.message, "from" -> ("- " + out.from))
  }
  get("/awesome/:name/:from") {
    contentType="text/html"

    val out = Message("Hey " + params("name") + ", you're awesome.", params("from"))
    ssp("/spec", "message" -> out.message, "from" -> ("- " + out.from))
  }
  get("/faith") {
    contentType="text/html"

    val out = Message("You just revived my faith in humanity.", "")
    ssp("/spec", "message" -> out.message, "from" -> "")
  }
  get("/faith/:from") {
    contentType="text/html"

    val out = Message("You just revived my faith in humanity.", params("from"))
    ssp("/spec", "message" -> out.message, "from" -> ("- " + out.from))
  }
  get("/yoda") {
    contentType="text/html"

    val out = Message("You're my personal yoda.", "")
    ssp("/spec", "message" -> out.message, "from" -> "")
  }
  get("/yoda/:from") {
    contentType="text/html"

    val out = Message("You're my personal yoda.", {params("from")})
    ssp("/spec", "message" -> out.message, "from" -> ("- " + out.from))
  }
  get("/amiawesome") {
    contentType="text/html"

    val out = Message("Hell yeah!", "")
    ssp("/spec", "message" -> out.message, "from" -> "")
  }
  
}
