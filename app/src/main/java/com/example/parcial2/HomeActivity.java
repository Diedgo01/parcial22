package com.example.parcial2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements CharacterAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private CharacterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Character> characters = getCharacters(); // Método para obtener los personajes
        adapter = new CharacterAdapter(characters, this);
        recyclerView.setAdapter(adapter);
    }

    private List<Character> getCharacters() {
        List<Character> characters = new ArrayList<>();
        characters.add(new Character("Rick Sanchez", "Alive", "Human", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"));
        characters.add(new Character("Morty Smith", "Alive", "Human", "https://rickandmortyapi.com/api/character/avatar/2.jpeg"));
        characters.add(new Character("Summer Smith", "Alive", "Human", "https://rickandmortyapi.com/api/character/avatar/3.jpeg"));
        characters.add(new Character("Beth Smith", "Alive", "Human", "https://rickandmortyapi.com/api/character/avatar/4.jpeg"));
        characters.add(new Character("Jerry Smith", "Alive", "Human", "https://rickandmortyapi.com/api/character/avatar/5.jpeg"));
        return characters;
    }

    @Override
    public void onItemClick(Character character) {
        Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
        intent.putExtra("character", character);
        startActivity(intent);
    }
}