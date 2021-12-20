package com.oscarvj.aplazotest.domain

import com.oscarvj.aplazotest.data.model.HeaderDetailMealModel
import com.oscarvj.aplazotest.data.model.MealDetailModel
import com.oscarvj.aplazotest.data.repository.DataRepository
import javax.inject.Inject

class DetailMealUsesCase @Inject constructor(
    private val repository: DataRepository
){
    suspend operator fun invoke(id: String): HeaderDetailMealModel = repository.getDetailsMeals(id)
}