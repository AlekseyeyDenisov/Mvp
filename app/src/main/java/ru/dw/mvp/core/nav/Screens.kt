package ru.dw.mvp.core.nav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.dw.mvp.model.GithubUser
import ru.dw.mvp.view.fragment.picker.ImagePickerFragment
import ru.dw.mvp.view.fragment.user.DetailsFragment
import ru.dw.mvp.view.fragment.users.UsersFragment

object UsersScreen : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment {
        return UsersFragment.newInstance()
    }
}

data class UserDetailsScreen(private val githubUser: GithubUser) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return DetailsFragment.newInstance(githubUser)
    }
}

object ImagePickerScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return ImagePickerFragment.newInstance()
    }
}