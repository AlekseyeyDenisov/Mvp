package ru.dw.mvp.view.fragment.picker

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import coil.load
import moxy.MvpAppCompatFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter
import ru.dw.mvp.databinding.FragmentImagePickerBinding
import ru.dw.mvp.presenter.ImagePickerPresenter
import ru.dw.mvp.repository.PickerRepositoryImpl


class ImagePickerFragment :
    MvpAppCompatFragment(),
    MvpView,
    ImagePickerView

{

    private var _binding: FragmentImagePickerBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentImagePickerBinding = null ")

    private val presenter: ImagePickerPresenter by moxyPresenter {
        ImagePickerPresenter(
            PickerRepositoryImpl()
        )
    }

    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it){
                getContent.launch(MIMETYPE_IMAGES)
            }

        }



    private val getContent = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { imageUri: Uri? ->
        imageUri?.let {
            showLoading()
            presenter.getUrlImages(uriPath(getFilePath(requireContext(),it))) { file ->
                binding.image.load(file)
            }

        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagePickerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pickImage.setOnClickListener {
            requestPermissions.launch("android.permission.READ_EXTERNAL_STORAGE")
        }
    }
    private fun getFilePath(context: Context, uri: Uri?): String? {
        var cursor: Cursor? = null
        val projection = arrayOf(MediaStore.MediaColumns.DISPLAY_NAME)
        try {
            if (uri == null) return null
            cursor = context.contentResolver.query(
                uri, projection, null, null,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
                return cursor.getString(index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    private fun uriPath(fileName: String?) = Environment.getExternalStorageDirectory()
        .toString() + DOWNLOADS + fileName

    companion object {
        const val MIMETYPE_IMAGES = "image/*"
        const val DOWNLOADS = "/Download/"

        @JvmStatic
        fun newInstance() = ImagePickerFragment()
    }

    override fun requestUrlImages(url: String) {

    }

    override fun showLoading() {
        binding.progressBarPicker.visibility = View.VISIBLE

    }

    override fun hideLoading() {
        binding.progressBarPicker.visibility = View.GONE

    }


}