package binar.andlima.challengechapter5.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import binar.andlima.challengechapter5.MainActivity
import binar.andlima.challengechapter5.R
import binar.andlima.challengechapter5.model.Data
import binar.andlima.challengechapter5.model.Detailuser
import binar.andlima.challengechapter5.model.Responseuser
import binar.andlima.challengechapter5.viewmodel.ViewModelUsers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var sharepref : SharedPreferences
    lateinit var listuserlogin : List<Responseuser>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val dataUser = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
        if (dataUser.contains("email")){
            startActivity(Intent(this, MainActivity::class.java))
        }

        sharepref = this.getSharedPreferences("datauser",Context.MODE_PRIVATE)

        btnLogin.setOnClickListener{

            if (etEmail.text.toString().isEmpty()){
                etEmail.setError("Isi Username")
            }else if(etPassword.text.toString().isEmpty()){
                etPassword.setError("Isi Password")
            }else{
                doLogin()
            }
        }

        tvBelumpunyaakun.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

     fun doLogin(){

        val viewModel = ViewModelProvider(this).get(ViewModelUsers::class.java)
         viewModel.loginUserAPI()
         viewModel.getLiveLogin().observe(this, Observer {

             if (it != null){
                 listuserlogin = it
                 loginAuth(listuserlogin)
                 startActivity(Intent(this, MainActivity::class.java))
                finish()
             }else{
                 Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
             }
//            if (it != null){
//                listuserlogin = it
//                loginAuth(listuserlogin)

//
//            }else{
//                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//            }
        })
    }

    fun loginAuth(listlogin : List<Responseuser>){
        val e = etEmail.text.toString()
        val  p = etPassword.text.toString()

        for(i in listlogin.indices){
            if (e == listlogin[i].email && p == listlogin[i].password) {
                sharepref = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
                val sf = sharepref.edit()
                sf.putString("email", listlogin[i].email)
                sf.putString("id", listlogin[i].id)
                sf.apply()
                Toast.makeText(this, listlogin[i].username, Toast.LENGTH_SHORT).show()
            }
        }

    }

}