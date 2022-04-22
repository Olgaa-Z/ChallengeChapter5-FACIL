package binar.andlima.challengechapter5.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class Detailuser(
    @SerializedName("address")
    val address: String,
    @SerializedName("complete_name")
    val completeName: String,
    @SerializedName("dateofbirth")
    val dateofbirth: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val username: String
)