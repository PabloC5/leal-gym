package com.example.lealgym.model

import java.sql.Timestamp

data class Treino(
    var nome: Number = 0,
    var descricao: String = "",
    var data: Timestamp? = null,
    var exercicio: Exercio = null
)
