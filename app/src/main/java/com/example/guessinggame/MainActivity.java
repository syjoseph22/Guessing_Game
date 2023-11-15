package com.example.guessinggame;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowCompat;

import com.example.guessinggame.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private GuessingGame mGame;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("GAME", mGame.getJSONStringFromThis());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        setSupportActionBar(binding.toolbar);
        setupButtonListeners();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            mGame.startNewGame();
            Snackbar.make(findViewById(R.id.content_main), "A new game has started.", Snackbar.LENGTH_SHORT).show();
        });

        mGame = savedInstanceState != null
                ? GuessingGame.getGuessingGameObjectFromJSONString(
                savedInstanceState.getString("GAME"))
                : new GuessingGame();
    }

    private void setContentView() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void setupButtonListeners() {
        findViewById(R.id.button1).setOnClickListener(this::handleButton123Click);
        findViewById(R.id.button2).setOnClickListener(this::handleButton123Click);
        findViewById(R.id.button3).setOnClickListener(this::handleButton123Click);
    }

    public void handleButton123Click(View view) {
        Button currentButton = (Button) view;
        String currentButtonText = currentButton.getText().toString();
        String s = "";
        if (currentButtonText.equals(String.valueOf(mGame.getWinningNumber()))) {
            s += "Correct! " + currentButtonText + " is the winning number!";
        } else {
            s += "Sorry. " + currentButtonText + " is not the winning number.";
        }
        Snackbar.make(view, s, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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