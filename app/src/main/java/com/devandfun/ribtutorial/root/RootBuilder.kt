package com.devandfun.ribtutorial.root

import android.view.LayoutInflater
import android.view.ViewGroup
import com.devandfun.ribtutorial.R
import com.devandfun.ribtutorial.root.logged_in.LoggedInBuilder
import com.devandfun.ribtutorial.root.logged_out.LoggedOutBuilder
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope
import com.devandfun.ribtutorial.root.logged_out.LoggedOutInteractor





/**
 * Builder for the {@link RootScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class RootBuilder(dependency: ParentComponent) : ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [RootRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [RootRouter].
   */
  fun build(parentViewGroup: ViewGroup): RootRouter {
    val view = createView(parentViewGroup)
    val interactor = RootInteractor()
    val component = DaggerRootBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.rootRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RootView? {
    return inflater.inflate(R.layout.activity_main, parentViewGroup, false) as RootView
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @RootScope
    @Binds
    internal abstract fun presenter(view: RootView): RootInteractor.RootPresenter

    @dagger.Module
    companion object {

      @RootScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: RootView,
          interactor: RootInteractor): RootRouter {
        return RootRouter(view, interactor, component, LoggedOutBuilder(component), LoggedInBuilder(component))
      }

      @RootScope
      @Provides
      @JvmStatic
      fun loggedOutListener(rootInteractor: RootInteractor): LoggedOutInteractor.Listener {
        return rootInteractor.LoggedOutListener()
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @RootScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<RootInteractor>, LoggedOutBuilder.ParentComponent,
    LoggedInBuilder.ParentComponent, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: RootInteractor): Builder

      @BindsInstance
      fun view(view: RootView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun rootRouter(): RootRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class RootScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class RootInternal
}
