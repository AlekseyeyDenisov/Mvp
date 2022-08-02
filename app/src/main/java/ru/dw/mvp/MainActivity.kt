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
                presenter.onCounterClick(ButtonCounter.btnOne)
            }
            btnNumberTwo.setOnClickListener {
                presenter.onCounterClick(ButtonCounter.btnTwo)
            }
            btnNumberThree.setOnClickListener {
                presenter.onCounterClick(ButtonCounter.btnThree)
            }
        }
    }

    private fun initPresenter() {
        presenter = CountersPresenter(this)
    }

    override fun setText(counter: String, position: Int) {
        with(binding) {
            when (position) {
                0 -> tvTextOne.text = counter
                1 -> tvTexTwo.text = counter
                2 -> tvTextThree.text = counter
            }
        }
    }
}
