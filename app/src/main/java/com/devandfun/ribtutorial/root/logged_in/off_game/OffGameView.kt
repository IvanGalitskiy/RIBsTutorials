package com.devandfun.ribtutorial.root.logged_in.off_game

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

/**
 * Top level view for {@link OffGameBuilder.OffGameScope}.
 */
class OffGameView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), OffGameInteractor.OffGamePresenter
