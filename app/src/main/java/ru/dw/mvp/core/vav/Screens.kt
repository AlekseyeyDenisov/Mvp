package ru.dw.mvp.core.vav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.dw.mvp.view.fragment.UsersFragment

object UsersScreen : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment {
        return UsersFragment.newInstance()
    }

}