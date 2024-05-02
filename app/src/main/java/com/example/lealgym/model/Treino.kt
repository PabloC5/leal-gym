package com.example.lealgym.model

import java.sql.Timestamp

data class Treino(
    var nome: String = "",
    var id_user: String = "",
    var descricao: String = "",
    var date: Timestamp? = null,
    var exercicio: Exercicio? = null
)
