package com.example.lealgym.model

import java.util.Date

data class Treino(
    var nome: String = "",
    var id_user: String = "",
    var descricao: String = "",
    var date: Date? = null,
    var exercicio: Exercicio? = null
)
