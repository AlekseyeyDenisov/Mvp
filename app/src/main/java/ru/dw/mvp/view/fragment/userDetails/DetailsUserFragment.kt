package ru.dw.mvp.view.fragment.userDetails

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import moxy.MvpAppCompatFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter
import ru.dw.mvp.MyApp
import ru.dw.mvp.core.utils.makeGone
import ru.dw.mvp.core.utils.makeVisible
import ru.dw.mvp.data.network.NetworkProvider
import ru.dw.mvp.databinding.FragmentUserDetailsBinding
import ru.dw.mvp.model.entity.GithubRepo
import ru.dw.mvp.model.entity.GithubUser
import ru.dw.mvp.presenter.OnBackPressedListener
import ru.dw.mvp.repository.impl.GithubRepositoryImpl
import ru.dw.mvp.view.fragment.userDetails.recycler.ForkDetailsAdapter
import ru.dw.mvp.view.fragment.userDetails.recycler.OnItemClickForkListener
import javax.inject.Inject


class DetailsUserFragment :
    MvpAppCompatFragment(),
    DetailsView,
    MvpView,
    OnBackPressedListener,
    OnItemClickForkListener {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentUserDetailsBinding = null ")

    @Inject
    lateinit var  presenter: DetailsPresenter

    private val forkAdapter = ForkDetailsAdapter(this)

    private val component by lazy {
        (requireActivity().application as MyApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
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
        initRecycler()
        if (Build.VERSION.SDK_INT >= 33) {
            arguments?.getParcelable(BUNDLE_DETAILS,GithubUser::class.java)?.let {
                presenter.loadForks(it.login)
                showUserData(it)
            }
        }else {
            arguments?.getParcelable<GithubUser>(BUNDLE_DETAILS)?.let {
                presenter.loadForks(it.login)
                showUserData(it)
            }
        }

    }

    private fun initRecycler() {
        with(binding) {
            recyclerGitHubFork.layoutManager = LinearLayoutManager(requireContext())
            recyclerGitHubFork.adapter = forkAdapter
        }
    }

    private fun showUserData(githubUser: GithubUser) {
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


    override fun show(githubRepo: List<GithubRepo>) {
        forkAdapter.forks = githubRepo
    }

    override fun showLoading() {
        binding.progressBar.makeVisible()

    }

    override fun hideLoading() {
        binding.progressBar.makeGone()
    }

    override fun onItemClick(githubForkUser: GithubRepo) {
        presenter.showDetailsFork(githubForkUser)
    }
}