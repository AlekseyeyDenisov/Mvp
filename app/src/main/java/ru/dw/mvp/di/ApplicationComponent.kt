package ru.dw.mvp.di

import android.app.Application
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import ru.dw.mvp.view.MainActivity
import ru.dw.mvp.view.fragment.userDetails.DetailsUserFragment
import ru.dw.mvp.view.fragment.users.UsersFragment


@ApplicationScopeSingleton
@Component(
    modules = [
        DataModule::class
    ]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(usersFragment: UsersFragment)

    fun inject(userFragment: DetailsUserFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application,
            @BindsInstance router: Router,
            @BindsInstance navigationHolder: NavigatorHolder,
        ): ApplicationComponent

    }

}