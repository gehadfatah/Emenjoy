package com.goda.ikhair.presentation.common

import android.graphics.Color
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.goda.ikhair.R
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

fun View.toggleVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}
/*fun View.toggleColor(visible: Boolean) {
    background= if (visible) resources.setBackgroundColor(ContextCompat.getColor(applicationContext,
            R.color.colorAccent))
    else View.GONE
}*/
infix fun View.onClick(action: (() -> Unit)) {
    this.setOnClickListener { action.invoke() }
}
fun View.loadImage(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        if (!TextUtils.isEmpty(it)) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.loading_animation)
            requestOptions.error(R.drawable.ic_broken_image)
            Glide.with(view.context)
                    .load(imageUrl)
                    .apply(requestOptions)
                    .into(view)
        }
    }
}
fun View.showSnackbar(message: String) {
    if (!TextUtils.isEmpty(message)) {
        val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.WHITE)
        val sbView = snackBar.view
        sbView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.colorAccent))
        snackBar.show()
    }
}

fun EditText.searchObservable(): Observable<String> {
    val subject = PublishSubject.create<String>()
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
            subject.onNext(text.toString())
        }

        override fun afterTextChanged(s: Editable) {
//            subject.onComplete()
        }
    })

    return subject
}
