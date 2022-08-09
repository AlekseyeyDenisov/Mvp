package ru.dw.mvp.view.fragment.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpView
import ru.dw.mvp.R
import ru.dw.mvp.databinding.FragmentUserDetailsBinding
import ru.dw.mvp.databinding.FragmentUsersListBinding
import ru.dw.mvp.model.GithubUser


class UserDetailsFragment : Fragment(), MvpView {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentUserDetailsBinding = null ")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val githubUser = arguments?.getParcelable<GithubUser>(BUNDLE_KEY)
        render(githubUser)
    }

    private fun render(githubUser: GithubUser?) {
        githubUser?.let {
            binding.userLogin.text = githubUser.login
        }
    }

    companion object {
        private const val BUNDLE_KEY = "key_details_fragment"
        fun bundleDetails(githubUser: GithubUser):Bundle {
            return Bundle().apply {
                putParcelable(BUNDLE_KEY,githubUser)
            }
        }

        @JvmStatic
        fun newInstance(bundle: Bundle) =
            UserDetailsFragment().apply {
                arguments = Bundle().apply {
                    arguments = bundle
                }
            }
    }
}