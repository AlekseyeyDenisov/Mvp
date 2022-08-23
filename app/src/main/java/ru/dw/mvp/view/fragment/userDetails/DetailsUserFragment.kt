package ru.dw.mvp.view.fragment.userDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import moxy.MvpAppCompatFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter
import ru.dw.mvp.MyApp
import ru.dw.mvp.core.OnBackPressedListener
import ru.dw.mvp.core.network.NetworkProvider
import ru.dw.mvp.core.utils.makeGone
import ru.dw.mvp.core.utils.makeVisible
import ru.dw.mvp.databinding.FragmentUserDetailsBinding
import ru.dw.mvp.model.entity.GithubReposUser
import ru.dw.mvp.model.entity.GithubUser
import ru.dw.mvp.presenter.DetailsPresenter
import ru.dw.mvp.repository.GithubRepositoryImpl
import ru.dw.mvp.view.fragment.userDetails.recycler.ForkDetailsAdapter
import ru.dw.mvp.view.fragment.userDetails.recycler.OnItemClickForkListener


class DetailsUserFragment :
    MvpAppCompatFragment(),
    DetailsView,
    MvpView,
    OnBackPressedListener,
    OnItemClickForkListener {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentUserDetailsBinding = null ")

    private val presenter: DetailsPresenter by moxyPresenter {
        DetailsPresenter(
            GithubRepositoryImpl(NetworkProvider.usersApi),
            MyApp.instance.router
        )
    }

    private val forkAdapter = ForkDetailsAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        arguments?.getParcelable<GithubUser>(BUNDLE_DETAILS)?.let {
            presenter.loadForks(it.login)
            showUserData(it)
        }

    }

    private fun initRecycler() {
        with(binding) {
            recyclerGitHubFork.layoutManager = LinearLayoutManager(requireContext())
            recyclerGitHubFork.adapter = forkAdapter
        }
    }

    private fun showUserData(githubUser: GithubUser){
        binding.ivUserAvatar.load(githubUser.avatarUrl)
        binding.userLogin.text = githubUser.login
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
            DetailsUserFragment().apply {
                arguments = bundleDetails(githubUser)
            }
    }

    override fun onBackPressed(): Boolean = presenter.onBackPressed()



    override fun show(githubReposUser: List<GithubReposUser>) {
        forkAdapter.forks = githubReposUser
    }

    override fun showLoading() {
        binding.progressBar.makeVisible()

    }

    override fun hideLoading() {
        binding.progressBar.makeGone()
    }

    override fun onItemClick(githubForkUser: GithubReposUser) {
        presenter.showDetailsFork(githubForkUser)
    }
}