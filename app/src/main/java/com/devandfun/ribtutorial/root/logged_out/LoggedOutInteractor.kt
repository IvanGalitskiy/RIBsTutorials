package com.devandfun.ribtutorial.root.logged_out

import android.text.TextUtils.isEmpty
import android.util.Log
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [LoggedOutScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class LoggedOutInteractor : Interactor<LoggedOutInteractor.LoggedOutPresenter, LoggedOutRouter>() {

    @Inject
    lateinit var presenter: LoggedOutPresenter
    @Inject
    lateinit var listener: Listener

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        presenter.loginName()
            .subscribe {
                if (!isEmpty(it)) {
                    listener.login(it);
                }
            }
    }

    override fun willResignActive() {
        super.willResignActive()

        // TODO: Perform any required clean up here, or delete this method entirely if not needed.
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface LoggedOutPresenter {
        fun loginName(): Observable<String>
    }
    interface Listener{
       fun login(name:String)
    }
}
