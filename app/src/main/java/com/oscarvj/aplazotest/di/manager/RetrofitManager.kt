package com.oscarvj.aplazotest.di.manager

import com.oscarvj.aplazotest.data.model.*
import com.oscarvj.aplazotest.data.network.ApiClient
import retrofit2.Response

class RetrofitManager(var api: ApiClient) {

    suspend fun callServiceGetCategories(service: String): Response<CategoriesModel>? {
        return api.getCategories(service)
    }

    suspend fun callServiceGetMeals(service: String): Response<HeaderMealsModel>? {
        return api.getMeals(service)
    }

    suspend fun callServiceGetDetailMeals(service: String): Response<HeaderDetailMealModel>? {
        return api.getDetailsMeals(service)
    }

    suspend fun callServiceGetRandomMeal(service: String): Response<HeaderDetailMealModel>? {
        return api.getRandomMeal(service)
    }
}
