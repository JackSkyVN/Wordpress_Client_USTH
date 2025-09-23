package com.example.wordpressclient

import android.os.Bundle
import android.view.View
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNav.setOnItemSelectedListener { item ->
            val itemView: View? = bottomNav.findViewById(item.itemId)

            // Animate the clicked item
            itemView?.let {
                val scaleUp = ScaleAnimation(
                    1f, 1.2f, // X: from normal to 120%
                    1f, 1.2f, // Y: from normal to 120%
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f, // Pivot X (center)
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f  // Pivot Y (center)
                )
                scaleUp.duration = 200
                scaleUp.fillAfter = true
                it.startAnimation(scaleUp)
            }

            true
        }
    }
}
