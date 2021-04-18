package com.example.myapplication.api

import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    ///////////////////////////////////// запросы для списков //////////////////////////////////////

    @GET("/db/allBreeds")
    fun getAllBreeds(): Call<List<String>>

    @GET("/db/allColors")
    fun getAllColors(): Call<List<String>>

    @GET("/db/allCities")
    fun getAllCities(): Call<List<String>>

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @GET("/db/allHorses")
    fun getAllHorses(): Call<List<Horse>>

    @GET("/db/informHorse/{idHorse}")
    fun getDetailInformHorse(@Path("idHorse")id: Int): Call<Horse>

    @GET("/db/favoriteHorses/{idSeller}")
    fun getFavoriteHorses(@Path("idSeller")idSeller: Int): Call<List<Horse>>

    @POST("/db/addDocument")
    fun addDocument(@Body document: Document): Call<Document>

    @POST("/db/addHorse")
    fun addHorse(@Body horse: Horse): Call<Horse>

    @POST("/db/addSalesContract")
    fun addSalesContract(@Body salesContract: SalesContract): Call<SalesContract>

    @GET("/db/sellerId/{idSeller}")
    fun getSellerId(@Path("idSeller")idSeller: Int): Call<Seller>
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @GET("/db/entry/{login}")
    fun entry(@Path("login")login: String): Call<UserPrincipal>

    @GET("/db/seller/{login}")
    fun getSeller(@Path("login")login: String): Call<Seller>

    @POST("/db/addUser")
    fun addUser(@Body user: UserPrincipal): Call<UserPrincipal>

    @POST("/db/addSeller")
    fun addNewSeller(@Body seller: Seller): Call<Seller>




    @GET("/db/sellersHorses/{id}")
    fun getSellersHorses(@Path("id")id: Int): Call<List<Horse>>
}