package com.owaldron.frfilmcatalogue.Data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.owaldron.frfilmcatalogue.Model.Pokemon;
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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewAdapter.ViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        String spriteLink = pokemon.getSprite();

        holder.title.setText(pokemon.getName());
        holder.type.setText(pokemon.getMoto());

        Picasso.get()
                .load(spriteLink)
                .fit()
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(holder.poster);
        holder.year.setText(pokemon.getTypes());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView poster;
        TextView year;
        TextView type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
