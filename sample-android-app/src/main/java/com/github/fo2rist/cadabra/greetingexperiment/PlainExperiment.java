package com.github.fo2rist.cadabra.greetingexperiment;

import android.support.annotation.StringRes;
import com.github.fo2rist.cadabra.BaseExperiment;
import com.github.fo2rist.cadabra.MainActivityParameters.MessageStyle;
import com.github.fo2rist.cadabra.R;
import com.github.fo2rist.cadabra.Variant;
import org.jetbrains.annotations.NotNull;

/**
 * Sample experiment configuration.
 * Demonstrates manual experiment object creation.
 */
public final class PlainExperiment extends BaseExperiment<PlainExperiment.PlainVariants> {

    public static PlainExperiment INSTANCE = new PlainExperiment(PlainVariants.class);

    private PlainExperiment(@NotNull Class<PlainVariants> variantsClass) {
        super(variantsClass);
    }

    /**
     * Variants for {@link PlainExperiment}.
     * Demonstrate experiment data access via variant enum itself and implementation via Java enums.
     */
    public enum PlainVariants implements Variant {
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

        PlainVariants(@StringRes int message, @NotNull MessageStyle type){
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
}
