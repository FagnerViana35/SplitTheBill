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

class AdapterMembers (
    context: Context,
    private val integrantesList: MutableList<Integrantes>
    ) : ArrayAdapter<Integrantes>(context, R.layout.tile_member, integrantesList) {
    private data class TileContactHolder(val name: TextView, val valuePay: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val member = integrantesList[position]
        var memberTileView = convertView
        if (memberTileView == null) {

            memberTileView =
                (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.tile_member,
                    parent,
                    false
                )

            val tileContactHolder = TileContactHolder(
                memberTileView.findViewById(R.id.name),
                memberTileView.findViewById(R.id.valuePay),
            )
            memberTileView.tag = tileContactHolder
        }

        with(memberTileView?.tag as TileContactHolder) {
            name.text = member.name
            valuePay.text = member.valuePay.toString()
        }

        return memberTileView
    }
}

