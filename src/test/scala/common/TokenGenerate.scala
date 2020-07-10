package common

import java.util

import com.fasterxml.jackson.databind.ObjectMapper
import config.Config
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils

class TokenGenerate {

  def getToken(): String = {
    val url = Config.token_url

    var result = ""
    val post = new HttpPost(url)
    // add request parameters or form parameters
    val urlParameters = new util.ArrayList[BasicNameValuePair]
    urlParameters.add(
      new BasicNameValuePair("grant_type", "client_credentials")
    )
    urlParameters.add(new BasicNameValuePair("client_id", "onboarding"))
    urlParameters.add(new BasicNameValuePair("client_secret", "7sjJhLEyKIIz"))
    post.setEntity(new UrlEncodedFormEntity(urlParameters))

    val httpClient = HttpClients.createDefault
    val response = httpClient.execute(post)

    result = EntityUtils.toString(response.getEntity)
    val mapper = new ObjectMapper
    val actualObj = mapper.readTree(result)
    //    print(actualObj)
    val token1 = actualObj.findValue("access_token").asText()

    if (httpClient != null) httpClient.close()
    if (response != null) response.close()

    token1
  }
}
