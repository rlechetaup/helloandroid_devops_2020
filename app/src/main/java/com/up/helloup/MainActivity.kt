package com.up.helloup

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.up.helloup.cidades.CidadesActivity
import com.up.helloup.login.LoginService
import com.up.helloup.utils.extensions.alert
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btLogin.setOnClickListener { onClickLogin() }
        tTitle.setOnLongClickListener { onClickFake() }
    }

    @SuppressLint("SetTextI18n")
    private fun onClickFake(): Boolean {
        tLogin.setText("user")
        tSenha.setText("123")
        return true
    }

    private fun onClickLogin() {
        val login = tLogin.text.toString()
        val senha = tSenha.text.toString()

        if(login.isEmpty() && senha.isEmpty()) {
            alert(getString(R.string.msg_login_empty));
        } else if(login.isEmpty() || senha.isEmpty()) {
            alert(getString(R.string.msg_login_empty2));
        } else {
            val service = LoginService()
            val user = service.login(login,senha)
            if(user != null) {

                startActivity<CidadesActivity>()

            } else {
                alert("Login incorreto, digite os dados novamente")
            }
        }
    }
}