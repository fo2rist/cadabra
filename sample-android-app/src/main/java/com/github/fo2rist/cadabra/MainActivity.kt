package com.github.fo2rist.cadabra

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.github.fo2rist.cadabra.MainActivityParameters.MessageStyle
import com.github.fo2rist.cadabra.greetingexperiment.AutoResourceVariants
import com.github.fo2rist.cadabra.greetingexperiment.PlainExperiment
import com.github.fo2rist.cadabraandroid.CadabraAndroid
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Sample activity.
 * Demonstrates how the config can be fetched and used to conduct the experiment.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstExperimentVariant = CadabraAndroid.instance.getExperimentVariant(PlainExperiment)
        fab1.setOnClickListener {
            when (firstExperimentVariant.type) {
                MessageStyle.TOAST -> showToast(firstExperimentVariant.message)
                MessageStyle.SNACK -> showSnackbar(firstExperimentVariant.message)
            }
        }

        fab2.setOnClickListener {
            showSnackbar(
                CadabraAndroid.instance.getExperimentContext(AutoResourceVariants::class)
                    .getStringId(R.string.greeting_title_a)
            )
        }

        setSupportActionBar(toolbar)
    }

    private fun showToast(@StringRes message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_LONG)
            .show()
    }

    private fun showSnackbar(@StringRes message: Int) {
        Snackbar.make(fab1, message, Snackbar.LENGTH_LONG)
            .show()
    }
}
