package com.colman.xogame

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.colman.xogame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val board = arrayOf("", "", "", "", "", "", "", "", "")
    private lateinit var buttons: Array<Button?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        buttons = arrayOf(
            binding?.button0, binding?.button1, binding?.button2,
            binding?.button3, binding?.button4, binding?.button5,
            binding?.button6, binding?.button7, binding?.button8
        )

        buttons.forEachIndexed { index, button ->
            button?.setOnClickListener { onCellClicked(button, index) }
            button?.text = ""
        }
    }

    private fun onCellClicked(button: Button, index: Int) {
        //TODO: implement
    }

}