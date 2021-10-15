package org.airgraft.cityautocomplete.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ScoreCalculatorTests {
    static DecimalFormat df = new DecimalFormat("0.000");

    @Test
    public void calculateScoreForLongAndLatWhereBothValuesAre0() throws Exception {
        assertEquals(ScoreCalculator.calculateScore(0, 0), 1);
    }

    @Test
    public void calculateScoreForLongAndLatWhereOneValueIs0() throws Exception {
        assertEquals(Double.parseDouble(df.format(ScoreCalculator.calculateScore(45.9999f, 0f))), 0);
    }

    @Test
    public void calculateScoreForLongAndLatWithNonZeroValues() throws Exception {
        assertEquals(Double.parseDouble(df.format(ScoreCalculator.calculateScore(45.9999f, 48f))), 0.957);
    }

    @Test
    public void calculateScoreForLongAndLatWithNegativeValues() throws Exception {
        assertEquals(Double.parseDouble(df.format(ScoreCalculator.calculateScore(-45.9999f, -48f))), 0.957);
    }

    @Test
    public void calculateScoreForLongAndLatWithOneNegativeValue() throws Exception {
        assertEquals(Double.parseDouble(df.format(ScoreCalculator.calculateScore(-45.9999f, 48f))), 0);
    }
}
