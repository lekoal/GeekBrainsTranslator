package com.example.geekbrainstranslator.view.main

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.animation.doOnEnd
import com.example.geekbrainstranslator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSplashScreen()

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setSplashScreenAnimation()
        }

        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                isHideSplashScreen = true
            }
        }.start()

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
            slideLeft.duration = 1000
            slideLeft.doOnEnd { view.remove() }
            slideLeft.start()
        }
    }
}