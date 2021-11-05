package com.test.spaceapp.ui.fragments.mainFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.terrakok.cicerone.Router
import com.test.spaceapp.SpaceApp
import com.test.spaceapp.databinding.MainFragmentListBinding
import com.test.spaceapp.domain.models.Photos
import com.test.spaceapp.ui.activity.NavigationKeys
import moxy.MvpAppCompatFragment
import javax.inject.Inject

class FragmentMain : MvpAppCompatFragment(), MainView {

    private lateinit var binding: MainFragmentListBinding

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var presenter: MainPresenter

    val navigationKey: String
        get() = NavigationKeys.MAIN_TAB_FRAGMENT

    companion object {
        fun newInstance(bundle: Bundle? = null) = FragmentMain().apply { arguments = bundle }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        SpaceApp.INSTANCE.appComponent.inject(this)
        binding = MainFragmentListBinding.inflate(layoutInflater)
        Log.d("RoverPhotos", "onCreateView")
        Log.d("RoverPhotos", "onCreateView")
        return binding.root

    }

    override fun showException(errorMessage: String) {
        TODO("Not yet implemented")
    }

    override fun showPhotos(photos: Photos) {
        Log.d("RoverPhotos", "RoverPhotos + $photos")
        //todo added data to adapter
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        presenter.getPhotos("2021-10-30", 1, "iqz2OJREH35Wnos1NV50CeEgB2tLWSyfCMSG2vaV")
    }
}