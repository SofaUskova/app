package com.example.myapplication.api

import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    //сохранение логина и пароля
    @POST("addUser")
    fun addUser(@Body user: UserPrincipal): Call<UserPrincipal>

    //сохраение даннных нового пользователя
    @POST("addSeller")
    fun addNewSeller(@Body seller: Seller): Call<Seller>

    //вход по логину
    @GET("seller/{login}")
    fun getSeller(@Path("login") login: String): Call<Seller>

    //писок продаваемых лошадей
    @GET("allHorses")
    fun getAllHorses(): Call<List<SalesContract>>

    //выставить лошадь на продажу
    @POST("addHorse")
    fun addHorse(@Body horse: Horse): Call<Horse>

    //обавление договора купли-продажи
    @POST("addSalesContract")
    fun addSalesContract(@Body salesContract: SalesContract): Call<SalesContract>

    //детальная информация о лошади
    @GET("informHorse/{idHorse}")
    fun getDetailInformHorse(@Path("idHorse") id: Int): Call<SalesContract>

    //лошади в избранном
    @GET("favoriteHorses/{login}")
    fun getFavoriteHorses(@Path("login") login: String): Call<List<SalesContract>>

    //добавить лошадь в избранное
    @POST("addFavoriteHorse")
    fun addFavoriteHorse(@Body favoriteHorse: FavoriteHorse): Call<FavoriteHorse>

    ///////////////////////////////////// запросы для списков //////////////////////////////////////

    @GET("allBreeds")
    fun getAllBreeds(): Call<List<String>>

    @GET("allColors")
    fun getAllColors(): Call<List<String>>

    @GET("allCities")
    fun getAllCities(): Call<List<String>>

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @GET("sellersHorses/{id}")
    fun getSellersHorses(@Path("id") id: Int): Call<List<Horse>>
}