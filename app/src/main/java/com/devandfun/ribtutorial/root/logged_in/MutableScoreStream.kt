package com.devandfun.ribtutorial.root.logged_in

import com.jakewharton.rxrelay2.BehaviorRelay
import dagger.internal.MapBuilder
import io.reactivex.Observable
import java.util.*
import kotlin.collections.HashMap

class MutableScoreStream(playerOne: String, playerTwo: String) : ScoreStream {


    val scoresRelay: BehaviorRelay<Map<String, Int>> = BehaviorRelay.create()

    init {
        scoresRelay.accept(mapOf(Pair(playerOne, 0), Pair(playerTwo, 0)))
    }

    override fun addVictory(userName: String): Map<String, Int> {
        val currentScores = scoresRelay.value
        val builder = HashMap<String, Int>()
        currentScores.forEach { t, u ->
            if (t == userName) {
                builder[t] = u + 1
            } else {
                builder[t] = u
            }
        }
        scoresRelay.accept(builder)
        return builder
    }
    override fun scores(): Observable<Map<String, Int>> = scoresRelay.hide()
}