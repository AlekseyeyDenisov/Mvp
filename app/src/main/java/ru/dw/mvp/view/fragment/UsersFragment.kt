package ru.dw.mvp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.dw.mvp.MyApp
import ru.dw.mvp.core.OnBackPressedListener
import ru.dw.mvp.databinding.FragmentUsersListBinding
import ru.dw.mvp.model.GithubUser
import ru.dw.mvp.presenter.UserPresenter
import ru.dw.mvp.repository.GitHubRepositoryImpl
import ru.dw.mvp.view.recycler.UserAdapter


class UsersFragment : MvpAppCompatFragment(), UserView, OnBackPressedListener {

    companion object {
        @JvmStatic
        fun newInstance() = UsersFragment()

    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            GitHubRepositoryImpl(),
            MyApp.instance.router
        )
    }

    private var _binding: FragmentUsersListBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentUsersListBinding = null ")

    private val userAdapter = UserAdapter()


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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onBackPressed() = presenter.onBackPressed()
}