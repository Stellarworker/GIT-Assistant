package com.stellarworker.gitassistant.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

private const val EMPTY_STRING = ""

fun View.makeSnackbar(
    text: String = EMPTY_STRING,
    actionText: String = EMPTY_STRING,
    action: (View) -> Unit = {},
    length: Int = Snackbar.LENGTH_LONG,
    anchor: View? = null
) {
    Snackbar
        .make(this, text, length)
        .setAction(actionText, action)
        .setAnchorView(anchor)
        .show()
}