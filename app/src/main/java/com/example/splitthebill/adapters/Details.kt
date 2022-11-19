package com.example.splitthebill.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.splitthebill.R
import com.example.splitthebill.model.Integrantes
import kotlin.math.roundToInt

class Details (
    context: Context,
    private val integrantesList: MutableList<Integrantes>
) : ArrayAdapter<Integrantes>(context, R.layout.tile_details, integrantesList) {
    private data class TileMemberHolder(val nomeDetail: TextView, val thePay: TextView, val theReceiver: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val member = integrantesList[position]
        var memberDetailView = convertView
        if (memberDetailView == null) {

            memberDetailView =
                (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.tile_details,
                    parent,
                    false
                )

            val tileContactHolder = TileMemberHolder(
                memberDetailView.findViewById(R.id.nomeDetail),
                memberDetailView.findViewById(R.id.thePay),
                memberDetailView.findViewById(R.id.theReceiver),
            )
            memberDetailView.tag = tileContactHolder
        }

        with(memberDetailView?.tag as TileMemberHolder) {
            nomeDetail.text = member.name
            thePay.text = member.valuePay.toString()
            theReceiver.text = member.valuePay.toString()
        }

        with(memberDetailView?.tag as TileMemberHolder) {
            var media: Double;
            var soma: Double = 0.0;
            var totalMembers: Int = integrantesList.size;

            for (member in integrantesList){
                soma += member.valuePay
            }
            media = soma / totalMembers;

            var diferenca: Double = media - member.valuePay

            if(diferenca < 0) {
                theReceiver.text = "Terá que receber: R$ " + ("%.2f".format((diferenca * -1)))
                thePay.visibility = View.GONE;
            }else if(diferenca > 0){
                thePay.text = "Terá que pagar: R$ " + ("%.2f".format(diferenca))
                theReceiver.visibility = View.GONE
            }else{
                theReceiver.text = "Membro não está devendo nada!";
            }

            nomeDetail.text = member.name

        }

        return memberDetailView

    }


}