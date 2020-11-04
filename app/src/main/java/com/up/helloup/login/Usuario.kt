package com.up.helloup.login

import java.io.Serializable

data class Usuario (
    var nome: String,
    var email: String
) : Serializable
