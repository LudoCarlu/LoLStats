package com.github.vincebrees.lolstats.presentation

import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by Vincent ETIENNE on 09/11/2018.
 */

abstract class BaseFragment : Fragment(){

    fun toast(message: String,
              duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }
}



