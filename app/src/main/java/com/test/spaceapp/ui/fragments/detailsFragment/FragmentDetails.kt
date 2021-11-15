package com.test.spaceapp.ui.fragments.detailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.spaceapp.R
import com.test.spaceapp.SpaceApp
import com.test.spaceapp.domain.models.RoverPhoto
import com.test.spaceapp.ui.fragments.mainFragment.MainPresenter
import com.test.spacedemoapp.data.repositories.RoverPhotosRepository
import io.reactivex.Observable
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class FragmentDetails: MvpAppCompatFragment(), DetailsView  {
//    private lateinit var binding: Binding FragmentMainBinding
//    private lateinit var photoListAdapter: RoverPhotosListAdapter

    @Inject
    lateinit var repository: RoverPhotosRepository

    @Inject
    lateinit var internetStateObservable: Observable<Boolean>

    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun provideDetailsPresenter(): DetailsPresenter? {
        return DetailsPresenter(repository, internetStateObservable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        SpaceApp.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        //binding = FragmentMainBinding.inflate(layoutInflater)
//
//        setupPhotoList()
////        photoListAdapter.addLoadStateListener { loadStates ->
////            if (loadStates.refresh == LoadState.Loading) {
////                showProgress()
////            } else {
////                hideProgress()
////            }
////        }
////        return binding.root
//
//    }

    private fun setupPhotoList() {
//        photoListAdapter = RoverPhotosListAdapter() { photo ->
//            openDetailsScreen(photo.urlItemPhoto, photo.roverCamera.fullName, photo.rover.name)
//        }
//        binding.photosRecyclerView.apply {
//            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//            adapter = photoListAdapter
//        }
//        binding.photosRecyclerView.addItemDecoration(
//            ItemDecorationColumns(
//                resources.getInteger(R.integer.photo_list_preview_columns),
//                resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
//                true
//            )
//        )
    }

    override fun resetPhotosList() {
        //photoListAdapter?.refresh()
    }

    override fun showInternetConnectionError() {
        view?.let {
            Snackbar.make(it, R.string.check_internet_connection, Snackbar.LENGTH_LONG)
                .setAction(R.string.OK, View.OnClickListener { /*Take Action*/ }).show()
        }
    }

    override fun showProgress() {
        //binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        //binding.progressBar.visibility = View.GONE
    }

    override fun setPagingData(pagingData: PagingData<RoverPhoto>) {
//        photoListAdapter.submitData(lifecycle, pagingData)
//        photoListAdapter.notifyDataSetChanged()
    }

    override fun openDetailsScreen(
        photoForDetails: String,
        cameraName: String,
        roverName: String
    ) {
//        val launchIntent = Intent(context, DetailsActivity::class.java)
//        with(launchIntent) {
//            putExtra("photo", photoForDetails)
//            putExtra("CameraName", cameraName)
//            putExtra("RoverName", roverName)
//        }
//        startActivity(launchIntent)
    }
}