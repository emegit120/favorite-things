package br.com.threeX.favoritethings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

//public const val SPLASH_TIME = 4000L

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
//        Handler(Looper.myLooper()!!).postDelayed(
//            {
//            val intent = Intent(this. NextActivity)
//            },
//            SPLASH_TIME
//        )
    }
}