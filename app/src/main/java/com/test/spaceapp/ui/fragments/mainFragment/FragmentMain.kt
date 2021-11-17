package com.test.spaceapp.ui.fragments.mainFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.google.android.gms.maps.GoogleMap
import com.google.android.material.snackbar.Snackbar
import com.test.spaceapp.R
import com.test.spaceapp.SpaceApp
import com.test.spaceapp.databinding.MainFragmentListBinding
import com.test.spaceapp.domain.models.RoverPhoto
import com.test.spacedemoapp.data.repositories.RoverPhotosRepository
import com.test.spacedemoapp.ui.adapter.ItemDecorationColumns
import com.test.spacedemoapp.ui.adapter.RoverPhotosListAdapter
import io.reactivex.Observable
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class FragmentMain : MvpAppCompatFragment(), MainView {

    private lateinit var binding: MainFragmentListBinding
    private lateinit var photoListAdapter: RoverPhotosListAdapter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var repository: RoverPhotosRepository

    @Inject
    lateinit var internetStateObservable: Observable<Boolean>

    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun provideDetailsPresenter(): MainPresenter? {
        return MainPresenter(repository, internetStateObservable, router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        SpaceApp.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentListBinding.inflate(layoutInflater)

        setupPhotoList()
        photoListAdapter.addLoadStateListener { loadStates ->
            if (loadStates.refresh == LoadState.Loading) {
                showProgress()
            } else {
                hideProgress()
            }
        }
        return binding.root
    }

    private fun setupPhotoList() {
        photoListAdapter = RoverPhotosListAdapter() { photo ->
            openDetailsScreen(photo.urlItemPhoto, photo.roverCamera.fullName, photo.rover.name)
        }
        binding.photosRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = photoListAdapter
        }
        binding.photosRecyclerView.addItemDecoration(
            ItemDecorationColumns(
                resources.getInteger(R.integer.photo_list_preview_columns),
                resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                true
            )
        )
    }

    override fun resetPhotosList() {
        photoListAdapter?.refresh()
    }

    override fun showInternetConnectionError() {
        view?.let {
            Snackbar.make(it, R.string.check_internet_connection, Snackbar.LENGTH_LONG)
                .setAction(R.string.OK, View.OnClickListener { /*Take Action*/ }).show()
        }
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun setPagingData(pagingData: PagingData<RoverPhoto>) {
        photoListAdapter.submitData(lifecycle, pagingData)
        photoListAdapter.notifyDataSetChanged()
    }

    override fun openDetailsScreen(
        photoForDetails: String,
        cameraName: String,
        roverName: String
    ) {
        presenter.onForwardCommandClick()
        Log.d("OpenFragmentDetails", "OpenFragmentDetails")
    }
}