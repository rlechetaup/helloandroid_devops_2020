package com.up.helloup.utils

import android.util.Log
import okhttp3.*
import java.io.IOException

object HttpHelper {
    private val TAG = "http"
    private val LOG_ON = true
    var client = OkHttpClient()
    // GET
    fun get(url: String): String {
        log("HttpHelper.get: $url")
        val request = Request.Builder().url(url).get().build()
        return getJson(request)
    }

    // Lê a resposta do servidor no formato JSON
    private fun getJson(request: Request): String {
        val response = client.newCall(request).execute()
        val responseBody = response.body
        if (responseBody != null) {
            val json = responseBody.string()
            log("	 << : $json")
            return json
        }
        throw IOException("Erro ao fazer a requisição")
    }

    private fun log(s: String) {
        if (LOG_ON) {
            Log.d(TAG, s)
        }
    }
}