package com.example.catnews.mock

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

class MockInterceptor(val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {

        val request = chain.request()

        val jsonFile = getFile(request.url.toString())

        // Read mock data from a JSON file
        val json = readMockDataFromFile(jsonFile)

        // Create the response using the mock data
        return Response.Builder()
            .request(request)
            .code(200)
            .message(json)
            .protocol(Protocol.HTTP_1_1)
            .addHeader("Content-Type", "application/json")
            .body(json.toResponseBody("application/json".toMediaTypeOrNull()))
            .build()
    }

    private fun readMockDataFromFile(fileName: String): String {
        return try {
            context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
        } catch (e: Exception) {
            ""
        }
    }

    private fun getFile(requestUrl: String): String {
        return if (requestUrl.contains("/news-list"))
            "news.json"
        else "sample_story.json"
    }
}