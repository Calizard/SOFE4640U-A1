package com.example.sofe4640_assignment_1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaymentResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_results);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                openMain(v);
            }
        });

        // Intent to get values
        Intent i = getIntent();
        double mortgageAmount = i.getDoubleExtra("MORTGAGE_AMOUNT", 0);
        double interestRate = i.getDoubleExtra("INTEREST_RATE", 0);
        double amortizationPeriod = i.getDoubleExtra("AMORTIZATION_PERIOD", 0);

        // Monthly interest rate
        double monthlyRate = interestRate / 12 / 100;

        // Total number of payments (months)
        double totalPayments = amortizationPeriod * 12;

        // Calculate the monthly payment
        double monthlyPayment = mortgageAmount * (monthlyRate * Math.pow(1 + monthlyRate, totalPayments)) /
                                (Math.pow(1 + monthlyRate, totalPayments) - 1);

        // Total payment
        double totalMortgagePayment = monthlyPayment * totalPayments;

        // Total interest
        double totalInterest = totalMortgagePayment - mortgageAmount;

        // Formatted Strings
        String formattedMonthlyPaymentText = String.format("Monthly Payment:\n$%,.2f",
                monthlyPayment);

        String formattedMortgageDetailsText = String.format("Mortgage Principal Amount:\n$%,.2f\nInterest Rate:\n%.2f%%\nAmortization Period:\n%.2f years",
                mortgageAmount, interestRate, amortizationPeriod);

        String formattedPaymentText = String.format("Total Payments over period:\n$%,.2f\nInterest paid over period:\n$%,.2f",
                totalMortgagePayment, totalInterest);

        // Find the TextView and set the text to display the received values
        TextView monthlyPaymentText = findViewById(R.id.monthlyPaymentText);
        monthlyPaymentText.setText(formattedMonthlyPaymentText);

        TextView resultTextView = findViewById(R.id.MortgageDetailsText);
        resultTextView.setText(formattedMortgageDetailsText);

        TextView paymentDetailsText = findViewById(R.id.PaymentDetailsText);
        paymentDetailsText.setText(formattedPaymentText);
    }

    public void openMain (View v) {

        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
    }
}