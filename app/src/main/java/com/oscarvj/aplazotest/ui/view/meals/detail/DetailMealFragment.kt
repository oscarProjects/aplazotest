package com.oscarvj.aplazotest.ui.view.meals.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.oscarvj.aplazotest.R
import com.oscarvj.aplazotest.data.model.MealDetailModel
import com.oscarvj.aplazotest.data.model.MealModel
import com.oscarvj.aplazotest.databinding.FragmentDetailMealBinding
import com.oscarvj.aplazotest.ui.viewmodel.meals.detail.DetailMealViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.net.Uri
import com.oscarvj.aplazotest.di.manager.NavigationManager
import javax.inject.Inject


@AndroidEntryPoint
class DetailMealFragment : Fragment() {

    private lateinit var binding: FragmentDetailMealBinding

    @Inject
    lateinit var navigation: NavigationManager

    private lateinit var mealModel: MealModel

    private lateinit var randomMealModel: MealDetailModel

    private lateinit var urlYoutube: String

    private val detailMealViewModel: DetailMealViewModel by viewModels()

    private var isFromCategories = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null){
            isFromCategories = requireArguments().getBoolean("isFromCategories")
            if(isFromCategories){
                randomMealModel = requireArguments().getSerializable("randomMealModel") as MealDetailModel
            }else{
                mealModel = requireArguments().getSerializable("mealModel") as MealModel
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailMealBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!isFromCategories){
            getDetailMeal()
            initObservers()
        }else{
            setData(randomMealModel)
        }
        initListeners()
    }


    private fun getDetailMeal(){
        detailMealViewModel.getDetailMeal(mealModel.idMeal)
    }

    private fun initObservers(){
        detailMealViewModel.mealDetailModel.observe(this, Observer {
            if(it != null){
                setData(it.meals[0])
            }
        })

        detailMealViewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })
    }

    private fun initListeners() {
        binding.linearYoutube.setOnClickListener {
            if(!urlYoutube.isNullOrEmpty()){
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(urlYoutube)
                startActivity(i)
            }
        }

        binding.imageViewBack.setOnClickListener {
            navigation.onNavigateBack()
        }
    }

    private fun setData(mealDetail: MealDetailModel) {
        binding.textViewName.text = mealDetail.strMeal
        binding.textViewCategory.text = mealDetail.strCategory
        binding.textViewArea.text = mealDetail.strArea
        binding.textViewTags.text = mealDetail.strTags
        binding.textViewInstructions.text = mealDetail.strInstructions
        binding.textViewWebSite.text = mealDetail.strSource

        urlYoutube = mealDetail.strYoutube.toString()

        setIngredientsChips(buildArrayIngredients(mealDetail), buildArrayMeasure(mealDetail), binding.chipsIngredients)

        Glide.with(this)
            .load(mealDetail.strMealThumb)
            .circleCrop()
            .into(binding.imgMeal)
    }

    private fun buildArrayIngredients(mealDetail: MealDetailModel): ArrayList<String>{
        val arrayList: ArrayList<String> = ArrayList()
        mealDetail.strIngredient1?.let { arrayList.add(it) }
        mealDetail.strIngredient2?.let { arrayList.add(it) }
        mealDetail.strIngredient3?.let { arrayList.add(it) }
        mealDetail.strIngredient4?.let { arrayList.add(it) }
        mealDetail.strIngredient5?.let { arrayList.add(it) }
        mealDetail.strIngredient6?.let { arrayList.add(it) }
        mealDetail.strIngredient7?.let { arrayList.add(it) }
        mealDetail.strIngredient8?.let { arrayList.add(it) }
        mealDetail.strIngredient9?.let { arrayList.add(it) }
        mealDetail.strIngredient10?.let { arrayList.add(it) }
        mealDetail.strIngredient11?.let { arrayList.add(it) }
        mealDetail.strIngredient12?.let { arrayList.add(it) }
        mealDetail.strIngredient13?.let { arrayList.add(it) }
        mealDetail.strIngredient14?.let { arrayList.add(it) }
        mealDetail.strIngredient15?.let { arrayList.add(it) }
        mealDetail.strIngredient16?.let { arrayList.add(it) }
        mealDetail.strIngredient17?.let { arrayList.add(it) }
        mealDetail.strIngredient18?.let { arrayList.add(it) }
        mealDetail.strIngredient19?.let { arrayList.add(it) }
        mealDetail.strIngredient20?.let { arrayList.add(it) }
        return arrayList
    }

    private fun buildArrayMeasure(mealDetail: MealDetailModel): ArrayList<String>{
        val arrayList: ArrayList<String> = ArrayList()
        mealDetail.strMeasure1?.let { arrayList.add(it) }
        mealDetail.strMeasure2?.let { arrayList.add(it) }
        mealDetail.strMeasure3?.let { arrayList.add(it) }
        mealDetail.strMeasure4?.let { arrayList.add(it) }
        mealDetail.strMeasure5?.let { arrayList.add(it) }
        mealDetail.strMeasure6?.let { arrayList.add(it) }
        mealDetail.strMeasure7?.let { arrayList.add(it) }
        mealDetail.strMeasure8?.let { arrayList.add(it) }
        mealDetail.strMeasure9?.let { arrayList.add(it) }
        mealDetail.strMeasure10?.let { arrayList.add(it) }
        mealDetail.strMeasure11?.let { arrayList.add(it) }
        mealDetail.strMeasure12?.let { arrayList.add(it) }
        mealDetail.strMeasure13?.let { arrayList.add(it) }
        mealDetail.strMeasure14?.let { arrayList.add(it) }
        mealDetail.strMeasure15?.let { arrayList.add(it) }
        mealDetail.strMeasure16?.let { arrayList.add(it) }
        mealDetail.strMeasure17?.let { arrayList.add(it) }
        mealDetail.strMeasure18?.let { arrayList.add(it) }
        mealDetail.strMeasure19?.let { arrayList.add(it) }
        mealDetail.strMeasure20?.let { arrayList.add(it) }
        return arrayList
    }

    private fun setIngredientsChips(
        ingredientsArrayList: ArrayList<String>,
        chipsMeasure: ArrayList<String>,
        chipsIngredients: ChipGroup
    ) {
        chipsIngredients.removeAllViews()
        for (ingredient in ingredientsArrayList.withIndex().iterator()) {
            if(ingredient.value.isNotEmpty() && chipsMeasure[ingredient.index].isNotEmpty()){
                val mChip = this.layoutInflater.inflate(R.layout.item_chip_ingredient, null, false) as Chip
                mChip.text = chipsMeasure[ingredient.index] + " " + ingredient.value
                mChip.textSize = 12.8F
                chipsIngredients.addView(mChip)
            }
        }
    }
}