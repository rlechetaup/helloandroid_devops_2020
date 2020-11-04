package com.up.helloup.login

class LoginService {
    fun login(login:String, senha: String): Usuario? {
        if(login == "ricardo" && senha == "123") {
            return Usuario("Ricardo","a@a.com")
        } else if(login == "teste" && senha == "123") {
            return Usuario("Teste","b@b.com")
        } else if(login == "user" && senha == "123") {
            return Usuario("User","b@b.com")
        }
        return null
    }
}