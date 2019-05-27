package com.devandfun.ribtutorial.root

import android.view.View
import com.devandfun.ribtutorial.root.logged_in.LoggedInBuilder
import com.devandfun.ribtutorial.root.logged_out.LoggedOutBuilder
import com.devandfun.ribtutorial.root.logged_out.LoggedOutRouter

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    val loggedOutBuilder: LoggedOutBuilder,
    val loggedInBuilder: LoggedInBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

    var loggedOutRouter: LoggedOutRouter? = null

    fun attachLoggedOut() {
        loggedOutRouter = loggedOutBuilder.build(view)
        attachChild(loggedOutRouter)
        view.addView(loggedOutRouter?.view)
    }

    fun attachLoggedIn() {
        attachChild(loggedInBuilder.build())
    }

    fun detachLoggedOut() {
        loggedOutRouter?.let {
            detachChild(it)
            view.removeView(it.view)
            loggedOutRouter = null
        }
    }
}
