package com.oscarvj.aplazotest.ui.view.categories.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.oscarvj.aplazotest.R
import com.oscarvj.aplazotest.data.model.CategoryModel
import com.oscarvj.aplazotest.databinding.FragmentDetailCategoryBinding
import com.oscarvj.aplazotest.di.manager.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailCategoryFragment : Fragment() {

    private lateinit var binding: FragmentDetailCategoryBinding

    private lateinit var category: CategoryModel

    @Inject
    lateinit var navigation: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null){
            category = requireArguments().getSerializable("category") as CategoryModel
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewTitle.text = category.strCategory
        binding.textViewDesc.text = category.strCategoryDescription
        Glide.with(this)
            .load(category.strCategoryThumb)
            .circleCrop()
            .into(binding.imageView)

        initListeners()
    }

    private fun initListeners() {
        binding.textViewMeals.setOnClickListener {
            navigation.onNavigate(view, R.id.action_meals_Fragment, buildArguments(category))
        }

        binding.imageViewBack.setOnClickListener {
            navigation.onNavigateBack()
        }
    }

    private fun buildArguments(categoryModel: CategoryModel): Bundle{
        val bundle = Bundle()
        bundle.putSerializable("category", categoryModel)
        return bundle
    }
}