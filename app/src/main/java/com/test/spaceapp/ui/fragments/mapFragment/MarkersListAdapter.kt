package com.test.spaceapp.ui.fragments.mapFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.spaceapp.R
import com.test.spaceapp.databinding.ItemMarkerListBinding
import com.test.spaceapp.domain.models.mapmarkers.MarkerLand

class MarkersListAdapter(
    val markersLandList: List<MarkerLand>,
    //private val itemClickListener: (markerLand: MarkerLand) -> Unit
) : RecyclerView.Adapter<MarkersListAdapter.MarkersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkersViewHolder {
        return MarkersViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_marker_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MarkersViewHolder, position: Int) {
        val markers: MarkerLand = markersLandList.get(position)
        holder.updateLandMarker(markers)
    }

    override fun getItemCount(): Int {
        return markersLandList.size
    }

    inner class MarkersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemMarkerListBinding.bind(itemView)

        fun updateLandMarker(markerLand: MarkerLand) {
            binding.markerName.text = markerLand.markerName
            binding.latitudeData.text = markerLand.latitude.toString()
            binding.longitudeData.text = markerLand.longitude.toString()
//            binding.clItemMarker.setOnClickListener {
//                itemClickListener.invoke(markerLand)
//            }
        }
    }
}