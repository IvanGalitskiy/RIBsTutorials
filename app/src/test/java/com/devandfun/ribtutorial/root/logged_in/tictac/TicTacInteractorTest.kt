package com.devandfun.ribtutorial.root.logged_in.tictac

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TicTacInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: TicTacInteractor.TicTacPresenter
  @Mock internal lateinit var router: TicTacRouter

  private var interactor: TicTacInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestTicTacInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<TicTacInteractor.TicTacPresenter, TicTacRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}