package ru.dw.mvp

class CountersPresenter(
    private val view: MainView
) {
    private val model = CountersModel()

    fun onCounterClick(id: Int) {
        when (id) {
            R.id.btnNumberOne -> {
                val newValue = model.next(0)
                view.setText(newValue.toString(), 0)
            }
            R.id.btnNumberTwo -> {
                val newValue = model.next(1)
                view.setText(newValue.toString(), 1)
            }
            R.id.btnNumberThree -> {
                val newValue = model.next(2)
                view.setText(newValue.toString(), 2)
            }
        }
    }
}
