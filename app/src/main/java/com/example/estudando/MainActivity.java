package com.example.estudando;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.estudando.ajuda.Ajuda;
import com.example.estudando.fragmentos.CursosEad;
import com.example.estudando.fragmentos.Favorito;
import com.example.estudando.fragmentos.FeedBack;
import com.example.estudando.fragmentos.MainFragment;
import com.example.estudando.fragmentos.Sobre;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    public DrawerLayout d1;
    public ActionBarDrawerToggle t;
    public NavigationView nv;

    RecyclerView recyclerView;
    AdapterCursos adapterCursos;
    CursosEad cursos;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);

        d1 = (DrawerLayout) findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(
                this, d1, toolbar, R.string.open, R.string.close
        );

        d1.addDrawerListener(t);
        t.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        cursos = new CursosEad();
        fragmentTransaction.add(R.id.fragmento, cursos);
        fragmentTransaction.commit();

        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.lista){
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    //cursos = new CursosEad();
                    fragmentTransaction.replace(R.id.fragmento, cursos);
                    fragmentTransaction.commit();;

                    ActionMenuItemView searchView = findViewById(R.id.search);
                    searchView.setVisibility(View.VISIBLE);
                }

                if (id == R.id.favoritos){
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    Favorito favorito = new Favorito();
                    fragmentTransaction.replace(R.id.fragmento, favorito);
                    fragmentTransaction.commit();

                    ActionMenuItemView searchView = findViewById(R.id.search);
                    searchView.setVisibility(View.GONE);
                }

                if (id == R.id.feedback){

                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    FeedBack feedBack = new FeedBack();
                    fragmentTransaction.replace(R.id.fragmento, feedBack);
                    fragmentTransaction.commit();

                    ActionMenuItemView searchView = findViewById(R.id.search);
                    searchView.setVisibility(View.GONE);
                }

                if (id == R.id.sobre){
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    Sobre sobre = new Sobre();
                    fragmentTransaction.replace(R.id.fragmento, sobre);
                    fragmentTransaction.commit();

                    ActionMenuItemView searchView = findViewById(R.id.search);
                    searchView.setVisibility(View.GONE);
                }

                if (id == R.id.ajuda){
                    Intent telaAjuda = new Intent(getApplicationContext(), Ajuda.class);
                    startActivity(telaAjuda);

                    habilitaIconeFiltro(true);

                    ActionMenuItemView searchView = findViewById(R.id.search);
                    searchView.setVisibility(View.GONE);
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);

        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.onActionViewCollapsed();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                cursos.adapterCursos.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cursos.adapterCursos.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    public boolean habilitaIconeFiltro(Boolean teste){
        return teste;
    }


}
