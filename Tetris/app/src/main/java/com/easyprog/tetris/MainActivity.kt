package com.easyprog.tetris

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.easyprog.tetris.storage.AppPreferences
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    var tvHighScore: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val btnNewGame = findViewById<Button>(R.id.btn_new_game)
        val btnResetScore = findViewById<Button>(R.id.btn_reset_score)
        val btnExit = findViewById<Button>(R.id.btn_exit)
        tvHighScore = findViewById(R.id.tv_high_score)

        btnNewGame.setOnClickListener(this::onBtnNewGameClick)
        btnResetScore.setOnClickListener(this::onBtnResetScoreClick)
        btnExit.setOnClickListener(this::onBtnExitClick)
    }

    private fun onBtnNewGameClick(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    private fun onBtnResetScoreClick(view: View) {
        val preferences = AppPreferences(this)
        preferences.clearHighScore()
        Snackbar.make(view, "Очки сброшены!", Snackbar.LENGTH_SHORT).show()
        tvHighScore?.text = "Лучший счет: ${preferences.getHighScore()}"
    }

    private fun onBtnExitClick(view: View) {
        exitProcess(0)
    }
}