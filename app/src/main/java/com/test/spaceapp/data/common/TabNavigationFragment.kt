//package com.test.spaceapp.data.common
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.FragmentActivity
//import com.github.terrakok.cicerone.Cicerone
//import com.github.terrakok.cicerone.Navigator
//import com.github.terrakok.cicerone.Router
//import com.test.spaceapp.R
//import com.test.spaceapp.SpaceApp
//import moxy.MvpAppCompatFragment
//import javax.inject.Inject
//
//
//abstract class TabNavigationFragment : MvpAppCompatFragment() {
//
//    @Inject
//    lateinit var cicerone: Cicerone<Router>
//
//    @Inject
//    lateinit var navigator: Navigator
//
//    abstract val navigationKey: String
//
//    //all the sceneComponents will depend on this component. it allows the same instance of cicerone
//    //to be injected in all the scenes of a flow, which allows a different backstack per tab.
//    //notice we need to pass the fragmentManager and the inner containerId to the module, which will
//    //create the navigator for this flow.
////    val component: FlowComponent? by lazy {
////        context?.let {
////            DaggerFlowComponent.builder()
////                .applicationComponent(SpaceApp.daggerComponent)
////                .flowModule(
////                    FlowModule(
////                        activity as FragmentActivity,
////                        childFragmentManager,
////                        R.id.sceneContainer
////                    )
////                )
////                .build()
////        }
////    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        //component?.inject(this)
//
//        return inflater.inflate(R.layout.fragment_tab_navigation, container, false)
//            ?: super.onCreateView(inflater, container, savedInstanceState)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        cicerone.getNavigatorHolder().setNavigator(navigator)
//    }
//
//    override fun onPause() {
//        cicerone.getNavigatorHolder().removeNavigator()
//        super.onPause()
//    }
//
//    //sends backPressed events from Main Activity to child fragments
//
//
//}