package ru.dw.mvp.view.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import coil.load
import ru.dw.mvp.databinding.FragmentImagePickerBinding


class ImagePickerFragment : Fragment() {

    private var _binding: FragmentImagePickerBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentImagePickerBinding = null ")

    private val getContent = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { imageUri: Uri? ->
        binding.image.load(imageUri)

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
            getContent.launch(MIMETYPE_IMAGES)
        }
    }

    companion object {
        const val MIMETYPE_IMAGES = "image/*"

        @JvmStatic
        fun newInstance() = ImagePickerFragment()
    }
}