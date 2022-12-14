package ru.dw.mvp.view.fragment.detailsfork

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter
import ru.dw.mvp.MyApp
import ru.dw.mvp.core.OnBackPressedListener
import ru.dw.mvp.databinding.FragmentForkDetailsBinding
import ru.dw.mvp.model.entity.GithubReposUser
import ru.dw.mvp.presenter.DetailsForkPresenter


class DetailsForkFragment :
    MvpAppCompatFragment(),
    MvpView,
    OnBackPressedListener{

    private var _binding: FragmentForkDetailsBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentUserDetailsBinding = null ")

    private val presenter: DetailsForkPresenter by moxyPresenter {
        DetailsForkPresenter(
            MyApp.instance.router
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForkDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<GithubReposUser>(BUNDLE_DETAILS_FORK)?.let {
            binding.fokSize.text = it.forksCount.toString()
        }

    }


    companion object {

        private const val BUNDLE_DETAILS_FORK = "BUNDLE_DETAILS_FORK"
        private fun bundleDetails(githubReposUser: GithubReposUser): Bundle {
            return Bundle().apply {
                putParcelable(BUNDLE_DETAILS_FORK, githubReposUser)
            }
        }

        @JvmStatic
        fun newInstance(githubForkUser: GithubReposUser) =
            DetailsForkFragment().apply {
                arguments = bundleDetails(githubForkUser)
            }
    }

    override fun onBackPressed(): Boolean = presenter.onBackPressed()


}