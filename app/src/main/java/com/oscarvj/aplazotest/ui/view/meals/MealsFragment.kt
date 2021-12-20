package com.oscarvj.aplazotest.ui.view.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.oscarvj.aplazotest.R
import com.oscarvj.aplazotest.data.model.CategoryModel
import com.oscarvj.aplazotest.data.model.MealModel
import com.oscarvj.aplazotest.databinding.FragmentMealsBinding
import com.oscarvj.aplazotest.di.manager.NavigationManager
import com.oscarvj.aplazotest.listener.ItemListener
import com.oscarvj.aplazotest.ui.view.meals.adapter.MealsAdapter
import com.oscarvj.aplazotest.ui.view.meals.detail.DetailMealFragment
import com.oscarvj.aplazotest.ui.viewmodel.meals.MealsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MealsFragment: Fragment(), ItemListener {

    private lateinit var binding: FragmentMealsBinding

    private lateinit var category: CategoryModel

    private lateinit var adapter: MealsAdapter

    private val mealsViewModel: MealsViewModel by viewModels()

    @Inject
    lateinit var navigation: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            category = requireArguments().getSerializable("category") as CategoryModel
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMealsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewName.text = category.strCategory

        initAdapterMeals()
        getMealsByCategory()
        initObservers()
        initListener()
    }

    private fun initAdapterMeals() {
        adapter = MealsAdapter(this)
        binding.rvMeals.layoutManager = GridLayoutManager(context, 2)
        binding.rvMeals.setHasFixedSize(true)
        binding.rvMeals.adapter = adapter
    }

    private fun getMealsByCategory(){
        mealsViewModel.getMealsByCategory(category.strCategory)
    }

    private fun initObservers(){
        mealsViewModel.mealsModel.observe(this, Observer {
            if(it != null){
                adapter.addData(it.meals)
            }
        })

        mealsViewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })
    }

    private fun initListener(){
        binding.imageViewBack.setOnClickListener {
            navigation.onNavigateBack()
        }
    }

    override fun onItemSelected(objects: Any) {
        navigation.onNavigate(view, R.id.mealsDetailFragment, buildArguments(objects as MealModel))
    }

    private fun buildArguments(mealModel: MealModel): Bundle {
        val bundle = Bundle()
        bundle.putBoolean("isFromCategories", false)
        bundle.putSerializable("mealModel", mealModel)
        return bundle
    }
}