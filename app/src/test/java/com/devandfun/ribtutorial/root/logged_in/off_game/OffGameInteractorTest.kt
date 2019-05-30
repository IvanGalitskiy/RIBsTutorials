package com.devandfun.ribtutorial.root.logged_in.off_game

import com.devandfun.ribtutorial.root.logged_in.MutableScoreStream
import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class OffGameInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: OffGameInteractor.OffGamePresenter
  @Mock internal lateinit var listener: OffGameInteractor.Listener
  @Mock internal lateinit var router: OffGameRouter

  private var interactor: OffGameInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestOffGameInteractor.create("fakename1", "fakename2", presenter, MutableScoreStream("fakename1", "fakename2"),
      listener)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<OffGameInteractor.OffGamePresenter, OffGameRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}