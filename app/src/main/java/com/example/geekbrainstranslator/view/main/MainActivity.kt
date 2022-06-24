package com.example.geekbrainstranslator.view.main

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.geekbrainstranslator.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
        }
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setSplashScreen()
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(binding.mainActivityContainer.id, MainTranslationFragment.newInstance())
                .commit()
        }
    }

    private fun setSplashScreen() {
        var isHideSplashScreen = false

        val scope = CoroutineScope(
            Dispatchers.IO
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setSplashScreenAnimation()
        }

        scope.launch {
            delay(1000)
            isHideSplashScreen = true
            scope.cancel()
        }


        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (isHideSplashScreen) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    @RequiresApi(31)
    private fun setSplashScreenAnimation() {
        splashScreen.setOnExitAnimationListener { view ->
            val slideLeft = ObjectAnimator.ofFloat(
                view,
                View.TRANSLATION_Y,
                0f,
                view.height.toFloat()
            )
            slideLeft.interpolator = AnticipateInterpolator()
            slideLeft.duration = 800
            slideLeft.doOnEnd { view.remove() }
            slideLeft.start()
        }
    }
}