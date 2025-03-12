package com.example.practiseapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextArea, editTextRainfall, editTextFertilizer;
    private Button buttonPredict;
    private TextView textViewResult, textViewHistory;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextArea = findViewById(R.id.editTextArea);
        editTextRainfall = findViewById(R.id.editTextRainfall);
        editTextFertilizer = findViewById(R.id.editTextFertilizer);
        buttonPredict = findViewById(R.id.buttonPredict);
        textViewResult = findViewById(R.id.textViewResult);
        textViewHistory = findViewById(R.id.textViewHistory);

        databaseHelper = new DatabaseHelper(this);

        buttonPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String areaStr = editTextArea.getText().toString();
                String rainfallStr = editTextRainfall.getText().toString();
                String fertilizerStr = editTextFertilizer.getText().toString();

                // Check for empty fields
                if (areaStr.isEmpty() || rainfallStr.isEmpty() || fertilizerStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Provide VALUES FOR ALL FIELDS", Toast.LENGTH_SHORT).show();
                    return; // Exit the method
                }

                // Convert input to double
                double area = Double.parseDouble(areaStr);
                double rainfall = Double.parseDouble(rainfallStr);
                double fertilizer = Double.parseDouble(fertilizerStr);

                // Calculate predicted harvest
                double predictedHarvest = PredictionModel.predictHarvest(area, rainfall, fertilizer);
                textViewResult.setText(String.format("Predicted Harvest: %.2f tons", predictedHarvest));

                // Save prediction to database
                databaseHelper.addPrediction(area, rainfall, fertilizer, predictedHarvest);
            }
        });
    }
}