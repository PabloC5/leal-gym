package com.example.lealgym.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.lealgym.R
import com.example.lealgym.databinding.ActivityMainBinding
import androidx.navigation.fragment.NavHostFragment
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.bind.root)

        supportFragmentManager.findFragmentById(this.bind.navHostFragment.id) as NavHostFragment
    }
}