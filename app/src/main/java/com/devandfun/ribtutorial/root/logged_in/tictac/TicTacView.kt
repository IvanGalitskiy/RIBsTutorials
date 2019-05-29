package com.devandfun.ribtutorial.root.logged_in.tictac

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.devandfun.ribtutorial.R
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.tic_tac_rib.view.*

/**
 * Top level view for {@link TicTacBuilder.TicTacScope}.
 */
class TicTacView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle),
    TicTacInteractor.TicTacPresenter{



    lateinit var vPlayerOne:TextView
    lateinit var vPlayerTwo:TextView

    override fun onFinishInflate() {
        super.onFinishInflate()
        vPlayerOne = findViewById(R.id.button)
        vPlayerTwo = findViewById(R.id.button2)
    }

    override fun playerOneWinReport(): Observable<Unit> = vPlayerOne.clicks()

    override fun playerTwoWinReport(): Observable<Unit> = vPlayerTwo.clicks()

    override fun setNames(playerOne: String, playerTwo: String) {
        vPlayerOne.text = playerOne
        vPlayerTwo.text = playerTwo
    }
}
