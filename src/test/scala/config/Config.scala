package config

object Config {
  val base_url = "http://computer-database.gatling.io"

  //TODO Insert the api oauth2 url
  val token_url = "Insert the api oauth2 url"

  val users = Integer.getInteger("users", 10).toInt
  val rampUp = Integer.getInteger("rampup", 1).toInt
  val throughput = Integer.getInteger("throughput", 100).toInt
}