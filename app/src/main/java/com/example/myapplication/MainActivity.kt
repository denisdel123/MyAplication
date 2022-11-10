package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var login = "empty"
    private var password = "empty"
    private var name1 = "empty"
    private var name2 = "empty"
    private var name3 = "empty"
    private var avatar = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constance.REQUEST_CODE_SING_IN){
            val l = data?.getStringExtra(Constance.LOGIN_KEY)
            val p = data?.getStringExtra(Constance.PASSWORD_KEY)

            if (login == l && password == p){
                binding.avatar1.visibility  = View.VISIBLE
                binding.avatar1.setImageResource(avatar)
                val info = "$name1, $name2, $name3"
                binding.textViewMain.text = info
                binding.singUp.visibility = View.GONE
                binding.singIn.text = "выйти"

            }
            else{
                val infoNo = "неверный логин или пароль"
                binding.textViewMain.text = infoNo
                binding.avatar1.visibility  = View.VISIBLE
                binding.avatar1.setImageResource(R.drawable.dula)

            }

        }

        else if (requestCode == Constance.REQUEST_CODE_SING_UP){
            login = data?.getStringExtra(Constance.LOGIN_KEY)!!
            password = data?.getStringExtra(Constance.PASSWORD_KEY)!!
            name1 = data?.getStringExtra(Constance.NAME1_KEY)!!
            name2 = data?.getStringExtra(Constance.NAME2_KEY)!!
            name3 = data?.getStringExtra(Constance.NAME3_KEY)!!
            avatar = data.getIntExtra(Constance.AVATAR_KEY, 0)
            binding.avatar1.setImageResource(avatar)
            binding.avatar1.visibility  = View.VISIBLE
            val info = "$name1, $name2, $name3"
            binding.textViewMain.text = info
            binding.singUp.visibility = View.GONE
            binding.singIn.text = getString(R.string.exit)
        }
    }


    fun onClikSingIn(view: View) {

        if (binding.avatar1.isVisible && binding.textViewMain.text.toString() != "неверный логин или пароль"){
            binding.avatar1.visibility = View.INVISIBLE
            binding.singUp.visibility = View.VISIBLE
            binding.textViewMain.text = ""
            binding.singIn.text = getString(R.string.sing_in)


        }
        else{
            val intent1 = Intent(this,TestActivity::class.java)
            intent1.putExtra(Constance.SING_KEY, Constance.SING_IN)
            startActivityForResult(intent1, Constance.REQUEST_CODE_SING_IN)
        }


    }


    fun onClikSingUp(view: View) {

        val intent = Intent(this,TestActivity::class.java)
        intent.putExtra(Constance.SING_KEY, Constance.SING_UP)
        startActivityForResult(intent,Constance.REQUEST_CODE_SING_UP)

    }






}