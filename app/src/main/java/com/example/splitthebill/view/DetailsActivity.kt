package com.example.splitthebill.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.splitthebill.R
import com.example.splitthebill.adapters.AdapterMembers
import com.example.splitthebill.adapters.Details
import com.example.splitthebill.databinding.ActivityDetailsBinding
import com.example.splitthebill.databinding.ActivityMainBinding
import com.example.splitthebill.model.Integrantes

class DetailsActivity : AppCompatActivity() {

    private val dab: ActivityDetailsBinding by lazy{
        ActivityDetailsBinding.inflate(layoutInflater)
    }

    private var listDetails : ArrayList<Integrantes>? = arrayListOf()
    private lateinit var adapterDetails : Details;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dab.root)

        var membersReceivers = intent.getParcelableArrayListExtra<Integrantes>("Details")

        listDetails = membersReceivers

        adapterDetails = listDetails?.let { Details(this, it) }!!

        dab.membersDetailsLV.adapter = adapterDetails
    }

}