package com.devandfun.ribtutorial.root

import com.devandfun.ribtutorial.root.logged_in.LoggedInBuilder
import com.devandfun.ribtutorial.root.logged_out.LoggedOutBuilder
import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RootRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: RootBuilder.Component
  @Mock internal lateinit var interactor: RootInteractor
  @Mock internal lateinit var view: RootView

  private var router: RootRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = RootRouter(view, interactor, component, LoggedOutBuilder(component), LoggedInBuilder(component))
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router!!)
    RouterHelper.detach(router!!)

    throw RuntimeException("Remove this test and add real tests.")
  }

}

