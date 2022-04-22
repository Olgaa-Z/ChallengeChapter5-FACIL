package binar.andlima.challengechapter5.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class ResponseRegister(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String
):Parcelable