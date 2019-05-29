package com.devandfun.ribtutorial.root.logged_in.tictac

import com.devandfun.ribtutorial.root.logged_in.ScoreStream
import com.uber.autodispose.ObservableScoper
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

/**
 * Coordinates Business Logic for [TicTacScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class TicTacInteractor : Interactor<TicTacInteractor.TicTacPresenter, TicTacRouter>() {

    @Inject
    lateinit var presenter: TicTacPresenter

    @Inject
    lateinit var listener:Listener

    @Inject
    @field:Named("player_one")
    lateinit var playerOne: String
    @Inject
    @field:Named("player_two")
    lateinit var playerTwo: String


    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        presenter.playerOneWinReport()
            .to(ObservableScoper<Unit>(this))
            .subscribe {
                listener.onGameFinished(playerOne)
            }
        presenter.playerTwoWinReport()
            .to(ObservableScoper<Unit>(this))
            .subscribe {
                listener.onGameFinished(playerTwo)
            }
        presenter.setNames(playerOne, playerTwo)
    }

    override fun willResignActive() {
        super.willResignActive()
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface TicTacPresenter {
        fun playerOneWinReport(): Observable<Unit>
        fun playerTwoWinReport(): Observable<Unit>
        fun setNames(playerOne:String, playerTwo:String)
    }

    interface Listener{
        fun onGameFinished(winnerName:String)
    }


}
