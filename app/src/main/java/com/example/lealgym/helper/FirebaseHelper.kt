package com.example.lealgym.helper

import com.example.lealgym.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class FirebaseHelper {
    companion object {
        fun getDatabase() = Firebase.firestore
        private fun getAuth() = FirebaseAuth.getInstance()

        fun getIdUser() = getAuth().uid

        fun getAutenticate() = getAuth().currentUser != null

        fun validError(error: String): Int {
            return  when {
                error.contains("The supplied auth credential is incorrect") -> {
                    R.string.account_not_registered_register
                }
                error.contains("The email address is badly formatted.") -> {
                    R.string.invalid_email_register
                }
                error.contains("The email address is already in use by another account") -> {
                    R.string.email_in_use_register
                }
                error.contains("The given password is invalid.") -> {
                    R.string.strong_password_register
                }
                else -> {
                    R.string.erro_generic
                }
            }
        }

    }

}