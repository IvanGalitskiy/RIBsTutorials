package com.devandfun.ribtutorial.root

import com.devandfun.ribtutorial.root.logged_out.LoggedOutInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject


/**
 * Coordinates Business Logic for [RootScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {

    @Inject
    lateinit var presenter: RootPresenter

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        router.attachLoggedOut()
        // TODO: Add attachment logic here (RxSubscriptions, etc.).
    }

    override fun willResignActive() {
        super.willResignActive()
        // TODO: Perform any required clean up here, or delete this method entirely if not needed.
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface RootPresenter

    internal inner class LoggedOutListener : LoggedOutInteractor.Listener {
        override fun login(userName: String) {
            router.detachLoggedOut()
            router.attachLoggedIn()
        }
    }
}
