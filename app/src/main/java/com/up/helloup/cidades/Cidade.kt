package com.up.helloup.cidades

import java.io.Serializable

class Cidade : Serializable {

    var id: Long = 0
    var nome = ""
    var urlFoto = ""
    var lat = 0.0;
    var lng = 0.0;

    var pontosTuristicos : List<PontoTuristico> = listOf();

    override fun toString(): String {
        return "Cidade(id=$id, nome='$nome', urlFoto='$urlFoto', lat=$lat, lng=$lng, pontosTuristicos=$pontosTuristicos)"
    }


}

class PontoTuristico : Serializable {

    var nome = ""
    var urlFoto = ""
    var lat = 0.0;
    var lng = 0.0;

    override fun toString(): String {
        return nome
    }
}