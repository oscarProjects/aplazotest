package com.oscarvj.aplazotest.ui.viewmodel.meals

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscarvj.aplazotest.data.model.HeaderMealsModel
import com.oscarvj.aplazotest.domain.MealsUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val mealsUsesCase: MealsUsesCase
) : ViewModel() {

    val mealsModel = MutableLiveData<HeaderMealsModel?>()

    val isLoading = MutableLiveData<Boolean>()

    fun getMealsByCategory(strCategory: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = mealsUsesCase.invoke(strCategory)
            if(result != null){
                mealsModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}