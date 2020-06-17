import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration._

class LoadtestScript extends Simulation {

  val httpConf = http
    .baseUrl("http://computer-database.gatling.io")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val computerDatabaseScenario = scenario("Filter")
    .exec(http("Filter computers")
      .get("/computers")
      .formParam("f", "Macbook"))
    .pause(200 milliseconds, 1000 milliseconds)
    .exec(http("Look at a Macbook")
      .get("/computers/473"))

  setUp(
    computerDatabaseScenario.inject(
      constantUsersPerSec(30) during (10 seconds)
    ).protocols(httpConf)
  )
}