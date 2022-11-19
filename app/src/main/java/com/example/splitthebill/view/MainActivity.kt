package com.example.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.splitthebill.R
import com.example.splitthebill.adapters.AdapterMembers
import com.example.splitthebill.databinding.ActivityMainBinding
import com.example.splitthebill.model.Integrantes

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val listMembers : MutableList<Integrantes> = mutableListOf()
    private lateinit var adapterMembers : AdapterMembers;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

    ListaDeIntegrantes()

        adapterMembers = AdapterMembers(this, listMembers)
        amb.membersLV.adapter = adapterMembers
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.addMember -> {
                carl.launch(Intent(this, ContactActivity::class.java))
                true
            }
            else -> { false }
        }
    }

    private fun ListaDeIntegrantes(){
        listMembers.add(
            Integrantes(1,"Fagner Viana", 230.00, "Linguiça, Tulipa, Carvão, Refrigentes, Cerveja, Vinho, Espetinho de Frango")
        )
        listMembers.add(
            Integrantes(2,"Valquiria", 60.00, "Contra Filé")
        )
        listMembers.add(
            Integrantes(3,"Gabriel", 90.00, ", Refrigerantes, Picanha e Pão de Alho")
        )

    }
}