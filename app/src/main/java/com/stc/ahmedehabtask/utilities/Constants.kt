package com.stc.ahmedehabtask.utilities

object Constants {
    const val AUTHORIZATION = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNGQ2N2JjZmIxYzFmNDhmMmNmNzQwMjcwMjliM2YxZiIsInN1YiI6IjVlN2U5ZjkzNDM1YWJkMDAxNWIwZjZhNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AA38kTRQmJlVZq0K3kM2zwUK-OE9RtHW8TvN_SySjRw"
    const val ACCEPT = "application/json"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val STORAGE_URL = "https://image.tmdb.org/t/p/w500"


    private fun internetIsConnected(): Boolean {
        return try {
            val command = "ping -c 1 google.com"
            Runtime.getRuntime().exec(command).waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }
}