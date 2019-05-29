package com.devandfun.ribtutorial.root.logged_in

import io.reactivex.Observable

interface ScoreStream {
    fun addVictory(userName:String): Map<String, Int>
    fun scores():Observable<Map<String,Int>>
}