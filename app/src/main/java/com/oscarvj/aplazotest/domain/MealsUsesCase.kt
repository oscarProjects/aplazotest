package com.oscarvj.aplazotest.domain

import com.oscarvj.aplazotest.data.model.HeaderMealsModel
import com.oscarvj.aplazotest.data.repository.DataRepository
import javax.inject.Inject

class MealsUsesCase @Inject constructor(
    private val repository: DataRepository
){
    suspend operator fun invoke(strCategory: String): HeaderMealsModel = repository.getMealsByCategory(strCategory)
}