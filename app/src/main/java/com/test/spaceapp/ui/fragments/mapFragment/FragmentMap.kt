package com.test.spaceapp.ui.fragments.mapFragment

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.test.spaceapp.R
import com.test.spaceapp.SpaceApp
import com.test.spaceapp.databinding.MapFragmentBinding
import com.test.spaceapp.domain.models.mapmarkers.MarkerLand
import com.test.spaceapp.ui.activity.NavigationKeys
import com.test.spaceapp.ui.fragments.bottomFragment.BottomFragment
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FragmentMap : MvpAppCompatFragment(), MapView, OnMapReadyCallback {

    private lateinit var binding: MapFragmentBinding
    private lateinit var gMap: GoogleMap
    private lateinit var markerLand: MarkerLand
    private lateinit var markerLandList: MutableList<MarkerLand>
    private lateinit var location: LatLng
    private lateinit var markersListAdapter: MarkersListAdapter

    @InjectPresenter
    lateinit var presenter: MapPresenter

    @ProvidePresenter
    fun createBottomNavigationPresenter() = MapPresenter()

    val navigationKey: String
        get() = NavigationKeys.MAP_TAB_FRAGMENT

    companion object {
        fun newInstance(bundle: Bundle? = null) = FragmentMap().apply { arguments = bundle }
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
        binding = MapFragmentBinding.inflate(layoutInflater)
        setupMarkersList()
        BottomFragment().show(childFragmentManager, "tag")
        return binding.root
    }

    private fun setupMarkersList() {
//        markersListAdapter = MarkersListAdapter()
//        binding. .apply {
//            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//            adapter = photoListAdapter
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        Log.d("MAPMARKER", "onMapReady")
        gMap = googleMap

        gMap.setOnMapClickListener { latlng -> // Clears the previously touched position
            //gMap.clear()
            //todo add new markers
            // Animating to the touched position
            gMap.animateCamera(CameraUpdateFactory.newLatLng(latlng))

            location = LatLng(latlng.latitude, latlng.longitude)
            showMarkerNameDialogInput(location)
            gMap.addMarker(
                MarkerOptions()
                    .position(location)
                    .icon(
                        BitmapDescriptorFactory.fromBitmap(
                            AppCompatResources.getDrawable(
                                requireContext(),
                                R.drawable.icon_marker
                            )!!.toBitmap()
                        )
                    )
                    .title("some")
            )
        }
//        gMap.setOnMapLongClickListener {
//            showMarkerNameDialogInput(location)
//            Log.d("MAPMARKER", "Dialog")
//            true
//        }
        Log.d("MAPMARKER", "setOnMapClickListener")
    }


    private fun showMarkerNameDialogInput(location: LatLng) {
        //todo change it -
        Log.d("MAPMARKER", "showMarkerNameDialogInput")
        val builder = AlertDialog.Builder(requireContext())
        val input = EditText(context);
        input.setHeight(100);
        input.setWidth(340);
        input.setGravity(Gravity.LEFT);

        input.setImeOptions(EditorInfo.IME_ACTION_DONE)
        builder.setView(input)

        // add Save and Cancel buttons
        builder.setPositiveButton(resources.getString(R.string.save)) { dialog, which ->
            //todo save markers
            markerLand = MarkerLand(
                markerName = input.toString(),
                latitude = location.latitude,
                longitude = location.longitude
            )
            markerLandList = arrayListOf()
            markerLandList.add(markerLand)
            Log.d("CheckMarker", "marker = $markerLand")
        }
        builder.setNegativeButton(resources.getString(R.string.cancel)) { dialog, which -> gMap.clear() }
        // create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }

}