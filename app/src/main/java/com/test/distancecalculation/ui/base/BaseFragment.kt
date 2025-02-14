package com.test.distancecalculation.ui.base

import android.os.Handler
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment() {
    
    fun showPreviousFragment() {
        Handler().post { activity?.onBackPressed() }
    }
}