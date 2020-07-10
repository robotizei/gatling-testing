package config

object Config {
  val base_url = "http://computer-database.gatling.io"
  val token_url = "https://api.qa.bancobari.com.br/auth/connect/token"

  val users = Integer.getInteger("users", 10).toInt
  val rampUp = Integer.getInteger("rampup", 1).toInt
  val throughput = Integer.getInteger("throughput", 100).toInt
}