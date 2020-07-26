package com.example.vactracker.ui.support;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.braintreepayments.cardform.view.CardForm;
import com.example.vactracker.MainActivity;
import com.example.vactracker.R;

public class SupportFragment extends Fragment {

    AlertDialog.Builder alertBuilder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_support, container, false);
        final TextView textView = root.findViewById(R.id.text_support);
        Button donate10 = root.findViewById(R.id.donation_10);
        Button donate20 = root.findViewById(R.id.donation_20);
        Button donate50 = root.findViewById(R.id.donation_50);
        Button donate100 = root.findViewById(R.id.donation_100);
        EditText donationAmount = root.findViewById(R.id.donation_amount);

        CardForm cardForm = root.findViewById(R.id.card_form);
        Button donate = root.findViewById(R.id.donate_button);

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(getActivity());

        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardForm.isValid()) {
                    alertBuilder = new AlertDialog.Builder(getActivity());
                    alertBuilder.setTitle("Confirm before purchase");
                    alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                            "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                            "Card CVV: " + cardForm.getCvv() + "\n" +
                            "Postal code: " + cardForm.getPostalCode() + "\n" +
                            "Phone number: " + cardForm.getMobileNumber());
                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(getActivity(), "Thank you for purchase", Toast.LENGTH_LONG).show();
                        }
                    });
                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();

                } else {
                    Toast.makeText(getActivity(), "Please complete the form", Toast.LENGTH_LONG).show();
                }
            }
        });



        donate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donationAmount.setText("10.00");
            }
        });

        donate20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donationAmount.setText("20.00");
            }
        });

        donate50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donationAmount.setText("50.00");
            }
        });

        donate100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donationAmount.setText("100.00");
            }
        });

        return root;
    }
}
