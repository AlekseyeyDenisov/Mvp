package ru.dw.mvp.view.fragment.users

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.core.Observable
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.dw.mvp.MyApp
import ru.dw.mvp.core.utils.ConnectivityListener
import ru.dw.mvp.data.network.NetworkProvider
import ru.dw.mvp.databinding.FragmentUsersListBinding
import ru.dw.mvp.model.entity.GithubUser
import ru.dw.mvp.presenter.OnBackPressedListener
import ru.dw.mvp.repository.impl.GithubRepositoryImpl
import ru.dw.mvp.view.fragment.users.recycler.OnItemClickUserListener
import ru.dw.mvp.view.fragment.users.recycler.UserAdapter
import javax.inject.Inject


class UsersFragment : MvpAppCompatFragment(),
    UsersView,
    OnBackPressedListener,
    OnItemClickUserListener {

    companion object {
        @JvmStatic
        fun newInstance() = UsersFragment()

    }

    @Inject
    lateinit var presenter: UsersPresenter




    private var _binding: FragmentUsersListBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentUsersListBinding = null ")

    private val userAdapter = UserAdapter(this)

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
        _binding = FragmentUsersListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
    }

    private fun initRecycler() {
        with(binding) {
            recyclerGitHubUser.layoutManager = LinearLayoutManager(requireContext())
            recyclerGitHubUser.adapter = userAdapter
        }
    }

    override fun initList(list: List<GithubUser>) {
        userAdapter.users = list
    }

    override fun showLoading() {
        showProgressBar(true)
    }

    override fun hideLoading() {
        showProgressBar(false)
    }

    private fun showProgressBar(visibility: Boolean) {
        binding.progressBar.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun onItemClick(githubUser: GithubUser) {
        presenter.showDetails(githubUser)

    }
}