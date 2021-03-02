package com.owaldron.frfilmcatalogue.Activities;

import android.os.Bundle;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.owaldron.frfilmcatalogue.Data.MovieRecyclerViewAdapter;
import com.owaldron.frfilmcatalogue.Model.Pokemon;
import com.owaldron.frfilmcatalogue.R;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.android.volley.*;
import com.owaldron.frfilmcatalogue.Util.Constants;
import com.owaldron.frfilmcatalogue.Util.Prefs;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieRecyclerViewAdapter movieRecyclerViewAdapter;
    private List<Pokemon> pokemonList;
    private RequestQueue queue;
    private AlertDialog.Builder alertDialogueBuilder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queue= Volley.newRequestQueue(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });

        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pokemonList  = new ArrayList<>();

        Prefs prefs=new Prefs(MainActivity.this);
        String search = prefs.getSearch();

        pokemonList=getMovies(search);
        movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(this,pokemonList);
        recyclerView.setAdapter(movieRecyclerViewAdapter);
        movieRecyclerViewAdapter.notifyDataSetChanged();
    }

    public List<Pokemon> getMovies(String searchTerm) {
        pokemonList.clear();
        Log.d("URL: ",Constants.URL1 + searchTerm+"/");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL1 + searchTerm+"/",
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    Pokemon poke = new Pokemon();
                    String name = response.getString("name");
                    poke.setName(name.substring(0,1).toUpperCase()+name.substring(1,name.length()));
                    String species  = response.getJSONObject("species").getString("name");
                    poke.setMoto(species);
                    String poster = response.getJSONObject("sprites").getString("front_default");
                    poke.setSprite(poster);
                    JSONArray types = response.getJSONArray("types");
                    String type ="";
                    for (int i=0;i<types.length();i++) {
                        String temp = types.getJSONObject(i).getString("type");
                        if (i==0) {
                            type=temp.substring(0,1).toUpperCase()+temp.substring(1,temp.length());
                        } else {
                            type=", "+temp.substring(0,1).toUpperCase()+temp.substring(1,temp.length());
                        }
                    }
                    poke.setTypes(type);
                    poke.setPokeid(response.getInt("id"));
                    Log.d("Name: ",name);
                    pokemonList.add(poke);

                    // pour mettre à jour les résultats de la recherche
                    movieRecyclerViewAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);

        return pokemonList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}