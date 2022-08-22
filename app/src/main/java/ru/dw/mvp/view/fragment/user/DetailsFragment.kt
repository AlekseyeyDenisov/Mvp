package ru.dw.mvp.view.fragment.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import moxy.MvpAppCompatFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter
import ru.dw.mvp.MyApp
import ru.dw.mvp.core.OnBackPressedListener
import ru.dw.mvp.core.network.NetworkProvider
import ru.dw.mvp.databinding.FragmentUserDetailsBinding
import ru.dw.mvp.model.GithubUser
import ru.dw.mvp.presenter.DetailsPresenter
import ru.dw.mvp.repository.GithubRepositoryImpl


class DetailsFragment :
    MvpAppCompatFragment(),
    DetailsView,
    MvpView,
    OnBackPressedListener {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentUserDetailsBinding = null ")

    private val presenter: DetailsPresenter by moxyPresenter {
        DetailsPresenter(
            GithubRepositoryImpl(NetworkProvider.usersApi),
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
        arguments?.getParcelable<GithubUser>(BUNDLE_DETAILS)?.let {
            presenter.loadUser(it.login)
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
            DetailsFragment().apply {
                arguments = bundleDetails(githubUser)
            }
    }

    override fun onBackPressed(): Boolean = presenter.onBackPressed()

    override fun show(githubUser: GithubUser) {
        binding.ivUserAvatar.load(githubUser.avatarUrl)
        binding.userLogin.text = githubUser.login

    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }
}