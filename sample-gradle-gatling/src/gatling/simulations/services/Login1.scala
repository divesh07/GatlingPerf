package services

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Login1 extends Simulation{
  //Protocol definition
  val httpProtocol = http.baseUrl("http://local:8088")

  //Header definition

  val headers_1 = Map(
    "Accept" -> "application/json, text/plain, */*",
    "Accept-Encoding" -> "gzip, deflate",
    "Accept-Language" -> "en-US",
    "Origin" -> "http://local:8088",
    "X-Callosum-Call-Id" -> "-1",
    "X-Callosum-Client-Type" -> "0",
    "X-Callosum-Ignore-Falcon-Cache" -> "false",
    "X-Callosum-Ignore-Mem-Cache" -> "false",
    "X-Callosum-Incident-Id" -> "null",
    "X-Callosum-trace-Id" -> "7dcf56bd-8f03-4c46-8041-1517e3a576bf",
    "X-Requested-By" -> "ThoughtSpot")

  //Scenario definition
  val scn = scenario(scenarioName = "adminLogin")
    .exec(http("request_1")
      .post(("/callosum/v1/session/login"))
        .headers(headers_1)
        .formParam("username", "tsadmin")
        .formParam("password", "admin")
        .formParam("rememberme", "true"))

  //Simulation definition
  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
