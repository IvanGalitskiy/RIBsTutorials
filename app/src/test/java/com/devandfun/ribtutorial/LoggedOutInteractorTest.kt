package com.devandfun.ribtutorial

import com.devandfun.ribtutorial.root.logged_out.LoggedOutInteractor
import com.devandfun.ribtutorial.root.logged_out.LoggedOutRouter
import com.devandfun.ribtutorial.root.logged_out.TestLoggedOutInteractor
import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper
import io.reactivex.Observable

import org.junit.Before
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class LoggedOutInteractorTest : RibTestBasePlaceholder() {

    @Mock
    internal lateinit var presenter: LoggedOutInteractor.LoggedOutPresenter
    @Mock
    internal lateinit var router: LoggedOutRouter
    @Mock
    internal lateinit var listener: LoggedOutInteractor.Listener

    private var interactor: LoggedOutInteractor? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        interactor = TestLoggedOutInteractor.create(presenter, listener)
    }

    @Test
    fun attach_whenViewEmitsName_shouldCallListener() {
        Mockito.`when`(presenter.loginName()).thenReturn(Observable.just(Pair<String, String>("fakename", "fakename2")))
        InteractorHelper.attach(interactor, presenter, router, null)
        verify(listener).login(Matchers.anyString(), Matchers.anyString())
    }
    @Test
    fun attach_whenViewEmitsEmptyName_shouldNotCallListener() {
        Mockito.`when`(presenter.loginName()).thenReturn(Observable.just(Pair<String, String>("", "fakename2")))
        InteractorHelper.attach(interactor, presenter, router, null)
        verify(listener, never()).login(Matchers.anyString(),Matchers.anyString())
    }
}