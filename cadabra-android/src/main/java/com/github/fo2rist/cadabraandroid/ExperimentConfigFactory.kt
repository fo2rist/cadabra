package com.github.fo2rist.cadabraandroid

import com.github.fo2rist.cadabra.ExperimentsConfig
import org.json.JSONObject

/**
 * Create config from json.
 * Format example:
 * ```
 *   {
 *     "ExperimentId": "VariantName",
 *     ...
 *   }
 * ```
 * @throws org.json.JSONException if the parse fails.
 */
fun configFromJson(json: String): ExperimentsConfig {
    if (json.isEmpty()) {
        return ExperimentsConfig.create()
    }

    val jsonObject = JSONObject(json)

    val keys = jsonObject.keys()
    val configPairs = Array<Pair<String, String>>(jsonObject.length()) {
        val experimentId = keys.next()
        experimentId to jsonObject.getString(experimentId)
    }

    @Suppress("SpreadOperator")
    return ExperimentsConfig.create(*configPairs)
}
