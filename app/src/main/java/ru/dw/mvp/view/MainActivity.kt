package ru.dw.mvp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.dw.mvp.presenter.CountersPresenter
import ru.dw.mvp.databinding.ActivityMainBinding
import ru.dw.mvp.model.CountersModel

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private val presenter by moxyPresenter {
        CountersPresenter(CountersModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomClick()
    }

    private fun initBottomClick() {
        with(binding) {
            btnNumberOne.setOnClickListener {
                presenter.onCounterClick(ButtonCounter.BtnOne)
            }
            btnNumberTwo.setOnClickListener {
                presenter.onCounterClick(ButtonCounter.BtnTwo)
            }
            btnNumberThree.setOnClickListener {
                presenter.onCounterClick(ButtonCounter.BtnThree)
            }
        }
    }

    companion object {
        const val POSITION_UNE = 0
        const val POSITION_TWO = 1
        const val POSITION_THREE = 2
    }

    override fun serCounterOneText(counter: String, value: Int) {
        binding.tvTextOne.text = counter
    }

    override fun serCounterTwoText(counter: String, value: Int) {
        binding.tvTexTwo.text = counter
    }

    override fun serCounterThreeText(counter: String, value: Int) {
        binding.tvTextThree.text = counter
    }
}
