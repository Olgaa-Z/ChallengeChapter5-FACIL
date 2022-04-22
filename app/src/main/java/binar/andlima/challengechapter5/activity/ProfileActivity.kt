package binar.andlima.challengechapter5.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import binar.andlima.challengechapter5.R
import binar.andlima.challengechapter5.model.Detailuser
import binar.andlima.challengechapter5.viewmodel.ViewModelFilm
import binar.andlima.challengechapter5.viewmodel.ViewModelProfile
import binar.andlima.challengechapter5.viewmodel.ViewModelUsers
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    lateinit var listuser : List<Detailuser>
    private lateinit var sharedPreference : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        sharedPreference = getSharedPreferences("datauser" , Context.MODE_PRIVATE)

        getDataProfile()

        updateProfile.setOnClickListener{
            updateDataProfile()
        }
        logoutProfile.setOnClickListener {
            logout()
        }

    }


    fun getDataProfile(){
        val id = sharedPreference.getString("id","")
        val viewModel = ViewModelProvider(this).get(ViewModelProfile::class.java)
        viewModel.DetailUserAPI(id!!.toInt())
        viewModel.getLiveProfile().observe(this, Observer {
            if (it != null){
                listuser = it
                initData(listuser)
                Toast.makeText(this, it.toString(),Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "failed" ,Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun initData(userdatalist : List<Detailuser>){

        for (i in userdatalist.indices){
            usernameProfile.setText(userdatalist[i].username)
            cnameProfile.setText(userdatalist[i].completeName)
            addressProfile.setText(userdatalist[i].address)
            birthdateProfile.setText(userdatalist[i].dateofbirth)
        }
    }

   fun  updateDataProfile(){
       val id = sharedPreference.getString("id","")

       val cname = cnameProfile.text.toString()

       val username = usernameProfile.text.toString()
       val address = addressProfile.text.toString()
       val dob = birthdateProfile.text.toString()

       val viewModel = ViewModelProvider(this).get(ViewModelUsers::class.java)
       viewModel.getLiveUpdate().observe(this, Observer {
           if (it != null){
               Toast.makeText(this, "Gagal Update Data", Toast.LENGTH_SHORT).show()
           }else{
               Toast.makeText(this, "Berhasil Update Data User", Toast.LENGTH_SHORT).show()
           }
       })

       viewModel.updateUserAPI(id!!.toInt(),cname, username, address, dob)
    }

    fun logout(){
        AlertDialog.Builder(this)
            .setTitle("Keluar Aplikasi")
            .setMessage("Yakin keluar dari aplikasi?")
            .setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->
                sharedPreference = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
                val SF = sharedPreference.edit()
                SF.clear()
                SF.apply()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            .show()
    }

}