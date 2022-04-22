package binar.andlima.challengechapter5.network

import binar.andlima.challengechapter5.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

//    @GET("users")
//    fun getAllUser(): Call<ResponseAllUsersItem>

//    @POST("users")
//    fun postUsers(@Body req : RequestUsers): Call<ResponseAllUsersItem>

    @POST("register.php")
    @FormUrlEncoded
    fun registerUser(
        @Field("email")email : String,
        @Field("password")password : String,
        @Field("username")username : String,
    ): Call<ResponseRegister>

    @POST("login.php")
    @FormUrlEncoded
    fun loginuser(
        @Field("email")email : String,
        @Field("password") password : String
    ): Call<List<Responseuser>>

    @GET("apiuser.php")
    fun allUser(): Call<List<Responseuser>>

    @GET("apifilm.php")
   fun getAllFilm(): Call<List<ResponseFilmItem>>

   @POST("updateuser.php")
   @FormUrlEncoded
   fun updateUser(
       @Field("id")id : String,
       @Field("complete_name")completename : String,
       @Field("username")username : String,
       @Field("address")adress : String,
       @Field("dateofbirth")birthday : String
   ): Call<Data>

    @POST("detailuser.php")
    @FormUrlEncoded
    fun detailUser(@Field ("id") id : Int) : Call<List<Detailuser>>

}