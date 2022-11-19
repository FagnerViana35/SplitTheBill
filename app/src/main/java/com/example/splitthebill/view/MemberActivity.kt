package com.example.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.splitthebill.R
import com.example.splitthebill.databinding.ActivityMainBinding
import com.example.splitthebill.databinding.ActivityMemberBinding
import com.example.splitthebill.model.Constants
import com.example.splitthebill.model.Constants.EXTRA_MEMBER
import com.example.splitthebill.model.Constants.VIEW_MEMBER
import com.example.splitthebill.model.Integrantes
import java.util.*

class MemberActivity : AppCompatActivity() {

    private val mab: ActivityMemberBinding by lazy{
        ActivityMemberBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mab.root)

        val receivedMember = intent.getParcelableExtra<Integrantes>(EXTRA_MEMBER)
        receivedMember?.let{ _receivedMember ->
            with(mab) {
                with(_receivedMember) {
                    nameEt.setText(name)
                    valuePayEt.setText(valuePay.toString())
                    listBuyEt.setText(listBuy).toString()
                }
            }
        }

        val viewContact = intent.getBooleanExtra(VIEW_MEMBER, false)
        if (viewContact) {
            mab.nameEt.isEnabled = false
            mab.valuePayEt .isEnabled = false
            mab.listBuyEt .isEnabled = false

            mab.nameEt.visibility = View.GONE
            mab.valuePayEt.visibility = View.GONE
            mab.saveMemberBt.visibility = View.GONE
        }

        mab.saveMemberBt.setOnClickListener {
            val member = Integrantes(
                id = receivedMember?.id?:Random(System.currentTimeMillis()).nextInt(),
                name = mab.nameEt.text.toString(),
                valuePay = mab.valuePayEt.text.toString().toDouble(),
                listBuy = mab.listBuyEt.text.toString(),
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_MEMBER, member)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

}