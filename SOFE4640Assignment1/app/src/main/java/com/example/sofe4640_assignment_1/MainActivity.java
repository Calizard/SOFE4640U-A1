package com.example.sofe4640_assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void calculatePayment (View v) {

        // Put Text Inputs into variables
        TextInputEditText mortgageAmountInput = findViewById(R.id.mortgageAmountInput);
        TextInputEditText interestRateInput = findViewById(R.id.interestRateInput);
        TextInputEditText amortizationPeriodInput = findViewById(R.id.amortizationPeriodInput);

        // Convert text inputs into Strings
        String mortgageAmountStr = mortgageAmountInput.getText().toString();
        String interestRateStr = interestRateInput.getText().toString();
        String amortizationPeriodStr = amortizationPeriodInput.getText().toString();

        // Validate if fields are empty
        if (mortgageAmountStr.isEmpty()) {
            mortgageAmountInput.setError("Mortgage amount is required");
            mortgageAmountInput.requestFocus();
            return;
        }

        if (interestRateStr.isEmpty()) {
            interestRateInput.setError("Interest rate is required");
            interestRateInput.requestFocus();
            return;
        }

        if (amortizationPeriodStr.isEmpty()) {
            amortizationPeriodInput.setError("Amortization period is required");
            amortizationPeriodInput.requestFocus();
            return;
        }

        // Parse the values into a double
        double mortgageAmount = Double.parseDouble(mortgageAmountStr);
        double interestRate = Double.parseDouble(interestRateStr);
        double amortizationPeriod = Double.parseDouble(amortizationPeriodStr);

        // Intent to pass the data to Payment Results
        Intent i = new Intent (this, PaymentResults.class);
        i.putExtra("MORTGAGE_AMOUNT", mortgageAmount);
        i.putExtra("INTEREST_RATE", interestRate);
        i.putExtra("AMORTIZATION_PERIOD", amortizationPeriod);

        startActivity(i);
    }
}