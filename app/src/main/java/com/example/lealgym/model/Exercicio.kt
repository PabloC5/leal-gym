package com.example.lealgym.model

import kotlinx.parcelize.Parcelize

data class Exercicio(
    var nome: String = "",
    var imagem: String = "",
    var observacao: String = ""
)
