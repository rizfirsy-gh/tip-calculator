package com.rizfirsy.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rizfirsy.myapplication.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding

    private fun calculateTip () {

        //get the cost value(user input)
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        //get the selected tip percentage by its ID
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //check if the cost is null
        if(cost == null) {
            //clear the tipResult and do nothing
            binding.tipResult.text = getString(R.string.empty_cost)
            return
        }

        //if `cost` not null, then calculate the tip
        var tip = cost * tipPercentage

        //check if the round-up is checked
        val isRoundUp = binding.roundUpSwitch.isChecked
        if(isRoundUp) {
            //if so, please round the tip up
            tip = ceil(tip)
        }

        //format the tip to concurrent number
        val formattedTip  =  NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //calculate the tip
        binding.calculateButton.setOnClickListener{calculateTip()}
    }
}


