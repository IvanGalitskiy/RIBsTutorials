package com.devandfun.ribtutorial.root.logged_in.off_game

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.devandfun.ribtutorial.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.off_game_rib.view.*

/**
 * Top level view for {@link OffGameBuilder.OffGameScope}.
 */
class OffGameView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), OffGameInteractor.OffGamePresenter {
    lateinit var vFirstName:TextView
    lateinit var vSecondName:TextView

    override fun onFinishInflate() {
        super.onFinishInflate()
        vFirstName = findViewById(R.id.player_one_name)
        vSecondName = findViewById(R.id.player_two_name)
    }

    override fun startGameRequest(): Observable<Any> {
        return Observable.just(0)
    }

    override fun setPlayerNames(playerOne: String, playerTwo: String) {
        vFirstName.setText(playerOne)
        vSecondName.setText(playerTwo)
    }
}
