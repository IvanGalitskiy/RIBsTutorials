package com.devandfun.ribtutorial.root

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

/**
 * Top level view for {@link RootBuilder.RootScope}.
 */
class RootView : FrameLayout, RootInteractor.RootPresenter {
    constructor(ctx: Context) : this(ctx, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}
