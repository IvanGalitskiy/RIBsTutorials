package com.devandfun.ribtutorial.root.logged_in.off_game

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.devandfun.ribtutorial.R
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.off_game_rib.view.*

/**
 * Top level view for {@link OffGameBuilder.OffGameScope}.
 */
class OffGameView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    LinearLayout(context, attrs, defStyle), OffGameInteractor.OffGamePresenter {


    lateinit var vFirstName: TextView
    lateinit var vSecondName: TextView
    lateinit var vFirstPlayerWinCount: TextView
    lateinit var vSecondPlayerWinCount: TextView

    override fun onFinishInflate() {
        super.onFinishInflate()
        vFirstName = findViewById(R.id.player_one_name)
        vSecondName = findViewById(R.id.player_two_name)
        vFirstPlayerWinCount = findViewById(R.id.player_one_win_count)
        vSecondPlayerWinCount = findViewById(R.id.player_two_win_count)

    }

    override fun startGameRequest(): Observable<Unit> {
        return  findViewById<View>(R.id.start_game_button).clicks()
    }

    override fun setPlayerNames(playerOne: String, playerTwo: String) {
        vFirstName.text = playerOne
        vSecondName.text = playerTwo
    }

    override fun setScores(playerOneScore: Int, playerTwoScore: Int) {
        vFirstPlayerWinCount.text = playerOneScore.toString()
        vSecondPlayerWinCount.text = playerTwoScore.toString()
    }
}
