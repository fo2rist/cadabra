package com.github.fo2rist.cadabra

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.github.fo2rist.cadabra.colorsexperimen.GreetingExperiment
import com.github.fo2rist.cadabra.colorsexperimen.GreetingVariants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val experimentVariant = Cadabra.instance.getExperimentVariant(GreetingExperiment)
        fab.setOnClickListener { view ->
            when(experimentVariant.type) {
                GreetingVariants.GreetWith.TOAST ->
                    showGreetingToast(view, experimentVariant.message, experimentVariant.duration)
                GreetingVariants.GreetWith.SNACK ->
                    showGreetingSnackbar(view, experimentVariant.message, experimentVariant.duration)
            }
        }

        setSupportActionBar(toolbar)
    }

    private fun showGreetingToast(view: View, message: CharSequence, duration: Int) {
        Toast.makeText(view.context, message, duration)
            .show()
    }

    private fun showGreetingSnackbar(view: View, message: CharSequence, duration: Int) {
        Snackbar.make(view, message, duration)
            .show()
    }
}
