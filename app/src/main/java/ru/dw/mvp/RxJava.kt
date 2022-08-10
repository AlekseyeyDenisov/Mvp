package ru.dw.mvp


import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


fun main() {
    val subscribeByDefault = Observable.just("Jonny", "Yuri", "Anton")
    exampleFlatMap(subscribeByDefault)
    exampleSwitchMap(subscribeByDefault)
}


private fun exampleSwitchMap(subscribeByDefault: Observable<String>) {
    subscribeByDefault
        //.debounce(1000, TimeUnit.MILLISECONDS)
        .switchMap { element -> getUserInfo(element)}
        .subscribe {println(it)}
}

private fun exampleFlatMap(subscribeByDefault: Observable<String>) {
    subscribeByDefault
        //.debounce(1000, TimeUnit.MILLISECONDS)
        .flatMap { element ->getUserInfo(element)}
        .subscribe {println(it)}
}

private fun getUserInfo(name: String): Observable<List<String>> {
    return Observable.just(listOf(name, "email.com"))
}