package com.oscarvj.aplazotest.domain

import com.oscarvj.aplazotest.data.repository.DataRepository
import com.oscarvj.aplazotest.data.model.CategoriesModel
import javax.inject.Inject

class CategoriesUsesCase @Inject constructor(
    private val repository: DataRepository
){
    suspend operator fun invoke() : CategoriesModel? = repository.getAllCategories()
}