package com.test.spaceapp.ui.fragments.splashFragment

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.test.spaceapp.R
import com.test.spaceapp.databinding.FragmentSplashScreenBinding


class FragmentSplashScreen : Fragment(), SplashScreenView {

    private lateinit var rocketAnimation: AnimatedVectorDrawable
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSplashScreenBinding
    private lateinit var iv: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(layoutInflater)

        navController = NavHostFragment.findNavController(this)
        binding.splashImageAnim.apply {
            setBackgroundResource(R.drawable.splash_screen_anim)
            rocketAnimation = background as AnimatedVectorDrawable
            rocketAnimation.start()
        }
        return binding.root

    }
}