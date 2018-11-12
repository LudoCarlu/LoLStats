package com.github.vincebrees.lolstats.presentation

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by Vincent ETIENNE on 09/11/2018.
 */

abstract class BaseActivity : AppCompatActivity(){
    fun toast(message: String,
              duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}