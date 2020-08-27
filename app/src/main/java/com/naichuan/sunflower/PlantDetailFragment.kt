package com.naichuan.sunflower

import android.content.Intent
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ShareCompat
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.naichuan.sunflower.data.Plant
import com.naichuan.sunflower.databinding.FragmentPlantDetailBinding
import com.naichuan.sunflower.utils.InjectorUtils
import com.naichuan.sunflower.viewmodels.PlantDetailViewModel

class PlantDetailFragment : BaseFragment<FragmentPlantDetailBinding>(R.layout.fragment_plant_detail) {

    private val args: PlantDetailFragmentArgs by navArgs()

    private val plantDetailViewModel: PlantDetailViewModel by viewModels {
        InjectorUtils.providePlantDetailViewModelFactory(this, args.plantId)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initBinding(binding: FragmentPlantDetailBinding): View? {
        binding.apply {
            viewModel = plantDetailViewModel
            lifecycleOwner = viewLifecycleOwner
//            callback = object : Callback {
//                override fun add(plant: Plant?) {
//                    plant?.let {
//                        hideAppBarFab(fab)
//                        plantDetailViewModel.addPlantToGarden()
//                        Snackbar.make(root, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG).show()
//                    }
//                }
//            }
        }
        binding.toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_share -> {
                    createShareIntent()
                    true
                }
                else -> false
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun createShareIntent() {
        val shareText = plantDetailViewModel.plant.value.let { plant ->
            if (plant == null) {
                ""
            } else {
                getString(R.string.share_text_plant, plant.name)
            }
        }
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setText(shareText)
            .setType("text/plain")
            .createChooserIntent()
            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(shareIntent)
    }

    private fun hideAppBarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }

    interface Callback {
        fun add(plant: Plant?)
    }
}