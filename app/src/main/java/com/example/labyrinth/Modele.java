package com.example.labyrinth;

import androidx.annotation.NonNull;

import com.example.labyrinth.Labyrinthe.Directions;
import com.example.labyrinth.Labyrinthe.Labyrinthe;

import java.io.Serializable;

public class Modele implements Directions {

    private int dimension;
    private int caseSize;
    private Labyrinthe labyrinthe;
    private int tab[][];
    private int playerPosX;
    private int playerPosY;
    private MainActivity mainActivity;

    public Modele(MainActivity mainActivity){
        dimension = 10;
        caseSize = 100;
        labyrinthe = new Labyrinthe(dimension);
        tab = labyrinthe.getTab();
        this.playerPosX = 0;
        this.playerPosY = 0;

        //we can win at last column and last row, so let's change tab
        tab[dimension*dimension-1][SUD] = 1;

        this.mainActivity = mainActivity;
    }

    public void rebuildLabyrinthe(){
        labyrinthe = new Labyrinthe(dimension);
        tab = labyrinthe.getTab();
        tab[dimension*dimension-1][SUD] = 1;

        playerPosX = 0;
        playerPosY = 0;
    }

    public void bougerHaut() {
        if(tab[playerPosY*dimension+playerPosX][NORD] != -1)
            this.playerPosY -= 1;
    }
    public void bougerDroite() {
        if(tab[playerPosY*dimension+playerPosX][EST] != -1)
            this.playerPosX += 1;
    }
    public void bougerEnBas() {
        if(tab[playerPosY*dimension+playerPosX][SUD] != -1) {
            this.playerPosY += 1;
            if (playerPosY >= dimension)
                mainActivity.stopGame();
        }
    }
    public void bougerGauche() {
        if(tab[playerPosY*dimension+playerPosX][OUEST] != -1)
            this.playerPosX -= 1;
    }

    public int getPlayerPosX() {
        return playerPosX;
    }

    public int getPlayerPosY() {
        return playerPosY;
    }

    public int getDimension() {
        return dimension;
    }

    public int getCaseSize() {
        return caseSize;
    }

    public int[][] getTab() {
        return tab;
    }
}
