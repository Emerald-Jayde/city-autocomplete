package org.airgraft.cityautocomplete.utils;

import org.airgraft.cityautocomplete.model.City;
import org.apache.commons.text.similarity.JaroWinklerDistance;

import java.text.DecimalFormat;

public class ScoreCalculator {

    /**
     * Calculates the total score for each given parameter, and returns the average.
     * @param city the City object
     * @param name search term
     * @param longitude searched longitude
     * @param latitude searched latitude
     * @return average score of all evaluated parameters
     */
    public static double calculateScore(City city, String name, String longitude, String latitude) {
        DecimalFormat df = new DecimalFormat("0.000");

        double sum = 0.0;
        double numOfCriteria = 1.0;

        double name_score = calculateScore(city.getName(), name);
        sum += name_score;

        if (longitude != null) {
            sum += calculateScore(Float.parseFloat(city.getLongitude()), Float.parseFloat(longitude));
            numOfCriteria++;
        }

        if (latitude != null) {
            sum += calculateScore(Float.parseFloat(city.getLatitude()), Float.parseFloat(latitude));
            numOfCriteria++;
        }

        return Double.parseDouble(df.format(sum / numOfCriteria));
    }

    /**
     * Calculates the similarities between 2 strings
     * The higher the score, the more similar the values
     * A score of 1 means the 2 values are identical
     * @param str1 the first string to compare
     * @param str2 the second string to compare
     * @return the similarity score as a double between [0, 1]
     */
    public static double calculateScore(String str1, String str2) {
        return new JaroWinklerDistance().apply(str1, str2);
    }

    /**
     * Calculates the percentage difference between 2 numbers
     * The lower the score, the more distant the values
     * @param num1 the first number to compare
     * @param num2 the second number to compare
     * @return the similarity percentage as a double between [0, 1].
     *  returns 0 if the average of both numbers is smaller than the absolute difference
     */
    public static double calculateScore(float num1, float num2) {
        if(num1 == num2) { return 1; }

        float absDiff = Math.abs(num1 - num2);
        float average = (Math.abs(num1) + Math.abs(num2))/2.0f;

        return (absDiff / average) > 1 ? 0 : 1 - (absDiff / average);
    }
}
