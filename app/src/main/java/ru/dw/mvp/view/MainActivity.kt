package ru.dw.mvp.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.dw.mvp.databinding.ActivityMainBinding
import ru.dw.mvp.model.GithubUser
import ru.dw.mvp.presenter.GitHubPresenter
import ru.dw.mvp.repository.GitHubRepositoryImpl
import ru.dw.mvp.view.recycler.UserAdapter

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private val userAdapter = UserAdapter()

    private val presenter by moxyPresenter {
        GitHubPresenter(GitHubRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
    }

    private fun initRecycler() {
        with(binding){
            recyclerGitHubUser.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerGitHubUser.adapter = userAdapter
        }
    }


    override fun initList(list: List<GithubUser>) {
        userAdapter.users = list
    }


}
