package com.example.parcial2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<Character> characters;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Character character);
    }

    public CharacterAdapter(List<Character> characters, OnItemClickListener listener) {
        this.characters = characters;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characters.get(position);
        holder.bind(character, listener);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView, statusTextView, speciesTextView;

        CharacterViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.characterImage);
            nameTextView = itemView.findViewById(R.id.characterName);
            statusTextView = itemView.findViewById(R.id.characterStatus);
            speciesTextView = itemView.findViewById(R.id.characterSpecies);
        }

        void bind(final Character character, final OnItemClickListener listener) {
            nameTextView.setText(character.getName());
            statusTextView.setText(character.getStatus());
            speciesTextView.setText(character.getSpecies());
            Picasso.get().load(character.getImageUrl()).into(imageView);

            itemView.setOnClickListener(v -> listener.onItemClick(character));
        }
    }
}