package ru.dw.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.dw.mvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: CountersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPresenter()

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

    private fun initPresenter() {
        presenter = CountersPresenter(this)
    }

    override fun setText(counter: String, value: Int) {
        with(binding) {
            when (value) {
                POSITION_UNE -> tvTextOne.text = counter
                POSITION_TWO -> tvTexTwo.text = counter
                POSITION_THREE -> tvTextThree.text = counter
            }
        }
    }

    companion object {
        const val POSITION_UNE = 0
        const val POSITION_TWO = 1
        const val POSITION_THREE = 2
    }
}
