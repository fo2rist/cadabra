package com.github.fo2rist.cadabra

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
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
        setSupportActionBar(toolbar)

        val firstExperimentVariant = CadabraAndroid.instance.getExperimentVariant(PlainExperiment.INSTANCE)
        fab1.setOnClickListener {
            when (firstExperimentVariant.type) {
                MessageStyle.TOAST -> showToast(firstExperimentVariant.message)
                MessageStyle.SNACK -> showSnackbar(firstExperimentVariant.message)
            }
        }

        val secondExperimentContext = CadabraAndroid.instance.getExperimentContext(AutoResourceVariants::class)
        fab2.setOnClickListener {
            showAlertDialog(
                secondExperimentContext.getStringId(R.string.greeting_title_a),
                secondExperimentContext.getLayoutId(R.layout.greeting_layout_a)
            )
        }
    }

    private fun showToast(@StringRes message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_LONG)
            .show()
    }

    private fun showSnackbar(@StringRes message: Int) {
        Snackbar.make(fab1, message, Snackbar.LENGTH_LONG)
            .show()
    }

    private fun showAlertDialog(titleResId: Int, layoutResId: Int) {
        AlertDialog.Builder(this)
            .setTitle(titleResId)
            .setView(layoutResId)
            .show()
    }
}
