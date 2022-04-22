package binar.andlima.challengechapter5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.andlima.challengechapter5.R
import binar.andlima.challengechapter5.model.ResponseFilmItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_film.view.*
import retrofit2.Response

class FilmAdapter(private var onClick : (ResponseFilmItem)->Unit): RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    private var dataFilm : List<ResponseFilmItem>? = null

    fun setDataFilm(film : List<ResponseFilmItem>){
        this.dataFilm = film
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.ViewHolder {
        val itemview = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false)
        return  ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: FilmAdapter.ViewHolder, position: Int) {
        holder.itemView.tvJudulfilm.text = dataFilm!![position].title
        holder.itemView.tvTglFilm.text = dataFilm!![position].releaseDate
        holder.itemView.tvSutradaraFilm.text = dataFilm!![position].director

        Glide.with(holder.itemView.context).load(dataFilm!![position].image).into(holder.itemView.imgFilm)
        holder.itemView.cardFilm.setOnClickListener {
            onClick(dataFilm!![position])
        }

    }

    override fun getItemCount(): Int {
        if (dataFilm == null){
            return 0
        }
        else{
            return dataFilm?.size!!
        }
    }
}