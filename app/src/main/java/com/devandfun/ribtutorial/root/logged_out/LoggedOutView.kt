package com.devandfun.ribtutorial.root.logged_out

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.devandfun.ribtutorial.R
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins

/**
 * Top level view for {@link LoggedOutBuilder.LoggedOutScope}.
 */
class LoggedOutView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle),
    LoggedOutInteractor.LoggedOutPresenter {

    lateinit var button: Button
    lateinit var editText: EditText

    override fun onFinishInflate() {
        super.onFinishInflate()
        button = findViewById(R.id.login_button)
        editText = findViewById(R.id.edit_text)
    }
    override fun loginName(): Observable<String> {
        return button.clicks()
            .map { editText.text.toString() }
    }
}
