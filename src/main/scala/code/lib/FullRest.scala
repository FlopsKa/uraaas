package code
package lib

// import model._

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
  case class Message(text: String, from: String) {
    def toXml = <message text={text} from={from} />
    def toJson = Extraction.decompose(this)
  }

  for {
      session <- S.session
  } session.requestHtmlProperties.set(session.requestHtmlProperties.is.setDocType(() => Full("<!DOCTYPE moose>"))) 

  // Serve /api/item and friends
  serve {

    // case "xml" :: _ XmlGet _ => <b>Test</b>

    case "amiawesome" :: _ JsonGet _ => Message("Hell Yeah!", "").toJson : JValue

    case "awesome" :: Nil JsonGet _ => Message("You're awesome.", "").toJson : JValue
    case "awesome" :: from :: Nil JsonGet _ => Message("You're awesome.", from).toJson : JValue
    case "awesome" :: name :: from :: _ JsonGet _ => Message("Hey " + name + ", you're awesome.", from).toJson : JValue

    case "faith" :: Nil JsonGet _ => Message("You just revived my faith in humanity.", "").toJson : JValue
    case "faith" :: from :: _ JsonGet _ => Message("You just revived my faith in humanity.", from).toJson : JValue
  
    case "yoda" :: Nil JsonGet _ => Message("You're my personal yoda.", "").toJson : JValue
    case "yoda" :: from :: _ JsonGet _ => Message("You're my personal yoda.", from).toJson : JValue
  }
}
