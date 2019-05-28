package com.devandfun.ribtutorial.root.logged_in.off_game

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Coordinates Business Logic for [OffGameScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class OffGameInteractor : Interactor<OffGameInteractor.OffGamePresenter, OffGameRouter>() {
    @Inject
    @field:Named("player_one")
    lateinit var playerOne: String
    @Inject
    @field:Named("player_two")
    lateinit var playerTwo: String
    @Inject
    lateinit var presenter: OffGamePresenter

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        presenter.setPlayerNames(playerOne, playerTwo)
    }

    override fun willResignActive() {
        super.willResignActive()

        // TODO: Perform any required clean up here, or delete this method entirely if not needed.
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface OffGamePresenter{
        fun setPlayerNames(playerOne: String, playerTwo: String)
        fun startGameRequest(): Observable<Any>
    }

    interface Listener {
        fun onStartGame()
    }
}
