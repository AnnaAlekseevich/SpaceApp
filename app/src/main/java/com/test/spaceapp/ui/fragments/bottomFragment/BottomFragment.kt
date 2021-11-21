package com.test.spaceapp.ui.fragments.bottomFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.test.spaceapp.R
import com.test.spaceapp.databinding.BottomSheetBinding
import com.test.spaceapp.domain.models.mapmarkers.MarkerLand
import com.test.spaceapp.ui.fragments.mapFragment.MarkersListAdapter

private const val COLLAPSED_HEIGHT = 228

class BottomFragment : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetBinding
    private lateinit var markersListAdapter: MarkersListAdapter
    override fun getTheme() = R.style.AppBottomSheetDialogTheme
    private var markerLandList: MutableList<MarkerLand> = mutableListOf()
    private lateinit var location: LatLng


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetBinding.inflate(layoutInflater)
        setMarkersData()
        Log.d("bottomSheet", "onCreateView")
        markersListAdapter = MarkersListAdapter(markersLandList = markerLandList)
        binding.markersRecyclerView.adapter = markersListAdapter
        return binding.root
    }

    private fun setMarkersData() {
        Log.d("bottomSheet", "setMarkersData")
        markerLandList.add(MarkerLand("first", 1.1, 2.2))
        markerLandList.add(MarkerLand("second", 2.2, 3.3))
        markerLandList.add(MarkerLand("third", 3.3, 4.4))
    }

    override fun onStart() {
        super.onStart()
        // Плотность понадобится нам в дальнейшем
        val density = requireContext().resources.displayMetrics.density
        Log.d("bottomSheet", "onStart")
        dialog?.let {
            // Находим сам bottomSheet и достаём из него Behaviour
            val bottomSheet =
                it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)

            // Выставляем высоту для состояния collapsed и выставляем состояние collapsed
            behavior.peekHeight = (COLLAPSED_HEIGHT * density).toInt()
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    // Нам не нужны действия по этому колбеку
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    Log.d("bottomSheet", "onSlide")
                    with(binding) {
                        // Нас интересует только положительный оффсет, тк при отрицательном нас устроит стандартное поведение - скрытие фрагмента
                        if (slideOffset > 0) {
                            // Делаем "свёрнутый" layout более прозрачным
                            layoutBottomSheet.alpha = 1 - 2 * slideOffset
                            // И в то же время делаем "расширенный layout" менее прозрачным
                            layoutBottomSheet.alpha = slideOffset * slideOffset

//                            // Когда оффсет превышает половину, мы скрываем collapsed layout и делаем видимым expanded
//                            if (slideOffset > 0.5) {
//                                layoutBottomSheet.visibility = View.GONE
//                                layoutBottomSheet.visibility = View.VISIBLE
//                            }
//
//                            // Если же оффсет меньше половины, а expanded layout всё ещё виден, то нужно скрывать его и показывать collapsed
//                            if (slideOffset < 0.5 && binding.layoutBottomSheet.visibility == View.VISIBLE) {
//                                layoutBottomSheet.visibility = View.VISIBLE
//                                layoutBottomSheet.visibility = View.INVISIBLE
//                            }
                        }
                    }
                }
            })
        }
    }
}