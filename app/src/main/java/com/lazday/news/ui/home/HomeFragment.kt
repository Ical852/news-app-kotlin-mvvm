package com.lazday.news.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lazday.news.databinding.CustomToolbarBinding
import com.lazday.news.databinding.FragmentHomeBinding
import com.lazday.news.source.news.ArticleModel
import com.lazday.news.source.news.CategoryModel
import com.lazday.news.ui.detail.DetailActivity
import com.lazday.news.ui.news.CategoryAdapter
import com.lazday.news.ui.news.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingToolbar.textTitle.text = viewModel.title

        binding.listCategory.adapter = categoryAdapter
        viewModel.category.observe(viewLifecycleOwner, Observer {
            viewModel.fetch(it)
        })
        newsInit()
        loadingInit()
        messageInit()
    }

    private val categoryAdapter by lazy {
        CategoryAdapter(viewModel.categries, object : CategoryAdapter.OnAdapterListener{
            override fun onClick(category: CategoryModel) {
                viewModel.category.postValue(category.id)
            }
        })
    }

    private val newsAdapter by lazy {
        NewsAdapter(arrayListOf(), object : NewsAdapter.OnAdapterListener{
            override fun onClick(article: ArticleModel) {
                startActivity(
                    Intent(requireActivity(), DetailActivity::class.java)
                        .putExtra("intent_detail", article)
                )
            }
        })
    }

    fun newsInit() {
        binding.listNews.adapter = newsAdapter
        viewModel.news.observe(viewLifecycleOwner, Observer {
            if (viewModel.loading.value!! == false) {
                binding.imageAlert.visibility = if (it.articles.isEmpty()) View.VISIBLE else View.GONE
                binding.textAlert.visibility = if (it.articles.isEmpty()) View.VISIBLE else View.GONE
            }
            newsAdapter.add(it.articles)
        })
    }

    fun loadingInit() {
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBottom.visibility = View.VISIBLE
                binding.progressTop.visibility = View.VISIBLE
            } else {
                binding.progressBottom.visibility = View.GONE
                binding.progressTop.visibility = View.GONE
            }
        })
    }

    fun messageInit() {
        viewModel.message.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })
    }
}