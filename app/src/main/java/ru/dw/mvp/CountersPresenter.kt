package ru.dw.mvp

class CountersPresenter(
    private val view: MainView
) {
    private val model = CountersModel()

    fun onCounterClick(enum: ButtonCounter) {
        when (enum) {
            ButtonCounter.btnOne -> {
                val newValue = model.next(0)
                view.setText(newValue.toString(), 0)
            }
            ButtonCounter.btnTwo-> {
                val newValue = model.next(1)
                view.setText(newValue.toString(), 1)
            }
            ButtonCounter.btnThree -> {
                val newValue = model.next(2)
                view.setText(newValue.toString(), 2)
            }
        }
    }
}
