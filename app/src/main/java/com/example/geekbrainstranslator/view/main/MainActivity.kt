package com.example.geekbrainstranslator.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.geekbrainstranslator.databinding.ActivityMainBinding
import org.koin.android.ext.android.getKoin

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(binding.mainActivityContainer.id, MainTranslationFragment.newInstance())
                .commit()
        }
    }
}