package com.example.geekbrainstranslator.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geekbrainstranslator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(binding.container.id, MainTranslationFragment.newInstance())
                .commit()
        }
    }
}