package com.devandfun.ribtutorial.root.logged_in

import com.devandfun.ribtutorial.root.RootView
import com.devandfun.ribtutorial.root.logged_in.off_game.DaggerOffGameBuilder_Component
import com.devandfun.ribtutorial.root.logged_in.off_game.OffGameBuilder
import com.devandfun.ribtutorial.root.logged_in.off_game.OffGameRouter
import com.devandfun.ribtutorial.root.logged_in.tictac.TicTacBuilder
import com.devandfun.ribtutorial.root.logged_in.tictac.TicTacRouter
import com.uber.rib.core.Router

/**
 * Adds and removes children of {@link LoggedInBuilder.LoggedInScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class LoggedInRouter(
    interactor: LoggedInInteractor,
    component: LoggedInBuilder.Component,
    val rootView: RootView,
    val offGameBuilder: OffGameBuilder,
    val tictacBuilder: TicTacBuilder
) : Router<LoggedInInteractor, LoggedInBuilder.Component>(interactor, component) {
    var offGameRouter: OffGameRouter? = null
    var ticTacRouter: TicTacRouter? = null


    override fun willDetach() {
        super.willDetach()
        detachOffGame()
        detachTicTacToe()
    }

    fun attachOffGame() {
        offGameRouter = offGameBuilder.build(rootView)
        attachChild(offGameRouter)
        rootView.addView(offGameRouter?.view)
    }

    fun detachOffGame() {
        offGameRouter?.let {
            detachChild(it)
            rootView.removeView(it.view)
            offGameRouter = null
        }
    }


    fun attachTicTacToe() {
        ticTacRouter = tictacBuilder.build(rootView)
        attachChild(ticTacRouter)
        rootView.addView(ticTacRouter?.view)
    }

    fun detachTicTacToe() {
        ticTacRouter?.let {
            detachChild(it)
            rootView.removeView(it.view)
            ticTacRouter = null
        }
    }
}
