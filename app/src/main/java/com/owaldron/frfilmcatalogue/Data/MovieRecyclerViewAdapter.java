package com.owaldron.frfilmcatalogue.Data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.owaldron.frfilmcatalogue.Activities.DetailsActivity;
import com.owaldron.frfilmcatalogue.Model.Pokemon;
import com.owaldron.frfilmcatalogue.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Pokemon> pokemons;
    public MovieRecyclerViewAdapter(Context context, List<Pokemon> pokes) {
        this.context=context;
        pokemons=pokes;
    }

    @NonNull
    @Override
    public MovieRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewAdapter.ViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        String spriteLink = pokemon.getSprite();

        holder.title.setText(pokemon.getName());
        holder.type.setText(pokemon.getTypes());

        Picasso.get().setLoggingEnabled(true);
        Picasso.get()
                .load("https://cdn.traction.one/pokedex/pokemon/"+pokemon.getPokeid()+".png")
                .fit()
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(holder.poster);

        holder.moto.setText(pokemon.getMoto());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView poster;
        TextView moto;
        TextView type;

        public ViewHolder(@NonNull View itemView, final Context ctx) {
            super(itemView);
            context=ctx;

            title = itemView.findViewById(R.id.pokemonName);
            poster = itemView.findViewById(R.id.pokeSprite);
            moto = itemView.findViewById(R.id.pokeMoto);
            type = itemView.findViewById(R.id.movieCatID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pokemon poke = pokemons.get(getAdapterPosition());

                    Intent intent = new Intent (context, DetailsActivity.class);

                    intent.putExtra("pokemon",poke);
                    ctx.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
