package binar.andlima.challengechapter5

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import binar.andlima.challengechapter5.activity.DetailFilmActivity
import binar.andlima.challengechapter5.activity.ProfileActivity
import binar.andlima.challengechapter5.adapter.FilmAdapter
import binar.andlima.challengechapter5.viewmodel.ViewModelFilm
import binar.andlima.challengechapter5.viewmodel.ViewModelUsers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapterfilm :FilmAdapter
    lateinit var prefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
        tvUsername.text = "Username, " + prefs.getString("email","")



        rvFilm.layoutManager = LinearLayoutManager(this)
        adapterfilm = FilmAdapter(){

            val pindahdata = Intent(applicationContext, DetailFilmActivity::class.java)
            pindahdata.putExtra("detailfilm", it)
            startActivity(pindahdata)
        }
        rvFilm.adapter = adapterfilm
        getDataFilm()

        iconProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

    }

    fun getDataFilm(){
        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.getLivedatFilm().observe(this, Observer {
            if (it != null){
                adapterfilm.setDataFilm(it)
                adapterfilm.notifyDataSetChanged()
            }
        })
        viewModel.getApiFilm()
    }
}