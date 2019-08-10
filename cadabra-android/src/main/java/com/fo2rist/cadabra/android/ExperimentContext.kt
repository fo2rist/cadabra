package com.fo2rist.cadabra.android

import com.fo2rist.cadabra.Variant

/**
 * Full context of experiment state as it's resolved for current session/user.
 */
class ExperimentContext(
    /**
     * Active variant to be applied by the app.
     */
    val variant: Variant,
    resources: VariantResources
) : VariantResources by resources
