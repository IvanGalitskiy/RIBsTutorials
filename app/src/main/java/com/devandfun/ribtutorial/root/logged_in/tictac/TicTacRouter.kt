package com.devandfun.ribtutorial.root.logged_in.tictac

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link TicTacBuilder.TicTacScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class TicTacRouter(
    view: TicTacView,
    interactor: TicTacInteractor,
    component: TicTacBuilder.Component) : ViewRouter<TicTacView, TicTacInteractor, TicTacBuilder.Component>(view, interactor, component)
