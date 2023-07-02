package com.example.labyrinth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean gameIsRunning = false;

    Modele monModele;

    private Handler myHandler;
    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            if(gameIsRunning) {
                vueJeu.invalidate();
            }

            myHandler.postDelayed(this, 100);
        }
    };

    ZoneDessin vueJeu;
    LinearLayout zoneJeu;
    ImageView  gaucheBouton;
    ImageView  hautBouton;
    ImageView  droiteBouton;
    ImageView  enbasBouton;
    ImageView  rejouerBouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monModele = new Modele(this);

        gaucheBouton = (ImageView) findViewById(R.id.gaucheBouton);
        gaucheBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameIsRunning)
                    monModele.bougerGauche();
            }
        });
        hautBouton = (ImageView ) findViewById(R.id.hautBouton);
        hautBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameIsRunning)
                    monModele.bougerHaut();
            }
        });

        droiteBouton = (ImageView ) findViewById(R.id.droiteBouton);
        droiteBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameIsRunning)
                    monModele.bougerDroite();
            }
        });

        enbasBouton = (ImageView ) findViewById(R.id.enbasBouton);
        enbasBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameIsRunning)
                    monModele.bougerEnBas();
            }
        });

        //Rejour bouton
        rejouerBouton = (ImageView ) findViewById(R.id.rejourBouton);
        rejouerBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monModele.rebuildLabyrinthe();
                startGame();
            }
        });

        vueJeu = new ZoneDessin(this, monModele);
        zoneJeu = (LinearLayout) findViewById(R.id.zoneJeu);
        zoneJeu.addView(vueJeu);

        startGame();
    }

    public boolean isGameIsRunning(){
        return gameIsRunning;
    }

    public void startGame(){
        rejouerBouton.setVisibility(View.GONE);
        gameIsRunning = true;
        myHandler = new Handler();
        myHandler.postDelayed(myRunnable, 100);
    }
    public void stopGame(){
        rejouerBouton.setVisibility(View.VISIBLE);
        gameIsRunning = false;
        if(myHandler != null)
            myHandler.removeCallbacks(myRunnable);
    }
}