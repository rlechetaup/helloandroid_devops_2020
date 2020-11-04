package com.up.helloup.cidades

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.up.helloup.R
import com.up.helloup.utils.extensions.loadUrl
import kotlinx.android.synthetic.main.activity_cidade.*

class CidadeActivity : AppCompatActivity() {

    val cidade by lazy {
        // Quando suar a variável carro vai ler do parâmetro
        intent.getSerializableExtra("cidade") as Cidade
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cidade)

        img.loadUrl(cidade.urlFoto)

        supportActionBar?.title = cidade.nome
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_cidade, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

}
