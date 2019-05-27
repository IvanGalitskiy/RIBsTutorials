package com.devandfun.ribtutorial.root.logged_in

import com.devandfun.ribtutorial.root.logged_in.off_game.OffGameInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import com.uber.rib.core.Router

import javax.inject.Inject

/**
 * Coordinates Business Logic for [LoggedInScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class LoggedInInteractor : Interactor<EmptyPresenter, LoggedInRouter>() {

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        router.attachOffGame()
    }



    internal inner class OffGameListener : OffGameInteractor.Listener {
        override fun onStartGame() {
            router.detachOffGame()
            router.attachTicTacToe()
        }
    }


}
