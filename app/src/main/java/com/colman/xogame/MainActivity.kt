package com.colman.xogame

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.colman.xogame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isPlayerX = true
    private var gameActive = true
    private val board = Array(9) { "" }
    private lateinit var buttons: Array<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttons = arrayOf(
            binding.button0, binding.button1, binding.button2,
            binding.button3, binding.button4, binding.button5,
            binding.button6, binding.button7, binding.button8
        )

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                onCellClicked(button, index)
            }
            button.text = ""
        }

        binding.playAgain.setOnClickListener {
            resetGame()
        }
    }

    private fun onCellClicked(button: Button, index: Int) {
        if (board[index].isNotEmpty() || !gameActive) return

        val symbol = if (isPlayerX) "X" else "O"
        board[index] = symbol
        button.text = symbol
        button.setTextColor(if (isPlayerX) Color.RED else Color.BLUE)

        if (checkWinner()) {
            endGame("Player $symbol Wins!")
        } else if (board.none { it.isEmpty() }) {
            endGame("It's a Draw!")
        } else {
            isPlayerX = !isPlayerX
            binding.gameStatus.text = "Player ${if (isPlayerX) "X" else "O"}'s Turn"
        }
    }

    private fun checkWinner(): Boolean {
        val winPositions = arrayOf(
            intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
        )
        return winPositions.any { pos ->
            board[pos[0]].isNotEmpty() && 
            board[pos[0]] == board[pos[1]] && 
            board[pos[1]] == board[pos[2]]
        }
    }

    private fun endGame(message: String) {
        binding.gameStatus.text = message
        gameActive = false
        binding.playAgain.visibility = View.VISIBLE
    }

    private fun resetGame() {
        isPlayerX = true
        gameActive = true
        board.fill("")
        buttons.forEach { it.text = "" }
        binding.gameStatus.text = "Player X's Turn"
        binding.playAgain.visibility = View.GONE
    }
}