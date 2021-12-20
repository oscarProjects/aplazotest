package com.oscarvj.aplazotest.ui.viewmodel.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscarvj.aplazotest.data.model.CategoriesModel
import com.oscarvj.aplazotest.data.model.CategoryModel
import com.oscarvj.aplazotest.data.model.HeaderDetailMealModel
import com.oscarvj.aplazotest.domain.CategoriesUsesCase
import com.oscarvj.aplazotest.domain.MealByNameUsesCase
import com.oscarvj.aplazotest.domain.MealRandomUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesUsesCase: CategoriesUsesCase,
    private val randomMealUsesCase: MealRandomUsesCase,
    private val mealByNameUsesCase: MealByNameUsesCase
) : ViewModel() {

    val categoriesModel = MutableLiveData<CategoriesModel?>()

    val randomMealModel = MutableLiveData<HeaderDetailMealModel?>()

    val mealByNameModel = MutableLiveData<HeaderDetailMealModel?>()

    val isLoading = MutableLiveData<Boolean>()

    fun getAllCategories(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = categoriesUsesCase()
            if(result != null){
                categoriesModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun getRandomMeal(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = randomMealUsesCase()
            if(result != null){
                randomMealModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun getMealByName(name: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = mealByNameUsesCase(name)
            if(result.meals != null){
                mealByNameModel.postValue(result)
            }else{
                mealByNameModel.postValue(null)
            }
            isLoading.postValue(false)

        }
    }
}