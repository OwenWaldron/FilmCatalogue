package com.owaldron.frfilmcatalogue.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.owaldron.frfilmcatalogue.Model.Pokemon;
import com.owaldron.frfilmcatalogue.R;
import com.owaldron.frfilmcatalogue.Util.Constants;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {
    private Pokemon pokemon;
    private ImageView pokeImageDet;
    private TextView pokeTitleDet;
    private TextView pokeSpeciesDet;
    private TextView pokeTypesDet;
    private TextView pokeGen;
    private TextView pokeDesc;
    private TextView pokeHeight;
    private TextView pokeWeight;
    private RequestQueue requestQueue;
    private int pokeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        requestQueue = Volley.newRequestQueue(this);

        pokemon = (Pokemon) getIntent().getSerializableExtra("pokemon");
        pokeId = pokemon.getPokeid();

        SetUpUI();
        getPokeDetails(pokeId);
    }

    private void SetUpUI ()
    {
        pokeImageDet = findViewById(R.id.pokeImgDet);
        pokeTitleDet = findViewById(R.id.pokeTitleDet);
        pokeSpeciesDet = findViewById(R.id.pokeSpeciesDet);
        pokeTypesDet = findViewById(R.id.pokeTypesDet);
        pokeGen = findViewById(R.id.pokeGen);
        pokeDesc = findViewById(R.id.pokeDesc);
        pokeHeight = findViewById(R.id.pokeHeight);
        pokeWeight = findViewById(R.id.pokeWeight);
    }

    private void getPokeDetails(int id)
    {
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET,
                Constants.URL3 + Integer.toString(id),
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {

                try {
                    JSONObject response = jsonArray.getJSONObject(0);
                    Pokemon poke = new Pokemon();
                    String name = response.getString("name");
                    poke.setName(name);
                    String species  = response.getString("species");
                    poke.setMoto(species);
                    String poster = response.getString("sprite");
                    poke.setSprite(poster);
                    JSONArray types = response.getJSONArray("types");
                    String type ="";
                    for (int i=0;i<types.length();i++) {
                        String temp = types.getString(i);
                        if (i==0) {
                            type = "Types: " + temp;
                        } else {
                            type = type + ", " + temp;
                        }
                    }
                    poke.setTypes(type);
                    poke.setDesc(response.getString("description"));
                    poke.setPokeid(response.getInt("number"));
                    int gen = response.getInt("gen");
                    poke.setGen(gen);
                    poke.setHeight(response.getString("height"));
                    poke.setWeight(response.getString("weight"));
                    Log.d("Name: ",name);
                    Log.d("Types: ",type);
                    Log.d("Species: ",species);

                    pokeTitleDet.setText(poke.getName());
                    pokeSpeciesDet.setText("Species: "+poke.getMoto());
                    pokeTypesDet.setText("Types: " + poke.getTypes());
                    pokeGen.setText("Generation: " + poke.getGen());
                    pokeDesc.setText("Description: " + poke.getDesc());
                    pokeHeight.setText("Height: " + poke.getHeight());
                    pokeWeight.setText("Weight: " + poke.getWeight());

                    Picasso.get()
                            .load(poke.getSprite())
                            .fit()
                            .into(pokeImageDet);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}