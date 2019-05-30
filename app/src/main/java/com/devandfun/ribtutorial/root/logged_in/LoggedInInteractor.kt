package com.devandfun.ribtutorial.root.logged_in

import android.util.Log
import com.devandfun.ribtutorial.root.logged_in.off_game.OffGameInteractor
import com.devandfun.ribtutorial.root.logged_in.tictac.TicTacInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import com.uber.rib.core.Router
import io.reactivex.Single

import javax.inject.Inject
import javax.inject.Named

/**
 * Coordinates Business Logic for [LoggedInScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class LoggedInInteractor : Interactor<EmptyPresenter, LoggedInRouter>(), LoggedInActionableItem {

    @Inject
    @LoggedInBuilder.LoggedInInternal
    lateinit var scoreStream: ScoreStream

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

    internal inner class TicTacGameListener : TicTacInteractor.Listener {
        override fun onGameFinished(winnerName: String) {
            scoreStream.addVictory(winnerName)
            router.detachTicTacToe()
            router.attachOffGame()
        }
    }


}
