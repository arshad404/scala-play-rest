package models

import play.api.libs.json.{Json, OWrites, Reads}

case class User (
                id: Int = 1,
                name: String = "CodePiper",
                age: Int = 10
                )

object User {
  implicit val userImplicitReads: Reads[User] = Json.reads[User]
  implicit val userImplicitWrites: OWrites[User] = Json.writes[User]
}

