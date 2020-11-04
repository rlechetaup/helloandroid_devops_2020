package br.com.livroandroid.cidades.cidades

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.up.helloup.R
import com.up.helloup.cidades.Cidade
import com.up.helloup.utils.extensions.loadUrl
import kotlinx.android.synthetic.main.adapter_cidade.view.*

// Define o construtor que recebe (cidades,onClick)
class CidadeAdapter(
    val cidades: List<Cidade>,
    val onClick: (Cidade) -> Unit
) :
    RecyclerView.Adapter<CidadeAdapter.CidadesViewHolder>() {
    // Retorna a quantidade de cidades na lista
    override fun getItemCount(): Int {
        return this.cidades.size
    }

    // Infla o layout do adapter e retorna o ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CidadesViewHolder {
        // Infla a view do adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cidade, parent, false)
        // Retorna o ViewHolder que contém todas as views
        val holder = CidadesViewHolder(view)
        return holder
    }

    // Faz o bind para atualizar o valor das views com os dados do Cidade
    override fun onBindViewHolder(holder: CidadesViewHolder, position: Int) {
        // Recupera o objeto cidade
        val cidade = cidades[position]
        val view = holder.itemView

        with(view) {
            // Atualiza os dados do cidade
            tNome.text = cidade.nome

            // Faz o download da foto e mostra o ProgressBar
            img.loadUrl(cidade.urlFoto, progress)

            // Adiciona o evento de clique na linha
            setOnClickListener { onClick(cidade) }
        }
    }

    // ViewHolder fica vazio pois usamos o import do Android Kotlin Extensions
    class CidadesViewHolder(view: View) : RecyclerView.ViewHolder(view)
}