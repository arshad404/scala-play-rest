package controllers

import javax.inject._
import play.api._
import play.api.libs.json.{JsError, JsValue, Json}
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def user = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(models.User())).as("application/json")
  }

  def postUser = Action(parse.json) { implicit request: Request[JsValue] =>
    val reqBody = request.body.validate[models.User]

    reqBody.fold (
      errors => {
        BadRequest(Json.obj("status" -> "error", "error" -> JsError.toJson(errors)))
      },
      user => {
        Ok(Json.toJson(user)).as("application/json")
      }
    )
  }
}
