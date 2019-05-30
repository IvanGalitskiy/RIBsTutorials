package com.devandfun.ribtutorial.root

import com.devandfun.ribtutorial.root.logged_in.LoggedInActionableItem
import com.devandfun.ribtutorial.root.logged_out.LoggedOutInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import com.uber.rib.workflow.core.Step
import javax.inject.Inject
import io.reactivex.Single



/**
 * Coordinates Business Logic for [RootScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() , RootActionableItem{

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

    override fun login(firstPlayer: String?, secondPlayer: String?): Step<Step.NoValue, LoggedInActionableItem> {
        return Step.from(Single.defer {
            router.detachLoggedOut()
            val loggedInActionableItem = router.attachLoggedIn(firstPlayer!!, secondPlayer!!)
            Single.just(Step.Data.toActionableItem(loggedInActionableItem))
        })
    }


    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface RootPresenter

    internal inner class LoggedOutListener : LoggedOutInteractor.Listener {
        override fun login(userName: String, secondUserName:String) {
            router.detachLoggedOut()
            router.attachLoggedIn(userName, secondUserName)
        }
    }
}
