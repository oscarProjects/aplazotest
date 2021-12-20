package com.oscarvj.aplazotest.domain

import com.oscarvj.aplazotest.data.model.HeaderDetailMealModel
import com.oscarvj.aplazotest.data.repository.DataRepository
import javax.inject.Inject

class MealRandomUsesCase  @Inject constructor(
    private val repository: DataRepository
){
    suspend operator fun invoke(): HeaderDetailMealModel = repository.getRandomMeal()
}