package com.devandfun.ribtutorial.root

import android.view.View
import com.devandfun.ribtutorial.root.logged_in.LoggedInActionableItem
import com.devandfun.ribtutorial.root.logged_in.LoggedInBuilder
import com.devandfun.ribtutorial.root.logged_out.LoggedOutBuilder
import com.devandfun.ribtutorial.root.logged_out.LoggedOutRouter
import com.jakewharton.rxrelay2.BehaviorRelay
import com.uber.rib.core.Optional

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
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component)  {

    var loggedOutRouter: LoggedOutRouter? = null
    private val loggedInActionableItemRelay = BehaviorRelay.create<Optional<LoggedInActionableItem>>()

    fun attachLoggedOut() {
        loggedOutRouter = loggedOutBuilder.build(view)
        attachChild(loggedOutRouter)
        view.addView(loggedOutRouter?.view)
    }

    fun attachLoggedIn(firstName:String ,secondName:String):LoggedInActionableItem {
        val loggedInRouter = loggedInBuilder.build(firstName, secondName)
        attachChild(loggedInBuilder.build(firstName, secondName))
        loggedInActionableItemRelay.accept(Optional.of(loggedInRouter.interactor))
        return loggedInRouter.interactor
    }

    fun detachLoggedOut() {
        loggedOutRouter?.let {
            detachChild(it)
            view.removeView(it.view)
            loggedOutRouter = null
        }
    }
}
