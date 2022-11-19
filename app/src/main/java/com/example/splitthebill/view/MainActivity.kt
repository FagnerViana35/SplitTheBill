package com.example.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.splitthebill.R
import com.example.splitthebill.adapters.AdapterMembers
import com.example.splitthebill.databinding.ActivityMainBinding
import com.example.splitthebill.model.Constants.EXTRA_MEMBER
import com.example.splitthebill.model.Constants.VIEW_MEMBER
import com.example.splitthebill.model.Integrantes

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val listMembers : MutableList<Integrantes> = mutableListOf()
    private lateinit var adapterMembers : AdapterMembers;

    private lateinit var marl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

    ListaDeIntegrantes()

        adapterMembers = AdapterMembers(this, listMembers)
        amb.membersLV.adapter = adapterMembers

        marl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val member = result.data?.getParcelableExtra<Integrantes>(EXTRA_MEMBER)

                member?.let { _member->
                    val position = listMembers.indexOfFirst { it.id == _member.id }
                    if (position != -1) {

                        listMembers[position] = _member
                    }
                    else {
                        listMembers.add(_member)
                    }
                    adapterMembers.notifyDataSetChanged()
                }
            }
        }

        registerForContextMenu(amb.membersLV)

        amb.membersLV.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val member = listMembers[position]
                val memberIntent = Intent(this@MainActivity, MemberActivity::class.java)
                memberIntent.putExtra(EXTRA_MEMBER, member)
                memberIntent.putExtra(VIEW_MEMBER, true)
                startActivity(memberIntent)
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addMember -> {
                val intentMemberActivity = Intent(this,MemberActivity::class.java)
                marl.launch(intentMemberActivity)
                true
            }
            else -> {false}
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position

        return when(item.itemId) {
            R.id.editMember -> {
                val member = listMembers[position]
                val memberIntent = Intent(this, MemberActivity::class.java)
                memberIntent.putExtra(EXTRA_MEMBER, member)
                memberIntent.putExtra(VIEW_MEMBER, false)
                marl.launch(memberIntent)
                true
            }
            R.id.removeMember -> {
                listMembers.removeAt(position)
                adapterMembers.notifyDataSetChanged()

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