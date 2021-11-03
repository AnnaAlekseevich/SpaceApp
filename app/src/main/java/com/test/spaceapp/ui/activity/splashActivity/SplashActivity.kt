package com.test.spaceapp.ui.activity.splashActivity

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.test.spaceapp.R
import com.test.spaceapp.SpaceApp
import com.test.spaceapp.data.common.Screens
import com.test.spaceapp.databinding.SplashActivityBinding
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class SplashActivity : MvpAppCompatActivity(), SplashActivityView {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @InjectPresenter
    lateinit var presenter: SplashActivityPresentor

    private val navigator: Navigator = AppNavigator(this, -1)
    private lateinit var rocketAnimation: AnimatedVectorDrawable
    private lateinit var binding: SplashActivityBinding
    private lateinit var iv: ImageView

    @ProvidePresenter
    fun createStartActivityPresenter() = SplashActivityPresentor(router)

    override fun onCreate(savedInstanceState: Bundle?) {
        SpaceApp.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.splashImageAnim.apply {
            setBackgroundResource(R.drawable.splash_screen_anim)
            rocketAnimation = background as AnimatedVectorDrawable
            rocketAnimation.start()
        }
        Handler(Looper.getMainLooper()).postDelayed({
            navigator.applyCommands(arrayOf<Command>(Replace(Screens.MainActivity())))
        }, 3000)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

}