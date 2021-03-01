package com.example.estudando.ajuda;

import com.example.estudando.MainActivity;
import com.example.estudando.R;
import com.example.estudando.fragmentos.ErroConexao;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class AjudaFragmento extends Fragment {

    private ViewPager2 viewPager2;

    private FragmentStateAdapter ajudaAdapter;

    Button comecar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ajuda, container, false);

        ArrayList<Fragment> lista = new ArrayList<Fragment>();
        lista.add(new AjudaPagina1());
        lista.add(new AjudaPagina2());
        lista.add(new AjudaPagina3());
        lista.add(new AjudaPagina4());
        lista.add(new AjudaPagina5());
        lista.add(new AjudaPagina6());

        viewPager2 = view.findViewById(R.id.pager);
        ajudaAdapter = new AjudaAdapter(this, lista);
        viewPager2.setAdapter(ajudaAdapter);

        /*comecar = view.findViewById(R.id.comecar);
        comecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity fa = getActivity();

                Intent intent = new Intent(fa, ErroConexao.class);
                startActivity(intent);
            }
        });*/

        return view;
    }
}
