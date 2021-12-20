package com.oscarvj.aplazotest.data.network

import com.oscarvj.aplazotest.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiClient {

    @GET
    suspend fun getCategories(@Url uri: String): Response<CategoriesModel>?

    @GET
    suspend fun getMeals(@Url uri: String): Response<HeaderMealsModel>?

    @GET
    suspend fun getDetailsMeals(@Url uri: String): Response<HeaderDetailMealModel>?

    @GET
    suspend fun getRandomMeal(@Url uri: String): Response<HeaderDetailMealModel>?
}