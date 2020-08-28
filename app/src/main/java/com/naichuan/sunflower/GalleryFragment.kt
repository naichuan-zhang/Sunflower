package com.naichuan.sunflower

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.naichuan.sunflower.adapter.GalleryAdapter
import com.naichuan.sunflower.databinding.FragmentGalleryBinding
import com.naichuan.sunflower.utils.InjectorUtils
import com.naichuan.sunflower.viewmodels.GalleryViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GalleryFragment : BaseFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {

    private val adapter = GalleryAdapter()
    private val args: GalleryFragmentArgs by navArgs()
    private var searchJob: Job? = null
    private val viewModel: GalleryViewModel by viewModels {
        InjectorUtils.provideGalleryViewModelFactory()
    }

    override fun initBinding(binding: FragmentGalleryBinding): View? {
        binding.photoList.adapter = adapter
        search(args.plantName)
        binding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
        return binding.root
    }

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchPhotos(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}