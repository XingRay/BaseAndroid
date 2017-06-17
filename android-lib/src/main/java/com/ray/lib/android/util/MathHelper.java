
package com.ray.lib.android.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author      : leixing
 * Date        : 2017-04-26
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class MathHelper {
    /**
     * A value that is used a null value.
     */
    public static final double NULL_VALUE = Double.MAX_VALUE;
    /**
     * A number formatter to be used to make sure we have a maximum number of
     * fraction digits in the labels.
     */
    private static final NumberFormat FORMAT = NumberFormat.getNumberInstance();

    private MathHelper() {
        // empty constructor
    }

    /**
     * Calculate the minimum and maximum values out of a list of doubles.
     *
     * @param values the input values
     * @return an array with the minimum and maximum values
     */
    public static double[] minmax(List<Double> values) {
        if (values.size() == 0) {
            return new double[2];
        }
        double min = values.get(0);
        double max = min;
        int length = values.size();
        for (int i = 1; i < length; i++) {
            double value = values.get(i);
            min = Math.min(min, value);
            max = Math.max(max, value);
        }
        return new double[]{min, max};
    }

    /**
     * Computes a reasonable set of labels for a data interval and number of
     * labels.
     *
     * @param start           start value
     * @param end             final value
     * @param approxNumLabels desired number of labels
     * @return collection containing the label values
     */
    public static List<Float> getLabels(final double start, final double end,
                                        final int approxNumLabels) {
        List<Float> labels = new ArrayList<>();
        if (approxNumLabels <= 0) {
            return labels;
        }
        FORMAT.setMaximumFractionDigits(5);
        double[] labelParams = computeLabels(start, end, approxNumLabels);
        // when the start > end the inc will be negative so it will still work
        int numLabels = 2 + (int) ((labelParams[1] - labelParams[0]) / labelParams[2]);
        // we want the range to be inclusive but we don't want to blow up when
        // looping for the case where the min and max are the same. So we loop
        // on
        // numLabels not on the values.
        for (int i = 0; i < numLabels; i++) {
            double z = labelParams[0] + i * labelParams[2];
            try {
                // this way, we avoid a label value like 0.4000000000000000001
                // instead
                // of 0.4
                z = FORMAT.parse(FORMAT.format(z)).doubleValue();
            } catch (ParseException e) {
                // do nothing here
            }
            labels.add((float) z);
        }
        return labels;
    }

    /**
     * Computes a reasonable number of labels for a data range.
     *
     * @param start           start value
     * @param end             final value
     * @param approxNumLabels desired number of labels
     * @return double[] array containing {start value, end value, increment}
     */
    private static double[] computeLabels(final double start, final double end,
                                          final int approxNumLabels) {
        if (Math.abs(start - end) < 0.0000001f) {
            return new double[]{start, start, 0};
        }
        double s = start;
        double e = end;
        boolean switched = false;
        if (s > e) {
            switched = true;
            double tmp = s;
            s = e;
            e = tmp;
        }
        double xStep = roundUp(Math.abs(s - e) / approxNumLabels);
        // Compute x starting point so it is a multiple of xStep.
        double xStart = xStep * Math.ceil(s / xStep);
        double xEnd = xStep * Math.floor(e / xStep);
        if (switched) {
            return new double[]{xEnd, xStart, -1.0 * xStep};
        }
        return new double[]{xStart, xEnd, xStep};
    }

    /**
     * Given a number, round up to the nearest power of ten times 1, 2, or 5.
     *
     * @param val the number, it must be strictly positive
     */
    private static double roundUp(final double val) {
        int exponent = (int) Math.floor(Math.log10(val));
        double rval = val * Math.pow(10, -exponent);
        if (rval > 5.0) {
            rval = 10.0;
        } else if (rval > 2.0) {
            rval = 5.0;
        } else if (rval > 1.0) {
            rval = 2.0;
        }
        rval *= Math.pow(10, exponent);
        return rval;
    }
}
