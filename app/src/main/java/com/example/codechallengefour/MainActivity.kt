package com.example.codechallengefour

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.codechallengefour.databinding.ActivityMainBinding
import com.example.codechallengefour.utils.DateUtils.longToDateString
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var bindings: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityMainBinding.inflate(layoutInflater)
        bindings.apply {
            txtInputTranslatableNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (!s.isNullOrEmpty()) {
                        val sValue = s.toString()
                        viewModel.translateNumberToWord(sValue)
                    }
                }
            })

            txtDate.setOnClickListener {
                val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
                datePicker.show(supportFragmentManager, "DatePicker")

                datePicker.addOnPositiveButtonClickListener {
                    val pairDate = datePicker.selection
                    pairDate?.let {
                        val date1 = it.first?.let { it1 -> longToDateString(it1) }
                        val date2 = it.second?.let { it1 -> longToDateString(it1) }

                        viewModel.calculateDaysOfWork(Date(it.first), Date(it.second))
                        bindings.txtDate.setText("$date1 - $date2")
                    }
                }
            }

        }
        observable()
        setContentView(bindings.root)
    }

    private fun observable() {
        lifecycleScope.launch {
            bindings.apply {
                viewModel.translatedNumber.onEach {
                    lblText.text = it
                }.launchIn(this@launch)
                viewModel.totalDayOfWork.onEach {
                    lblWorkDays.text = "$it days"
                }.launchIn(this@launch)
            }
        }
    }

}