package com.example.sp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    lateinit var username: EditText
    lateinit var color: Spinner
    lateinit var caps: Switch


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.username = findViewById(R.id.idUsername)
        this.color = findViewById(R.id.idColorSpinner)
        this.caps = findViewById(R.id.idCapsSwitch)


        color.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> color.setBackgroundColor(Color.WHITE)
                    1 -> color.setBackgroundColor(Color.BLUE)
                    2 -> color.setBackgroundColor(Color.GREEN)
                    3 -> color.setBackgroundColor(Color.YELLOW)
                    else -> color.setBackgroundColor(Color.CYAN)
                }
            }


            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }


        caps.setOnCheckedChangeListener { _, _ ->
            if (caps.isChecked) {
                caps.text = "ALL CAPS"
            } else caps.text = "All caps"
        }


    }


    override fun onResume() {
        super.onResume()
        val SP = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val key1 = SP.getString("username", "")
        val key2 = SP.getInt("color", 0)
        val key3 = SP.getBoolean("caps", false)


        this.username.setText(key1)
        this.color.setSelection(key2)
        this.caps.isChecked = key3


    }


    override fun onPause() {
        super.onPause()
        val SP = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val edit = SP.edit()
        edit.putString("username", this.username.text.toString())
        edit.putInt("color", this.color.selectedItemPosition)
        edit.putBoolean("caps", this.caps.isChecked)
        edit.apply()


    }


}

