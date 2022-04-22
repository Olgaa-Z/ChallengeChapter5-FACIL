package binar.andlima.challengechapter5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import binar.andlima.challengechapter5.R
import binar.andlima.challengechapter5.model.ResponseFilmItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_film.*

class DetailFilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)

        val detailfilm = intent.getParcelableExtra("detailfilm") as ResponseFilmItem?

        tv_detailjudulfilm.text = detailfilm?.title
        tv_detailsutradarafilm.text = detailfilm?.director
        tv_detailtglfilm.text = detailfilm?.releaseDate
        tvDetailsinopsisfilm.text = detailfilm?.synopsis

        Glide.with(this).load(detailfilm?.image).into(detail_imgfilm)

    }
}