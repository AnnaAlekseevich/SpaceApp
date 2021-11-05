package com.test.spaceapp.ui.fragments.mapFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.spaceapp.SpaceApp
import com.test.spaceapp.databinding.MapFragmentBinding
import com.test.spaceapp.ui.activity.NavigationKeys
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FragmentMap : MvpAppCompatFragment(), MapView {

    private lateinit var binding: MapFragmentBinding

    @InjectPresenter
    lateinit var presenter: MapPresenter

    @ProvidePresenter
    fun createBottomNavigationPresenter() = MapPresenter()

    val navigationKey: String
        get() = NavigationKeys.MAP_TAB_FRAGMENT

    companion object {
        fun newInstance(bundle: Bundle? = null) = FragmentMap().apply { arguments = bundle }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        SpaceApp.INSTANCE.appComponent.inject(this)
        binding = MapFragmentBinding.inflate(layoutInflater)

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        if (childFragmentManager.findFragmentById(R.id.sceneContainer) == null) {
//            cicerone.router.replaceScreen(Screens.Map())
//        }
    }

}