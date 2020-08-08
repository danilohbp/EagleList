package com.example.estudando.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;

import com.example.estudando.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedBack#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedBack extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedBack() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedBack.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedBack newInstance(String param1, String param2) {
        FeedBack fragment = new FeedBack();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed_back, container, false);

        //chamarAppsEmail();

        final RadioButton sugestao = view.findViewById(R.id.rdSugestao);
        final RadioButton critica = view.findViewById(R.id.rdCritica);

        sugestao.setChecked(true);

        Button btn = view.findViewById(R.id.btnEmail);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sugestao.isChecked()){
                    chamarAppsEmail("Sugestão para o App EagleList");
                }
                else if(critica.isChecked()){
                    chamarAppsEmail("Crítica do App EagleList");
                }
            }
        });


        return view;
    }



    public void chamarAppsEmail(String assunto){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"eaglelist@gmail.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, assunto);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Escreva seu feedback, por favor");
        startActivity(emailIntent);
    }

    public void radioOpcao(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rdSugestao:
                if (checked)
                    assunto("Sugestão para o App EagleList");
                    break;
            case R.id.rdCritica:
                if (checked)
                    assunto("Crítica do App EagleList");
                    break;
            default:
                naoMarcouBotaoAlgum();
        }
    }

    public String assunto(String s){
        return s;
    }

    public Boolean naoMarcouBotaoAlgum(){
        return false;
    }

}