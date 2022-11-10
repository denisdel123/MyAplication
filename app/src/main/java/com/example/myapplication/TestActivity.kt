package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.myapplication.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    lateinit var binding : ActivityTestBinding
    private var singState = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


       singState = intent.getStringExtra(Constance.SING_KEY)!!

        if (singState == Constance.SING_IN){
            binding.name1.visibility = View.GONE
            binding.name2.visibility = View.GONE
            binding.name3.visibility = View.GONE
            binding.avatarButton.visibility = View.INVISIBLE
        }

    }


    fun onClikavatar (view : View){

        binding.avatar2.setImageResource(R.drawable.ic_launcher_foreground)
        binding.avatar2.visibility = View.VISIBLE

    }


    fun onClikOK (view: View){
        if (singState == Constance.SING_UP){
            val intent = Intent()
            intent.putExtra(Constance.LOGIN_KEY,binding.login.text.toString())
            intent.putExtra(Constance.PASSWORD_KEY,binding.password.text.toString())
            intent.putExtra(Constance.NAME1_KEY,binding.name1.text.toString())
            intent.putExtra(Constance.NAME2_KEY,binding.name2.text.toString())
            intent.putExtra(Constance.NAME3_KEY,binding.name3.text.toString())
            if (binding.avatar2.isVisible)intent.putExtra(Constance.AVATAR_KEY, R.drawable.ic_launcher_foreground)
            setResult(RESULT_OK, intent)
            finish()


        }
        else if (singState == Constance.SING_IN){
            intent.putExtra(Constance.LOGIN_KEY,binding.login.text.toString())
            intent.putExtra(Constance.PASSWORD_KEY,binding.password.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}