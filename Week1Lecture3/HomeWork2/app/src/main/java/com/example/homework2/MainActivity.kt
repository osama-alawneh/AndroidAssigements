package com.example.homework2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework2.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBackgroundLogic(binding)
        setImageBackgroundLogic(binding)
    }

    private fun setBackgroundLogic(binding : ActivityMainBinding){
        binding.backgroundBtn.setOnClickListener(){
            binding.root.setBackgroundColor(randomColor())
        }
    }
    private fun setImageBackgroundLogic(binding : ActivityMainBinding){
        binding.maharishiImageLogoBtn.setOnClickListener(){
            binding.maharishiImageLogoBtn.setBackgroundColor(randomColor())
        }
    }
    private fun randomColor() : Int{
        val rnd = Random()
        val color = Color.argb(
            255,
            rnd.nextInt(256),
            rnd.nextInt(256),
            rnd.nextInt(256)
        )
        return color;
    }

}