package com.lazday.news.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lazday.news.databinding.CustomToolbarBinding
import com.lazday.news.databinding.FragmentBookmarkBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val bookmarkModule = module {
    factory { BookmarkFragment() }
}

class BookmarkFragment : Fragment() {
    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val bookmarkViewModel: BookmarkViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(layoutInflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingToolbar.textTitle.text = bookmarkViewModel.title
    }
}