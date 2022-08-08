package ru.dw.mvp

class CountersPresenter(
    private val view: MainView
) {
    private val model = CountersModel()

    fun onCounterClick(enum: ButtonCounter) {
        when (enum) {
            ButtonCounter.BtnOne -> {
                onClick(enum)
            }
            ButtonCounter.BtnTwo-> {
                onClick(enum)
            }
            ButtonCounter.BtnThree -> {
                onClick(enum)
            }
        }
    }

    private fun onClick(enum: ButtonCounter) {
        val newValue = model.next(enum.position)
        view.setText(newValue.toString(), enum.position)
    }


}
