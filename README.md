![cadabra](docs/assets/cadabra_logo_slim.png)

# Cadabra A/B experiments library
Cadabra simplifies configuration of A/B experiments and access to experiments' config
It keeps the registry of all experiments in one place and provides non-blocking API for access and helpers for Android UI

See the [documentation](https://fo2rist.github.io/cadabra/) for mor details.

To use in gradle project 

    // for basic functionality
    implementation 'com.fo2rist.cadabra:cadabra-core:0.3.0'
    
    // for Android automatic resources resolving
    implementation 'com.fo2rist.cadabra:cadabra-android:0.3.0'
    
    // for automatic parsing of Firebase A/B experiment configs
    implementation 'com.fo2rist.cadabra:cadabra-firebase:0.3.0'

NOTE: cadabra-firebase 0.2 is the latest to support Android API 15 and it uses firebase-config v16
 latter versions use new firebase-config and androidx libraries

MIGRATION NOTES: ExperimentNotStarted exception was removed in 0.3.0, now Cadabra returns null for active variant if the experiment wasn't started

[![Build Status](https://app.bitrise.io/app/5781b73059466ba5/status.svg?token=fvX37th1yPPTTe6L2iVzuQ&branch=master)](https://app.bitrise.io/app/5781b73059466ba5)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e1ae15358eb94f52b0fe262b256f788e)](https://www.codacy.com/app/fo2/cadabra?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=fo2rist/cadabra&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/fo2rist/cadabra/branch/master/graph/badge.svg)](https://codecov.io/gh/fo2rist/cadabra)
