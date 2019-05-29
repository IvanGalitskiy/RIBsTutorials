package com.devandfun.ribtutorial.root.logged_in.tictac

import android.view.LayoutInflater
import android.view.ViewGroup
import com.devandfun.ribtutorial.R
import com.devandfun.ribtutorial.root.logged_in.ScoreStream
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link TicTacScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class TicTacBuilder(dependency: ParentComponent) :
    ViewBuilder<TicTacView, TicTacRouter, TicTacBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [TicTacRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [TicTacRouter].
     */
    fun build(parentViewGroup: ViewGroup): TicTacRouter {
        val view = createView(parentViewGroup)
        val interactor = TicTacInteractor()
        val component = DaggerTicTacBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.tictacRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): TicTacView? {
        return inflater.inflate(R.layout.tic_tac_rib, parentViewGroup, false) as TicTacView
    }

    interface ParentComponent {
        fun ticTacListener(): TicTacInteractor.Listener
        @Named("player_one")
        fun playerOneTicTac(): String
        @Named("player_two")
        fun playerTwoTicTac(): String
    }

    @dagger.Module
    abstract class Module {

        @TicTacScope
        @Binds
        internal abstract fun presenter(view: TicTacView): TicTacInteractor.TicTacPresenter

        @dagger.Module
        companion object {

            @TicTacScope
            @Provides
            @JvmStatic
            internal fun router(
                component: Component,
                view: TicTacView,
                interactor: TicTacInteractor
            ): TicTacRouter {
                return TicTacRouter(view, interactor, component)
            }
        }

        // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }

    @TicTacScope
    @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
    interface Component : InteractorBaseComponent<TicTacInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: TicTacInteractor): Builder

            @BindsInstance
            fun view(view: TicTacView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun tictacRouter(): TicTacRouter
    }

    @Scope
    @Retention(CLASS)
    internal annotation class TicTacScope

    @Qualifier
    @Retention(CLASS)
    internal annotation class TicTacInternal
}
