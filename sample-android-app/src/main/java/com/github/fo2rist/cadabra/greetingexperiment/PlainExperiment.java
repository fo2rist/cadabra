package com.github.fo2rist.cadabra.greetingexperiment;

import android.support.annotation.StringRes;
import com.github.fo2rist.cadabra.MainActivityParameters.MessageStyle;
import com.github.fo2rist.cadabra.R;
import com.github.fo2rist.cadabra.Variant;
import org.jetbrains.annotations.NotNull;


/**
 * Experiment enum with data.
 * Demonstrates data access via variant enum itself and implementation via Java enums.
 */
public enum PlainExperiment implements Variant {
    A(
            R.string.greeting_title_a,
            MessageStyle.TOAST
    ),
    B(
            R.string.greeting_title_b,
            MessageStyle.SNACK
    ),
    ;

    @StringRes
    public int message;

    @NotNull
    public MessageStyle type;

    PlainExperiment(@StringRes int message, @NotNull MessageStyle type){
        this.message = message;
        this.type = type;
    }

    //Java enums must explicitly declare getName() and delegate to name()
    @NotNull
    @Override
    public String getName() {
        return this.name();
    }
}
