package com.test.spaceapp.ui.fragments.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.spaceapp.databinding.MainFragmentListBinding
import com.test.spaceapp.ui.activity.NavigationKeys
import moxy.MvpAppCompatFragment

//this is a standard content fragment, which will be inflated inside a TabNavigationFragment.
class FragmentMain : MvpAppCompatFragment(), MainView {

    private lateinit var binding: MainFragmentListBinding

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
        binding = MainFragmentListBinding.inflate(layoutInflater)
        return binding.root

    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        if (childFragmentManager.findFragmentById(R.id.sceneContainer) == null) {
//            cicerone.router.replaceScreen(Screens.Main())
//        }
//    }

}