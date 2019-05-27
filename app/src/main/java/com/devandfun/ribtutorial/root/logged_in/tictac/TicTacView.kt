package com.devandfun.ribtutorial.root.logged_in.tictac

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Top level view for {@link TicTacBuilder.TicTacScope}.
 */
class TicTacView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle), TicTacInteractor.TicTacPresenter
