package com.up.helloup.cidades


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.livroandroid.cidades.cidades.CidadeAdapter
import com.up.helloup.R
import kotlinx.android.synthetic.main.activity_cidades.*
import kotlinx.android.synthetic.main.include_progress.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class CidadesActivity : AppCompatActivity() {

    var cidades: List<Cidade>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cidades)

        setTitle("Cidades")

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()

        // Swipe to Refresh
        swipeToRefresh.setOnRefreshListener {
            taskCidades()
        }
        swipeToRefresh.setColorSchemeResources(
            R.color.refresh_progress_1,
            R.color.refresh_progress_2,
            R.color.refresh_progress_3
        )
    }

    override fun onResume() {
        super.onResume()

        if(cidades == null) {
            taskCidades()
        }
    }

    private fun taskCidades() {
        progress.visibility = View.VISIBLE
        doAsync {
            cidades = CidadeService.getCidades()

            uiThread {
                cidades?.let {
                    recyclerView.adapter = CidadeAdapter(it) { c -> onClickCidade(c) }
                }

                swipeToRefresh.isRefreshing = false
                progress.visibility = View.GONE
            }
        }
    }

    private fun onClickCidade(c: Cidade) {
        toast("Cidade ${c.nome}")

        startActivity<CidadeActivity>("cidade" to c)
    }
}
