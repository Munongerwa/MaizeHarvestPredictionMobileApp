package com.example.practiseapp;

public class PredictionModel {
    public static double predictHarvest(double area, double rainfall, double fertilizer) {
        // Simple prediction model: Adjust the coefficients based on real data
        double yieldPerAcre = 2.0; // Base yield per acre without any inputs
        double predictedYield = yieldPerAcre * area;

        // Adjust based on rainfall and fertilizer
        if (rainfall < 20) {
            predictedYield *= 0.5; // Low rainfall reduces yield
        } else if (rainfall > 50) {
            predictedYield *= 1.2; // High rainfall increases yield
        }

        if (fertilizer < 100) {
            predictedYield *= 0.8; // Low fertilizer reduces yield
        } else if (fertilizer > 200) {
            predictedYield *= 1.5; // High fertilizer increases yield
        }

        return predictedYield;
    }
}