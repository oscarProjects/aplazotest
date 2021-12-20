package com.oscarvj.aplazotest.ui.viewmodel.meals.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscarvj.aplazotest.data.model.HeaderDetailMealModel
import com.oscarvj.aplazotest.domain.DetailMealUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMealViewModel @Inject constructor(
    private val detailMealUsesCase: DetailMealUsesCase
) : ViewModel() {

    val mealDetailModel = MutableLiveData<HeaderDetailMealModel?>()

    val isLoading = MutableLiveData<Boolean>()

    fun getDetailMeal(id: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = detailMealUsesCase.invoke(id)
            if(result != null){
                mealDetailModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}