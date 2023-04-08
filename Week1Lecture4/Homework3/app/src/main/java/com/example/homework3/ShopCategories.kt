package com.example.homework3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.homework3.databinding.ActivityShopCategoriesBinding

class ShopCategories : AppCompatActivity() {
    lateinit var binding: ActivityShopCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopCategoriesBinding.inflate(layoutInflater);
        setContentView(binding.root);
        setEmailInHeader();
    }

    private fun setEmailInHeader(){
        val userData = intent.getSerializableExtra("user") as User;
        binding.welcomeTextField.text = "Welcome, ${userData.emailAddress}";
    }
}