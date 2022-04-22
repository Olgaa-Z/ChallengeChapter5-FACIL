package binar.andlima.challengechapter5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import binar.andlima.challengechapter5.R
import binar.andlima.challengechapter5.viewmodel.ViewModelUsers
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener{
            if (etrpassword.text.toString() != etrkonfirmpassword.text.toString()){
                Snackbar.make(it, "Password Tidak Sama ", Snackbar.LENGTH_LONG).show()
            }else{
                doRegister()
            }
        }
    }

    fun doRegister(){
        val userName = etrUsername.text.toString()
        val email = etrEmail.text.toString()
        val  pass = etrpassword.text.toString()
        val viewModel = ViewModelProvider(this).get(ViewModelUsers::class.java)
        viewModel.getLiveRegister().observe(this, Observer {
            if (it != null){
                Toast.makeText(this, "Berhasil", Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this, "Gagal", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.registerUserAPI(email,pass,userName)
    }


}