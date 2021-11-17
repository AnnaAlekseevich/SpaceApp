package com.test.spaceapp.ui.activity.mainActivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.google.android.gms.maps.SupportMapFragment
import com.test.spaceapp.R
import com.test.spaceapp.SpaceApp
import com.test.spaceapp.databinding.ActivityMainBinding
import com.test.spaceapp.ui.common.BackButtonListener
import com.test.spaceapp.ui.common.RouterProvider
import com.test.spaceapp.ui.fragments.mainFragment.FragmentMain
import com.test.spaceapp.ui.fragments.mapFragment.FragmentMap
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainActivityView, RouterProvider {

    private lateinit var binding: ActivityMainBinding

    @Inject
    override lateinit var router: Router

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    @ProvidePresenter
    fun createBottomNavigationPresenter() = MainActivityPresenter(router)

    override fun onCreate(savedInstanceState: Bundle?) {
        SpaceApp.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.d("BottomNavigation", "ActivityMainBinding ")
        setFragment(FragmentMain())
        setContentView(binding.root)
        setupNavigationBar()
    }

    fun setFragment(fr: Fragment) {
        if (fr!= null){
            Log.d("BottomNavigation", "setFragment")
            val frag = supportFragmentManager.beginTransaction()
            frag.replace(R.id.main_container, fr)
            frag.commit()
        }
    }

    private fun setupNavigationBar() {
        binding.bottomNavigationBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab_main -> setFragment(FragmentMain()) //envia comandos para o navigator
                R.id.tab_map -> setFragment(FragmentMap())
            }
            true
        }
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        var fragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                fragment = f
                break
            }
        }
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        } else {
            presenter.onBackPressed()
        }
    }

}