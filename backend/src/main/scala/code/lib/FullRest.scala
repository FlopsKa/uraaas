package code
package lib

import model._

import net.liftweb._
import common.Full
import http._
import rest._
import util._
import Helpers._
import json._

/**
 * A full REST example
 */
object FullRest extends RestHelper {
  case class Message(text: String, from: String, to: String) {
    def toXml = <message text={text} from={from} to={to} />
    def toJson = Extraction.decompose(this)
  }

  // Serve /api/item and friends
  serve {
    case "test" :: _ JsonGet _ => JString("Bla") : JValue
  }
}
