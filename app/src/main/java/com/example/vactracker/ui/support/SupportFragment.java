package com.example.vactracker.ui.support;

import android.content.DialogInterface;
import android.graphics.Color;
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
        donate.setTextColor(Color.DKGRAY);

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
                donate.setTextColor(Color.WHITE);
                donate.setBackgroundResource(R.drawable.button_focus);
                if (cardForm.isValid()) {
                    alertBuilder = new AlertDialog.Builder(getActivity());
                    alertBuilder.setTitle("Confirm before donating");
                    alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                            "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                            "Card CVV: " + cardForm.getCvv() + "\n" +
                            "Postal code: " + cardForm.getPostalCode() + "\n" +
                            "Phone number: " + cardForm.getMobileNumber());
                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(getActivity(), "Thank you for your donation!", Toast.LENGTH_LONG).show();
                        }
                    });
                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            donate.setTextColor(Color.DKGRAY);
                            donate.setBackgroundResource(R.drawable.button_unfocused);
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();

                } else {
                    Toast.makeText(getActivity(), "Please complete the form", Toast.LENGTH_LONG).show();
                    donate.setTextColor(Color.DKGRAY);
                    donate.setBackgroundResource(R.drawable.button_unfocused);
                }
            }
        });



        donate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donationAmount.setText("10.00");
                donate10.setTextColor(Color.WHITE);
                donate20.setTextColor(Color.DKGRAY);
                donate50.setTextColor(Color.DKGRAY);
                donate100.setTextColor(Color.DKGRAY);
                donate10.setBackgroundResource(R.drawable.button_focus);
                donate20.setBackgroundResource(R.drawable.button_unfocused);
                donate50.setBackgroundResource(R.drawable.button_unfocused);
                donate100.setBackgroundResource(R.drawable.button_unfocused);

            }
        });

        donate20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donationAmount.setText("20.00");
                donate10.setTextColor(Color.DKGRAY);
                donate20.setTextColor(Color.WHITE);
                donate50.setTextColor(Color.DKGRAY);
                donate100.setTextColor(Color.DKGRAY);
                donate10.setBackgroundResource(R.drawable.button_unfocused);
                donate20.setBackgroundResource(R.drawable.button_focus);
                donate50.setBackgroundResource(R.drawable.button_unfocused);
                donate100.setBackgroundResource(R.drawable.button_unfocused);
            }
        });

        donate50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donationAmount.setText("50.00");
                donate10.setTextColor(Color.DKGRAY);
                donate20.setTextColor(Color.DKGRAY);
                donate50.setTextColor(Color.WHITE);
                donate100.setTextColor(Color.DKGRAY);
                donate10.setBackgroundResource(R.drawable.button_unfocused);
                donate20.setBackgroundResource(R.drawable.button_unfocused);
                donate50.setBackgroundResource(R.drawable.button_focus);
                donate100.setBackgroundResource(R.drawable.button_unfocused);
            }
        });

        donate100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donationAmount.setText("100.00");
                donate10.setTextColor(Color.DKGRAY);
                donate20.setTextColor(Color.DKGRAY);
                donate50.setTextColor(Color.DKGRAY);
                donate100.setTextColor(Color.WHITE);
                donate10.setBackgroundResource(R.drawable.button_unfocused);
                donate20.setBackgroundResource(R.drawable.button_unfocused);
                donate50.setBackgroundResource(R.drawable.button_unfocused);
                donate100.setBackgroundResource(R.drawable.button_focus);
            }
        });

        return root;
    }
}
