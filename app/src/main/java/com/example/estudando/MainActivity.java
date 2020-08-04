package com.example.estudando;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.estudando.ajuda.Ajuda;
import com.example.estudando.fragmentos.CursosEad;
import com.example.estudando.fragmentos.Favorito;
import com.example.estudando.fragmentos.MainFragment;
import com.example.estudando.fragmentos.Sobre;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    public DrawerLayout d1;
    public ActionBarDrawerToggle t;
    public NavigationView nv;

    public AdapterCursos adapterCursos;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        d1 = (DrawerLayout) findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(
                this, d1, toolbar, R.string.open, R.string.close
        );

        d1.addDrawerListener(t);
        t.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmento, new MainFragment());
        fragmentTransaction.commit();

        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.lista){
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    CursosEad cursos = new CursosEad();
                    fragmentTransaction.replace(R.id.fragmento, cursos);
                    fragmentTransaction.commit();
                }

                //

                if (id == R.id.favoritos){
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    Favorito favorito = new Favorito();
                    fragmentTransaction.replace(R.id.fragmento, favorito);
                    fragmentTransaction.commit();
                }

                if (id == R.id.feedback){
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    // The intent does not have a URI, so declare the "text/plain" MIME type
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"eaglelist@gmail.com"}); // recipients
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback para o App EagleList");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Escreva seu feedback, por favor");
                    startActivity(emailIntent);
                }

                if (id == R.id.sobre){
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    Sobre sobre = new Sobre();
                    fragmentTransaction.replace(R.id.fragmento, sobre);
                    fragmentTransaction.commit();

                }

                if (id == R.id.ajuda){
                    Intent telaAjuda = new Intent(getApplicationContext(), Ajuda.class);
                    startActivity(telaAjuda);
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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Lista lista = new Lista();
                adapterCursos = new AdapterCursos(getApplicationContext(), lista.listagem());
                adapterCursos.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Lista lista = new Lista();
                adapterCursos = new AdapterCursos(getApplicationContext(), lista.listagem());
                adapterCursos.getFilter().filter(newText);
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
}
