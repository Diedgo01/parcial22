package com.example.parcial2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView nameTextView, statusTextView, speciesTextView;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.detailImage);
        nameTextView = findViewById(R.id.detailName);
        statusTextView = findViewById(R.id.detailStatus);
        speciesTextView = findViewById(R.id.detailSpecies);
        logoutButton = findViewById(R.id.logoutButton);

        Character character = (Character) getIntent().getSerializableExtra("character");
        if (character != null) {
            Picasso.get().load(character.getImageUrl()).into(imageView);
            nameTextView.setText(character.getName());
            statusTextView.setText(character.getStatus());
            speciesTextView.setText(character.getSpecies());
        }

        logoutButton.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(DetailActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}