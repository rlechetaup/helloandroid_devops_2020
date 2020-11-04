package com.up.helloup.cidades

import android.util.Log
import com.up.helloup.utils.HttpHelper
import com.up.helloup.utils.extensions.fromJson

// Web Service dos carros
class CidadeService {

    // isso faz com que os métodos aqui dentro fiquem estáticos
    companion object {

        fun getCidades(): List<Cidade> {
            try {
                val url = "https://www.mocky.io/v2/5db35e0a300000500057b628"
                Log.d("up","get $url")
                val json = HttpHelper.get(url)
                Log.d("up","json: $json")
                val cidades = fromJson<List<Cidade>>(json)
                Log.d("up","cidades: $cidades")
                Log.d("up","pontos: $cidades")
                return cidades
            }catch (error: Exception) {
                Log.e("up","Error ${error.message}", error)
            }
            return emptyList()
        }
    }


}