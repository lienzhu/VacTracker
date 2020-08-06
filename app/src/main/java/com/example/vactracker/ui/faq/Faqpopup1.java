package com.example.vactracker.ui.faq;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.vactracker.R;

public class Faqpopup1 extends DialogFragment {

    private static final String TAG = "done";
    TextView tvClose, tvFAQ, tvAnswer;
    Button bExit, bDonate;

    public static Faqpopup1 newInstance() {
        return new Faqpopup1();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faqpopup1_fragment, container, false);

        tvFAQ = view.findViewById(R.id.tvFAQ);
        tvAnswer = view.findViewById(R.id.tvAnswer);

        bExit = view.findViewById(R.id.bExit);
        bDonate = view.findViewById(R.id.bDonate);
        bDonate.setVisibility(View.GONE);

        bDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.who.int/emergencies/diseases/novel-coronavirus-2019/donate");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        String value = getArguments().getString("Key");

        bExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDialog().dismiss();

            }
        });

        if (value == "Q1") {
            tvFAQ.setText("What is CoVac?");
            Log.d(TAG, "Question1 Displayed");
            tvAnswer.setText("The CoVac App is one-stop-shop for all information relating to the development of the COVID-19 vaccine. \n " +
                    "The app compiles information from credible news outlets to provide users with access to authentic updates. " +
                    "Our goal is to solve the problem of COVID-19 vaccine information being segregated all over the internet, where reliable sources aren’t always easily accessible. ");
            Log.d(TAG, "Question1 Answer");
        } else if (value == "Q2") {
            Log.d(TAG, "else if Q2");
            tvFAQ.setText("Where is the information from?");
            tvAnswer.setText("The information displayed in CoVac is compiled " +
                    "from various news outlets from the internet. These outlets have been filtered and verified by our team, e" +
                    "nsuring that only authentic information is provided to you.");

        } else if (value == "Q3") {
            tvFAQ.setText("What do the clinical trials mean and how do they work?");
            tvAnswer.setText("Clinical trials describe the process in which new vaccines/drugs are approved to be used and distributed to the general population in order to prevent, diagnose or treat a particular disease. Healthcare professionals including researchers and medical doctors undertake these clinical trials which generally fall into 5 stages. It often takes years or as long as decades for a compound to pass through the clinical trials. The 5 stages include: " +
                    "\n Preclinical:" +
                    "\n In this stage, the efficacy, toxicity and other relevant chemical information of the new drug is tested. This research is usually conducted in test tubes and cell cultures in animal models. The purpose of this process is to determine which therapies have merit and are able to be progressed into human trials.\n" +
                    "Phase I:\n" +
                    "In this phase, the safety is assessed by giving healthy volunteers a new therapeutic. Generally, 20-100 individuals are tested on with various subtherapeutic doses. Researches have the opportunity to assess the drug’s chemical properties in humans as they may be different from animal models.\n" +
                    "Duration: Approximately several months\n" +
                    "Success Rate: Approximately 70%\n \n" +
                    "Phase II:\n" +
                    "Phase II commences when the therapeutic has been verified as safe for healthy individuals. The drug or vaccine is administered to the target population to determine if it will be effective at its therapeutic dosage. 100-300 individuals are often involved in this clinical phase and will only progress to the next phase when the data suggests that the drug has positive effects on its target population.\n" +
                    "Duration: Approximately several months to 2 years\n" +
                    "Success Rate: Approximately 33%\n" +
                    "\n" +
                    "Phase III\n" +
                    "In this phase, the drug is now presumed to have a positive effect on the target population. 300-3000 individuals are involved in this stage to observe the short-term side effects that are more uncommon and rare. In addition to clinical researchers, personal physicians begin to become involved in the clinical trials as the therapeutic progresses into the next stage.\n" +
                    "Duration: Approximately 1-4 years\n" +
                    "Success Rate: Approximately 25-30%\n" +
                    "\n" +
                    "Phase IV:\n" +
                    "The purpose of this phase is to monitor long-term effects of the drug on a larger population. At this stage, the therapeutic may be prescribed to individuals in the target population through their physician. Extremely rare side effects may be observed in this stage due to the longer term and a larger subset of the population is using the therapeutic.\n");
        } else if (value == "Q4") {
            tvFAQ.setText("How does pandemics (such as COVID-19) impact the clinical trials Process?");
            tvAnswer.setText("During a life-threatening situation where there is no vaccine or medicine currently for a particular disease (like during this pandemic), the clinical trial process may be expedited. \n" +
                    "\n" +
                    "As it generally takes years or even as long as decades for the compound to progress through the clinical trials, the process can be fast-tracked to provide medical assistance to those in need. This is usually done for drugs that already display strong potential in combating the disease as they are already effective against related disorders. \n" +
                    "\n" +
                    "To progress faster through the stages, the process will involve more frequent meetings with governing bodies as well as the drug manufacturing company must submit the sections of crucial documents as soon as they are completed as opposed to one submission for every completed stage of the trial. Although this process is fast-tracked, the entire process is still extremely rigorous.\n");
        } else if (value == "Q5") {
            tvFAQ.setText("What is the meaning of each heading on the Vaccine Profile?");
            tvAnswer.setText("Product Type: Vaccine's drug or platform class \n \n" +
                    "Description: Short description of the vaccine \n \n" +
                    "Developer(s): The organisation(s) developing the vaccine \n \n " +
                    "Stage: The current phase of clinical development \n \n" +
                    "Origin: The source of the data containing the vaccine's research and development details. \n \n " +
                    "Other diseases: Other diseases or pathogens for which the vaccine has undergone or is undergoing clinical developmnent.");
        } else if (value == "Q6") {
            tvFAQ.setText("How can I help?");
            tvAnswer.setText("You can donate on our support page. All proceeds will go to the World Health Organisation's COVID-19 Response Fund" +
                    "to go toward the development and distribution of a vaccine. \n \nFor further information regarding the COVID-19 Response Fund, please click the button below.");
            bDonate.setVisibility(View.VISIBLE);
        } else if (value == "Q7") {
            tvFAQ.setText("How can I provide feedback about this tracker?");
            tvAnswer.setText("We appreciate your use of our vaccine tracker! If you wish to provide us with any feedback in regards to the content, bugs, issues or any suggestions for improvement, please email us at vaccinetracker@DMTechnology.com. Any feedback is welcome!\n");
        }

        return view;
    }
}
