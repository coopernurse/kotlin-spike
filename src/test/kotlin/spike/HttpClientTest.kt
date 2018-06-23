package spike

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.kittinunf.fuel.httpGet
import org.junit.Test

data class HttpBinResponse(
        val args: Map<String,String>,
        val headers: Map<String,String>,
        val url: String
)

class HttpClientTest {

    @Test
    fun testGetSync() {
        val (request, response, result) = "http://httpbin.org/get".httpGet().responseString()
        println(result.get())

        val mapper = ObjectMapper().registerKotlinModule()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val httpBinResp = mapper.readValue<HttpBinResponse>(result.get())
        println(httpBinResp)
    }

}
