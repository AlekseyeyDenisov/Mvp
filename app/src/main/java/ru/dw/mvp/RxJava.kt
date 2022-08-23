package ru.dw.mvp


import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.TestScheduler
import java.util.*
import java.util.concurrent.TimeUnit


fun main() {
    val subscribeByDefault = Observable.just("Jonny-1", "Yuri-2", "Anton-3")
    //exampleFlatMap(subscribeByDefault)
    //exampleSwitchMap(subscribeByDefault)
    switchMap(subscribeByDefault)



}

fun switchMap(subscribeByDefault: Observable<String>) {
    val scheduler = TestScheduler()
    subscribeByDefault.switchMap { s ->
            val delay = Random().nextInt(10)
            Observable.just<String>(s.toString() + "x")
                .delay(delay.toLong(), TimeUnit.SECONDS, scheduler)
        }.subscribe { println(it) }
    scheduler.advanceTimeBy(1, TimeUnit.MINUTES)
}


private fun exampleSwitchMap(subscribeByDefault: Observable<String>) {
    val scheduler = TestScheduler()
    subscribeByDefault
        .switchMap { element ->
            val delay: Int = Random().nextInt(10)
            Observable.just(listOf(element, "switchMap"))
                //.delay(delay.toLong(), TimeUnit.SECONDS, scheduler)
                .debounce(2, TimeUnit.SECONDS)
        }
        .subscribe { println(it) }
}

private fun exampleFlatMap(subscribeByDefault: Observable<String>) {
    subscribeByDefault
        //.debounce(1000, TimeUnit.MILLISECONDS)
        .flatMap { element ->
            Observable.just(listOf(element, "flatMap"))
        }
        .subscribe { println(it) }
}

private fun exampleConcatMap(subscribeByDefault: Observable<String>) {
    subscribeByDefault
        //.debounce(1000, TimeUnit.MILLISECONDS)
        .concatMap { element ->
            Observable.just(listOf(element, "concatMap"))
        }
        .subscribe { println(it) }
}

private fun getUserInfo(name: String): Observable<List<String>> {
    return Observable.just(listOf(name, "email.com"))
}

fun example() {

}


