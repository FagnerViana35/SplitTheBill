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
    ) : ArrayAdapter<Integrantes>(context, R.layout.tile_contact, integrantesList) {
    private data class TileContactHolder(val nameTv: TextView, val emailTv: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contact = integrantesList[position]
        var contactTileView = convertView
        if (contactTileView == null) {

            contactTileView =
                (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.tile_contact,
                    parent,
                    false
                )

            val tileContactHolder = TileContactHolder(
                contactTileView.findViewById(R.id.nameTv),
                contactTileView.findViewById(R.id.emailTv),
            )
            contactTileView.tag = tileContactHolder
        }

        with(contactTileView?.tag as TileContactHolder) {
            nameTv.text = contact.name
            emailTv.text = contact.email
        }

        return contactTileView
    }
}

