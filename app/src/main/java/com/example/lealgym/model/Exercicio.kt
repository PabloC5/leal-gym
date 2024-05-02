package com.example.lealgym.model

import android.net.Uri

data class Exercicio(
    var nome: String = "",
    var imagem: Uri? = null,
    var observacao: String = ""
)
