package com.example.appcinema;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoPelicula#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoPelicula extends Fragment {

    private TextView title;
    private ImageView image;

    private String title_movie;
    private int image_movie;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoPelicula() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoPelicula.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoPelicula newInstance(String param1, String param2) {
        InfoPelicula fragment = new InfoPelicula();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_pelicula, container, false);
        title = view.findViewById(R.id.title);
        image = view.findViewById(R.id.poster);
        setElements();
        return view;
    }

    private void setElements() {
        this.title.setText(title_movie);
        this.image.setImageResource(image_movie);
    }

    public void setTitle(String title) {this.title_movie = title;}
    public void setImage(int image) {this.image_movie = image;}

}