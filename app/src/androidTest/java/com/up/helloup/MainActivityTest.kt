package com.up.helloup

import androidx.test.espresso.Espresso
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
public class MainActivityOKTest {

    @Rule @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loginView() {

        Tester()
            .checkTitle("HelloUP")
            .checkText("App Teste")
            .checkDisplayed(R.id.tLogin, R.id.tSenha)
            .checkHint(
                Pair(R.id.tLogin, R.string.hint_login),
                Pair(R.id.tSenha, R.string.hint_senha)
            )
    }

    @Test
    fun loginErro_required() {

        Tester()
            .click(R.id.btLogin)
            .checkDialog(title = R.string.alert_title, msg = R.string.msg_login_empty)

    }

    @Test
    fun loginErro_required2() {

        Tester()
            .typeText(R.id.tLogin, "teste", closeKeyboard = true)
            .click(R.id.btLogin)
            .checkDialog(title = R.string.alert_title, msg = R.string.msg_login_empty2)
    }

    @Test
    fun loginOk_ListaOk() {

        Tester()
            .typeText(R.id.tLogin, "ricardo")
            .typeText(R.id.tSenha, "123")
            .click(R.id.btLogin)

        Tester()
            .checkTitle("Cidades")
            .wait(6000)
            .checkCount(2)
            .clickItem(0)
            .checkTitle("Lisboa")
            .back().checkTitle("Cidades")
            .clickItem(1)
            .checkTitle("Roma")
            .back().checkTitle("Cidades")
    }
}
