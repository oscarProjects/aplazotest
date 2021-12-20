package com.oscarvj.aplazotest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoriesModel(
    @SerializedName("categories") val categories: List<CategoryModel>
)

data class CategoryModel(
    @SerializedName("idCategory") val idCategory: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strCategoryThumb") val strCategoryThumb: String,
    @SerializedName("strCategoryDescription") val strCategoryDescription: String
) : Serializable
