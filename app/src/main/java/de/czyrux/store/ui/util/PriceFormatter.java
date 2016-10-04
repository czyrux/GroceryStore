package de.czyrux.store.ui.util;

import java.text.NumberFormat;
import java.util.Locale;

public class PriceFormatter {

    private PriceFormatter() { }

    public static String format(double price) {
        String formattedPrice = NumberFormat.getNumberInstance(Locale.getDefault()).format(price);
        return String.format("%s €", formattedPrice);
    }
}
