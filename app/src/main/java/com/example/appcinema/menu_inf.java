package com.example.appcinema;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link menu_inf#newInstance} factory method to
 * create an instance of this fragment.
 */
public class menu_inf extends Fragment {
    private boolean seatSelected = false;
    private boolean buttonEnabled = true;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public menu_inf() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment menu_inf.
     */
    // TODO: Rename and change types and number of parameters
    public static menu_inf newInstance(String param1, String param2) {
        menu_inf fragment = new menu_inf();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof SeatSelection && getView() != null) {
            Button continueButton = getView().findViewById(R.id.buttonContinuar);
            if (continueButton != null) {
                continueButton.setEnabled(false);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_inf, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getActivity() instanceof SeatSelection) {
            Button continueButton = view.findViewById(R.id.buttonContinuar);

            // Configuramos el botón para que se active solo cuando seatSelected sea true
            continueButton.setEnabled(buttonEnabled && seatSelected);

            continueButton.setOnClickListener(view1 -> {
                navigateToNextActivity();
            });
        }
    }

    public void updateSeatSelectionStatus(boolean isSelected) {
        seatSelected = isSelected;
        if (getView() != null) {
            Button continueButton = getView().findViewById(R.id.buttonContinuar);
            if (continueButton != null) {
                continueButton.setEnabled(seatSelected);
            }
        }
    }
    public void setButtonEnabled(boolean isEnabled) {
        this.buttonEnabled = isEnabled;
        if (getView() != null) {
            Button continueButton = getView().findViewById(R.id.buttonContinuar);
            if (continueButton != null) {
                continueButton.setEnabled(isEnabled);

            }
        }
    }

    private void navigateToNextActivity() {
        if (seatSelected && getActivity() != null) {
            SeatSelection activity = (SeatSelection) getActivity();
            if (activity != null && activity.selectedSeat != null) {
                Intent intent = new Intent(getActivity(), TicketSelection.class);
                intent.putExtra("seat", activity.selectedSeat);
                startActivity(intent);
            }
        }
    }
    public void resetButton() {
        seatSelected = false;
        buttonEnabled = false;
        if(getView() != null) {
            Button continueButton = getView().findViewById(R.id.buttonContinuar);
            if(continueButton != null) {
                continueButton.setEnabled(false);
            }
        }
    }
}