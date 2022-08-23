package ru.dw.mvp.core.nav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.dw.mvp.model.entity.GithubReposUser
import ru.dw.mvp.model.entity.GithubUser
import ru.dw.mvp.view.fragment.detailsfork.DetailsForkFragment
import ru.dw.mvp.view.fragment.picker.ImagePickerFragment
import ru.dw.mvp.view.fragment.userDetails.DetailsUserFragment
import ru.dw.mvp.view.fragment.users.UsersFragment

object UsersScreen : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment {
        return UsersFragment.newInstance()
    }
}

data class UserDetailsScreen(private val githubUser: GithubUser) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return DetailsUserFragment.newInstance(githubUser)
    }
}

data class UserDetailsForkScreen(private val githubReposUser: GithubReposUser) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return DetailsForkFragment.newInstance(githubReposUser)
    }
}

object ImagePickerScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return ImagePickerFragment.newInstance()
    }
}