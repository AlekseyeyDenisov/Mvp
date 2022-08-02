package ru.dw.mvp

class CountersPresenter(
    private val view: MainView
) {
    private val model = CountersModel()

    fun onCounterClick(enum: ButtonCounter) {
        when (enum) {
            ButtonCounter.BtnOne -> {
                val newValue = model.next( enum.position)
                view.setText(newValue.toString(), enum.position)
            }
            ButtonCounter.BtnTwo-> {
                val newValue = model.next(enum.position)
                view.setText(newValue.toString(), enum.position)
            }
            ButtonCounter.BtnThree -> {
                val newValue = model.next(enum.position)
                view.setText(newValue.toString(), enum.position)
            }
        }
    }
}
