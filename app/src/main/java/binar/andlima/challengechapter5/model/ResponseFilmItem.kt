package binar.andlima.challengechapter5.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseFilmItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("title")
    val title: String
):Parcelable