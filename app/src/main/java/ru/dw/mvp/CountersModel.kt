package ru.dw.mvp

class CountersModel {

    private val counters = IntArray(ButtonCounter.values().size)

    fun next(position: Int): Int {
        return counters[position]++
    }

//    fun set(position: Int, value: Int) {
//        counters[position] = value
//    }
}
