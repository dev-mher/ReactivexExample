package com.example.android.reactivexsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Observable.fromArray(Array(3, { i -> (i * i) }))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result: Array<Int>? ->
                            result?.let {
                                val iterator = result.iterator()
                                while (iterator.hasNext()) {
                                    println("==> from observer ${iterator.next()}")
                                }
                            }
                        },
                        { error: Throwable? -> println("==> error is ${error?.message}") },
                        { println("==> Complete") }
                )
    }
}
