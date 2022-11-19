package com.example.splitthebill.adapters

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.splitthebill.R
import com.example.splitthebill.model.Integrantes

class Details (
    context: Context,
    private val integrantesList: MutableList<Integrantes>
) : ArrayAdapter<Integrantes>(context, R.layout.tile_details, integrantesList) {
    private data class TileContactHolder(val nomeDetail: TextView, val thePay: TextView, val theReceiver: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val member = integrantesList[position]
        var memberDetailView = convertView
        if (memberDetailView == null) {

            memberDetailView =
                (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.tile_member,
                    parent,
                    false
                )

            val tileContactHolder = TileContactHolder(
                memberDetailView.findViewById(R.id.nomeDetail),
                memberDetailView.findViewById(R.id.thePay),
                memberDetailView.findViewById(R.id.theReceiver),
            )
            memberDetailView.tag = tileContactHolder
        }

        with(memberDetailView?.tag as TileContactHolder) {
            nomeDetail.text = member.name
            thePay.text = member.valuePay.toString()
            theReceiver.text = member.valuePay.toString()
        }

        return memberDetailView
    }
}