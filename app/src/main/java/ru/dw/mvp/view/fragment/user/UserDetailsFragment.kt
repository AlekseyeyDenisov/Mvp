package ru.dw.mvp.view.fragment.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter
import ru.dw.mvp.MyApp
import ru.dw.mvp.core.OnBackPressedListener
import ru.dw.mvp.databinding.FragmentUserDetailsBinding
import ru.dw.mvp.model.GithubUser
import ru.dw.mvp.presenter.UserDetailsPresenter


class UserDetailsFragment :
    MvpAppCompatFragment(),
    MvpView,
    OnBackPressedListener {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentUserDetailsBinding = null ")

    private val presenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(
            MyApp.instance.router
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val githubUser = arguments?.getParcelable<GithubUser>(BUNDLE_DETAILS)

        render(githubUser)
    }

    private fun render(githubUser: GithubUser?) {
        githubUser?.let {
            binding.userLogin.text = githubUser.login
        }
    }

    companion object {

        private const val BUNDLE_DETAILS = "key_details_fragment"
        private fun bundleDetails(githubUser: GithubUser): Bundle {
            return Bundle().apply {
                putParcelable(BUNDLE_DETAILS, githubUser)
            }
        }

        @JvmStatic
        fun newInstance(githubUser: GithubUser) =
            UserDetailsFragment().apply {
                arguments = bundleDetails(githubUser)
            }
    }

    override fun onBackPressed(): Boolean = presenter.onBackPressed()
}