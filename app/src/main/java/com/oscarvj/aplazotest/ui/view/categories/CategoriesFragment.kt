package com.oscarvj.aplazotest.ui.view.categories

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.oscarvj.aplazotest.R
import com.oscarvj.aplazotest.data.model.CategoryModel
import com.oscarvj.aplazotest.data.model.MealDetailModel
import com.oscarvj.aplazotest.databinding.FragmentCategoriesBinding
import com.oscarvj.aplazotest.di.manager.NavigationManager
import com.oscarvj.aplazotest.listener.ItemListener
import com.oscarvj.aplazotest.ui.view.categories.adapter.CategoriesAdapter
import com.oscarvj.aplazotest.ui.view.main.MainActivity
import com.oscarvj.aplazotest.ui.view.meals.detail.DetailMealFragment
import com.oscarvj.aplazotest.ui.viewmodel.categories.CategoriesViewModel
import com.oscarvj.aplazotest.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesFragment : Fragment(), ItemListener {

    private lateinit var binding: FragmentCategoriesBinding

    private val categoriesViewModel: CategoriesViewModel by viewModels()

    private lateinit var adapter: CategoriesAdapter

    private lateinit var randomMealModel: MealDetailModel

    @Inject
    lateinit var navigation: NavigationManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapterCategories()
        getAllData()
        initObservers()
        initListener()
    }

    private fun initAdapterCategories() {
        adapter = CategoriesAdapter(this)
        binding.rvCategories.layoutManager = GridLayoutManager(context, 2)
        binding.rvCategories.setHasFixedSize(true)
        binding.rvCategories.adapter = adapter
    }

    private fun getAllData(){
        categoriesViewModel.getAllCategories()
        categoriesViewModel.getRandomMeal()
    }

    private fun initObservers(){
        categoriesViewModel.categoriesModel.observe(this, Observer {
            if(it != null){
                adapter.addData(it.categories)
            }
        })

        categoriesViewModel.randomMealModel.observe(this, Observer {
            if(it != null){
                randomMealModel = it.meals[0]

                binding.tvCardMeal.text = it.meals[0].strMeal

                Glide.with(this)
                    .load(it.meals[0].strMealThumb)
                    .circleCrop()
                    .into(binding.imageViewMeal)
            }
        })

        categoriesViewModel.mealByNameModel.observe(this, Observer {
            if(it != null){
                randomMealModel = it.meals[0]

                binding.tvCardMeal.text = it.meals[0].strMeal

                Glide.with(this)
                    .load(it.meals[0].strMealThumb)
                    .circleCrop()
                    .into(binding.imageViewMeal)
            }else{
                Toast.makeText(context, "NO meals found", Toast.LENGTH_SHORT).show()
            }
        })

        categoriesViewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })
    }

    private fun initListener(){
        binding.btnSearchMeal.setOnClickListener {
            if(binding.etMeal.text.toString().trim().isNullOrEmpty()){
                Toast.makeText(context, "Enter a name", Toast.LENGTH_SHORT).show()
            }else{
                binding.root.hideKeyboard()
                categoriesViewModel.getMealByName(binding.etMeal.text.toString().trim())
            }
        }
        binding.textViewDetails.setOnClickListener {
            binding.etMeal.setText("")
            val bundle = Bundle()
            bundle.putBoolean("isFromCategories", true)
            bundle.putSerializable("randomMealModel", randomMealModel)
            navigation.onNavigate(view, R.id.action_detail_meals_category_Fragment, bundle)
        }
    }

    override fun onItemSelected(objects: Any) {
        binding.etMeal.setText("")
        navigation.onNavigate(view, R.id.action_detailCategoryFragment, buildArguments(objects as CategoryModel))
    }

    private fun buildArguments(categoryModel: CategoryModel): Bundle{
        val bundle = Bundle()
        bundle.putSerializable("category", categoryModel)
        return bundle
    }

}