package com.test.spaceapp.ui.fragments.detailsFragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.github.terrakok.cicerone.Router
import com.google.android.material.snackbar.Snackbar
import com.test.spaceapp.R
import com.test.spaceapp.SpaceApp
import com.test.spaceapp.databinding.FragmentDetailsBinding
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class FragmentDetails : MvpAppCompatFragment(), DetailsView {

    private lateinit var binding: FragmentDetailsBinding

    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun createDetailsNavigationPresenter() = DetailsPresenter(router)

    override fun onCreate(savedInstanceState: Bundle?) {
        SpaceApp.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
//        val intent= intent
//
//        val photoImage: String = intent.getStringExtra("photo").toString()
//        val roverCameraName: String = intent.getStringExtra("CameraName").toString()
//        val roverFullName: String = intent.getStringExtra("RoverName").toString()
        val photoImage = ""
        binding.btnBack.setOnClickListener { back ->
//            activity?.finish()
            presenter.onBackPressed()
        }
        binding.btnShare.setOnClickListener { share ->
            onShareImage()
        }

        showRoverPhoto(photoImage)
        return binding.root
    }

    override fun showRoverPhoto(pictureUri: String) {
        pictureUri.toUri().let {
            Glide
                .with(binding.roverPhotoDetails.context)
                .load(it)
                .into(binding.roverPhotoDetails)
        }
    }

    //for converting String to Uri
    private fun String.toUri(): Uri = Uri.parse(this)

    private fun onShareImage() {
        var bitmap: Bitmap = (binding.roverPhotoDetails.drawable as BitmapDrawable).bitmap
        shareImageToAnotherApp(bitmap)
    }

    private fun shareImageToAnotherApp(bitmap: Bitmap) {
        val uri: Uri = getmageToShare(bitmap)!!
        val intent = Intent(Intent.ACTION_SEND)
        // putting uri of image to be shared
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        // adding text to share
        intent.putExtra(Intent.EXTRA_TEXT, "Sharing Image")
        // Add subject Here
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
        // setting type to image
        intent.type = "image/png"
        // calling startactivity() to share
        startActivity(Intent.createChooser(intent, "Share Via"))
    }

    // Retrieving the url to share
    private fun getmageToShare(bitmap: Bitmap): Uri? {
        val imagefolder = File(activity?.cacheDir, "images")
        var uri: Uri? = null
        try {
            imagefolder.mkdirs()
            val file = File(imagefolder, "shared_image.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream)
            outputStream.flush()
            outputStream.close()
            uri = context?.let {
                FileProvider.getUriForFile(
                    it,
                    "com.anni.shareimage.fileprovider",
                    file
                )
            }
        } catch (e: Exception) {
            view?.let {
                Snackbar.make(it, "" + e.message, Snackbar.LENGTH_LONG)
                    .setAction(R.string.OK, View.OnClickListener { /*Take Action*/ }).show()
            }
        }
        return uri
    }

}