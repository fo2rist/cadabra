package com.fo2rist.cadabra

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.fo2rist.cadabra.MainActivityParameters.MessageStyle
import com.fo2rist.cadabra.greetingexperiment.AutoResourceExperiment
import com.fo2rist.cadabra.greetingexperiment.FirebaseJsonExperiment
import com.fo2rist.cadabra.greetingexperiment.FirebaseKeyValueExperiment
import com.fo2rist.cadabra.greetingexperiment.PlainExperiment
import com.fo2rist.cadabra.android.CadabraAndroid
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

/**
 * Sample activity.
 * Demonstrates how the config can be fetched and used to conduct the experiment.
 */
class MainActivity : AppCompatActivity() {

    private val cadabraAndroid = CadabraAndroid.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val firstExperimentVariant = cadabraAndroid.getExperimentVariant(PlainExperiment::class)
        fab1.setOnClickListener {
            when (firstExperimentVariant.type) {
                MessageStyle.TOAST -> showToast(firstExperimentVariant.message)
                MessageStyle.SNACK -> showSnackbar(firstExperimentVariant.message)
            }
        }

        val secondExperimentContext = cadabraAndroid.getExperimentContext(AutoResourceExperiment::class)
        fab2.setOnClickListener {
            showAlertDialog(
                secondExperimentContext.getStringId(R.string.greeting_title_a),
                secondExperimentContext.getLayoutId(R.layout.greeting_layout_a)
            )
        }

        val thirdExperimentContext = cadabraAndroid.getExperimentContext(FirebaseJsonExperiment::class)
        fab3.setOnClickListener {
            showSnackbar(thirdExperimentContext.getStringId(R.string.remote_greeting_f1))
        }

        val fourthExperimentContext = cadabraAndroid.getExperimentContext(FirebaseKeyValueExperiment::class)
        label_experiment4.text = fourthExperimentContext.getString(R.string.firebase_key_value_kv0)

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
