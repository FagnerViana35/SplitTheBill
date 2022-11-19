package com.example.splitthebill.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.splitthebill.R
import com.example.splitthebill.databinding.ActivityDetailsBinding
import com.example.splitthebill.databinding.ActivityMainBinding

class DetailsActivity : AppCompatActivity() {

    private val dab: ActivityDetailsBinding by lazy{
        ActivityDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dab.root)
    }

    
}