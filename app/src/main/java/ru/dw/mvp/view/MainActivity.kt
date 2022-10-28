package ru.dw.mvp.view

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.dw.mvp.MyApp
import ru.dw.mvp.R
import ru.dw.mvp.presenter.OnBackPressedListener
import ru.dw.mvp.databinding.ActivityMainBinding
import ru.dw.mvp.presenter.MainPresenter
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(),MainView {

    private val navigator = AppNavigator(this, R.id.containerMain)
    private lateinit var binding: ActivityMainBinding

    private val presenter by moxyPresenter { MainPresenter(MyApp.instance.router)  }

    @Inject
    lateinit var  navigationHolder: NavigatorHolder

    private val component by lazy {
        (this.application as MyApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { currentFragment->
            if (currentFragment is OnBackPressedListener && currentFragment.onBackPressed()){
                return
            }

        }
        presenter.onBackPressed()
    }


}
