package com.oscarvj.aplazotest.domain

import com.oscarvj.aplazotest.data.model.HeaderDetailMealModel
import com.oscarvj.aplazotest.data.repository.DataRepository
import javax.inject.Inject

class MealByNameUsesCase  @Inject constructor(
    private val repository: DataRepository
){
    suspend operator fun invoke(name: String): HeaderDetailMealModel = repository.getMealByName(name)
}