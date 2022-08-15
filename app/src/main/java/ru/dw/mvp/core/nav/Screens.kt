package ru.dw.mvp.core.nav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.dw.mvp.model.GithubUser
import ru.dw.mvp.view.fragment.ImagePickerFragment
import ru.dw.mvp.view.fragment.user.UserDetailsFragment
import ru.dw.mvp.view.fragment.users.UsersFragment

object UsersScreen : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment {
        return UsersFragment.newInstance()
    }
}

class UserDetailsScreen(private val githubUser: GithubUser) : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserDetailsFragment.newInstance(githubUser)
    }

}

object ImagePickerScreen : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment {
        return ImagePickerFragment.newInstance()
    }
}