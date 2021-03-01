package com.example.estudando.ajuda;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class AjudaAdapter extends FragmentStateAdapter {

    ArrayList<Fragment> lista;

    public AjudaAdapter(@NonNull Fragment fragment, ArrayList<Fragment> lista) {
        super(fragment);
        this.lista = lista;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return lista.get(position);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
