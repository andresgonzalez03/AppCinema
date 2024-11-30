package com.example.appcinema;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link menu_inf#newInstance} factory method to
 * create an instance of this fragment.
 */
public class menu_inf extends Fragment {
    private boolean seatSelected = false;
    private boolean buttonEnabled = true;
    private boolean horarioSelected = false;
    private boolean entradaSelected = false;
    private Button continueButton;

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
        if (getView() != null) {
            Button continueButton = getView().findViewById(R.id.buttonContinuar);
            if (getActivity() instanceof ViewDetailsCompra ) {
                continueButton.setEnabled(true);
            } else if (getActivity() instanceof TicketSelection || getActivity() instanceof SeatSelection || getActivity() instanceof movieDetails || getActivity() instanceof PagoTarjeta) {
                continueButton.setEnabled(false);
            } else {
                continueButton.setEnabled(true);
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
        continueButton = view.findViewById(R.id.buttonContinuar);
        if (continueButton != null) {
            if(getActivity() instanceof ViewDetailsCompra) {
                continueButton.setEnabled(true);
                continueButton.setOnClickListener(view1 -> navigateToNextActivity());
            } else if(getActivity() instanceof CompraEntradas) {
                continueButton.setEnabled(true);
                continueButton.setText(R.string.boto_register);
                continueButton.setOnClickListener(view1 -> navigateToNextActivity());
            }
            else if (getActivity() instanceof SeatSelection) {
                continueButton.setEnabled(seatSelected);
                continueButton.setOnClickListener(view1 -> navigateToNextActivity());
            } else if (getActivity() instanceof movieDetails) {
                continueButton.setEnabled(horarioSelected);
                continueButton.setOnClickListener(view1 -> navigateToNextActivity());
            } else if (getActivity() instanceof TicketSelection) {
                continueButton.setEnabled(entradaSelected);
                continueButton.setOnClickListener(view1 -> navigateToNextActivity());
            } else if (getActivity() instanceof PagoTarjeta) {
                continueButton.setEnabled(false);
                continueButton.setText("Pagar");
                continueButton.setOnClickListener(view1 -> {
                    PagoTarjeta pagoTarjetaActivity = (PagoTarjeta) getActivity();
                    if (pagoTarjetaActivity != null) {
                        pagoTarjetaActivity.realizarPago();
                        Intent intent = new Intent(getActivity(), CompraConfirmada.class);
                        startActivity(intent);
                        getActivity().finish();

                    }
                });
            }
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
        if (getActivity() instanceof SeatSelection && seatSelected) {
            SeatSelection activity = (SeatSelection) getActivity();
            if (activity != null && !activity.getSelectedSeats().isEmpty()) {
                Intent intent = new Intent(getActivity(), TicketSelection.class);
                intent.putStringArrayListExtra("seats", new ArrayList<>(activity.getSelectedSeats()));
                intent.putExtra("title", activity.getMovieTitle());
                intent.putExtra("image", activity.getMovieImg());
                startActivity(intent);
            }
        } else if (getActivity() instanceof movieDetails && horarioSelected) {
            movieDetails activity = (movieDetails) getActivity();
            if (activity != null && activity.getSelectedHorario() != null) {
                Intent intent = new Intent(getActivity(), SeatSelection.class);
                intent.putExtra("horari", activity.getSelectedHorario());
                intent.putExtra("title", activity.getMovieTitle());
                intent.putExtra("image", activity.getMovieImg());
                startActivity(intent);
            }
        } else if (getActivity() instanceof TicketSelection && entradaSelected) {
            TicketSelection activity = (TicketSelection) getActivity();
            if (activity != null && activity.getEntrada() != null) {
                Intent intent = new Intent(getActivity(), ViewDetailsCompra.class);
                intent.putExtra("entrada", activity.getEntrada());
                intent.putExtra("nEntradas", activity.getNEntradas());
                intent.putExtra("seats", activity.getSelectedSeats());
                intent.putExtra("title", activity.getMovieTitle());
                intent.putExtra("image", activity.getMovieImg());
                startActivity(intent);
            }
        } else if (getActivity() instanceof ViewDetailsCompra) {
            ViewDetailsCompra activity = (ViewDetailsCompra) getActivity();
            if (activity != null) {
                Intent intent = new Intent(getActivity(), CompraEntradas.class);
                intent.putExtra("precio", activity.getPrecioTotal());
                intent.putExtra("title", activity.getMovieTitle());
                intent.putExtra("image", activity.getMovieImage());
                startActivity(intent);
            }
        } else if (getActivity() instanceof CompraEntradas) {
            CompraEntradas activity = (CompraEntradas) getActivity();
            Intent intent = new Intent(getActivity(), PagoTarjeta.class);
            intent.putExtra("precio", activity.getPrecioTotal());
            intent.putExtra("title", activity.getMovieTitle());
            intent.putExtra("image", activity.getMovieImage());
            startActivity(intent);
        }
    }
    public void resetButton() {
        seatSelected = false;
        buttonEnabled = false;
        horarioSelected = false;
        entradaSelected = false;
        if(getView() != null) {
            Button continueButton = getView().findViewById(R.id.buttonContinuar);
            if(continueButton != null) {
                continueButton.setEnabled(false);
            }
        }
    }

    public void updateHorarioSelectionStatus(boolean isSelected) {
        horarioSelected = isSelected;
        Button continueButton = getView().findViewById(R.id.buttonContinuar);
        if (continueButton != null) {
            continueButton.setEnabled(isSelected);
        }
    }

    public void updateEntradaSelectionStatus(boolean isSelected) {
        entradaSelected = isSelected;
        Button continueButton = getView().findViewById(R.id.buttonContinuar);
        if (continueButton != null) {
            continueButton.setEnabled(isSelected);
        }
    }
}