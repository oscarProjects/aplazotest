package com.oscarvj.aplazotest.data.network

import com.oscarvj.aplazotest.data.model.*
import com.oscarvj.aplazotest.di.manager.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor(
    private val serviceManager: RetrofitManager
) {

    suspend fun getCategories() : CategoriesModel{
        return withContext(Dispatchers.IO){
            val response = serviceManager.callServiceGetCategories("categories.php")
            response?.body()!!
        }
    }

    suspend fun getMealsByCategory(strCategory: String): HeaderMealsModel {
        return withContext(Dispatchers.IO){
            val response = serviceManager.callServiceGetMeals("filter.php?c=$strCategory")
            response?.body()!!
        }
    }

    suspend fun getDetailsMeals(id: String): HeaderDetailMealModel {
        return withContext(Dispatchers.IO){
            val response = serviceManager.callServiceGetDetailMeals("lookup.php?i=$id")
            response?.body()!!
        }
    }

    suspend fun getRandomMeal(): HeaderDetailMealModel {
        return withContext(Dispatchers.IO){
            val response = serviceManager.callServiceGetRandomMeal("random.php")
            response?.body()!!
        }
    }

    suspend fun getMealByName(name: String): HeaderDetailMealModel {
        return withContext(Dispatchers.IO){
            val response = serviceManager.callServiceGetRandomMeal("search.php?s=$name")
            response?.body()!!
        }
    }
}