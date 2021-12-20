package com.oscarvj.aplazotest.data.repository

import com.oscarvj.aplazotest.data.model.*
import com.oscarvj.aplazotest.data.network.ApiService
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val api: ApiService
){

    suspend fun getAllCategories(): CategoriesModel {
        return api.getCategories()
    }

    suspend fun getMealsByCategory(strCategory: String): HeaderMealsModel {
        return api.getMealsByCategory(strCategory)
    }

    suspend fun getDetailsMeals(id: String): HeaderDetailMealModel {
        return api.getDetailsMeals(id)
    }

    suspend fun getRandomMeal(): HeaderDetailMealModel {
        return api.getRandomMeal()
    }

    suspend fun getMealByName(name: String): HeaderDetailMealModel {
        return api.getMealByName(name)
    }
}