package ru.dw.mvp.core.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.dw.mvp.R


fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_user_placeholder)
        .into(this)
}

fun <T> Single<T>.subscribeByDefault(): Single<T> {
    return this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Disposable.disposeBy(bag: CompositeDisposable) {
    bag.add(this)
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun <T> Single<T>.doCompletableIf(
    predicate: Boolean,
    completableCreater: (data: T) -> Completable
): Single<T> {
    return if (predicate) {
        this.flatMap {
            completableCreater(it).andThen(Single.just(it))
        }
    } else {
        this
    }
}


fun fibonaci(index: Int): Int {
    if (index <= 0) {
        return 0
    }
    var previous = 1
    var current = 1
    var temp = 0
    for (i in 0..index) {
        temp = current
        current = current + previous
        previous = temp
    }
    return current
}




