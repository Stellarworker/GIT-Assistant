package com.stellarworker.gitassistant.data.custom

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class RxButton : MaterialButton {
    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
    }

    val clickEvent: Subject<Unit> = BehaviorSubject.create()

    init {
        setOnClickListener {
            clickEvent.onNext(Unit)
        }
    }
}