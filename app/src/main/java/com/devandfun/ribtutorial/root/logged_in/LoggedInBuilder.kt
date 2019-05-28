package com.devandfun.ribtutorial.root.logged_in

import com.devandfun.ribtutorial.root.RootView
import com.devandfun.ribtutorial.root.logged_in.off_game.OffGameBuilder
import com.devandfun.ribtutorial.root.logged_in.tictac.TicTacBuilder
import com.uber.rib.core.Builder
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.InteractorBaseComponent
import java.lang.annotation.Retention

import javax.inject.Qualifier
import javax.inject.Scope

import dagger.Provides
import dagger.BindsInstance

import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Named

class LoggedInBuilder(dependency: ParentComponent) :
    Builder<LoggedInRouter, LoggedInBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [LoggedInRouter].
     *
     * @return a new [LoggedInRouter].
     */
    fun build(firstPlayerName:String, secondPlayerName:String): LoggedInRouter {
        val interactor = LoggedInInteractor()
        val component = DaggerLoggedInBuilder_Component.builder()
            .playerOne(firstPlayerName)
            .playerTwo(secondPlayerName)
            .parentComponent(dependency)
            .interactor(interactor)
            .build()

        return component.loggedinRouter()
    }

    interface ParentComponent {
        fun rootView(): RootView
    }


    @dagger.Module
    abstract class Module {

        @dagger.Module
        companion object {

            @LoggedInScope
            @Provides
            @JvmStatic
            internal fun presenter(): EmptyPresenter {
                return EmptyPresenter()
            }

            @LoggedInScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                interactor: LoggedInInteractor,
                rootView: RootView
            ): LoggedInRouter {
                return LoggedInRouter(
                    interactor,
                    component,
                    rootView,
                    OffGameBuilder(component),
                    TicTacBuilder(component)
                )
            }


            @LoggedInScope
            @Provides
            @JvmStatic
            internal fun offGameListener(interactor: LoggedInInteractor): LoggedInInteractor.OffGameListener {
                return interactor.OffGameListener()
            }
            // TODO: Create provider methods for dependencies created by this Rib. These methods should be static.
        }
    }
    class TestModel{}

    @LoggedInScope
    @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
    interface Component : InteractorBaseComponent<LoggedInInteractor>, BuilderComponent, OffGameBuilder.ParentComponent,
        TicTacBuilder.ParentComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: LoggedInInteractor): Builder

            @BindsInstance
            fun playerOne(@Named("player_one") playerOne: String): Builder

            @BindsInstance
            fun playerTwo(@Named("player_two") playerTwo: String): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }

    }

    interface BuilderComponent {
        fun loggedinRouter(): LoggedInRouter
    }

    @Scope
    @Retention(CLASS)
    internal annotation class LoggedInScope


    @Qualifier
    @Retention(CLASS)
    internal annotation class LoggedInInternal
}
