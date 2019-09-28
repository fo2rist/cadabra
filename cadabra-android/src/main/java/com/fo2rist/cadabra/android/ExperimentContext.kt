package com.fo2rist.cadabra.android

import com.fo2rist.cadabra.Variant

/**
 * Full context of experiment state as it's resolved for current session/user.
 * If the experiment wasn't started the [variant] is null resources resolved as is.
 */
class ExperimentContext(
    /** Active variant to be applied by the app. */
    val variant: Variant?,

    resources: VariantResources

) : VariantResources by resources
