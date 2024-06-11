package com.example.nexttogo.utils

object Utils {

    fun convertTimerFormat(targetTime: Long): String {
        val minutes = targetTime / 60
        val remainingSeconds = targetTime % 60
        return if (minutes > 0) {
            String.format("%dm%ds", minutes, remainingSeconds)
        } else {
            String.format("%ds", remainingSeconds)
        }
    }
}