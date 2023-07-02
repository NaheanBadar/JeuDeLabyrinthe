package com.example.labyrinth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.labyrinth.Labyrinthe.Directions;

public class ZoneDessin extends View implements Directions{
    Paint paint = new Paint();

    Bitmap player;

    Modele monModele;

    public ZoneDessin(Context context, Modele modele){
        super(context);

        this.monModele = modele;
        paint.setStrokeWidth(3);

        Bitmap playerOriginal = BitmapFactory.decodeResource(context.getResources(), R.drawable.personnage);
        player = Bitmap.createScaledBitmap(playerOriginal, 100, 100, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        clear(canvas);
        drawBorder(canvas);
        drawLabyrinth(canvas);
        drawPlayer(canvas);
    }

    void drawPlayer(Canvas canvas){
        canvas.drawBitmap(player, monModele.getPlayerPosX()*monModele.getCaseSize(), monModele.getPlayerPosY()*monModele.getCaseSize(), paint);
    }

    void drawLabyrinth(Canvas canvas){
        int[][] lab = monModele.getTab();
        int dimension = monModele.getDimension();
        int caseSize = monModele.getCaseSize();

        for(int i=0; i<lab.length; i++){
            if(lab[i][NORD] == -1){
                canvas.drawLine((i%dimension)*caseSize,(i/dimension)*caseSize,(i%dimension)*caseSize+caseSize,(i/dimension)*caseSize, paint);
            }
            if(lab[i][OUEST] == -1){
                canvas.drawLine((i%dimension)*caseSize,(i/dimension)*caseSize,(i%dimension)*caseSize,(i/dimension)*caseSize+caseSize, paint);
            }
        }
    }

    void drawBorder(Canvas canvas){
        int dimension = monModele.getDimension();
        int caseSize = monModele.getCaseSize();

        canvas.drawLine(0, 0, caseSize*dimension, 0, paint); // up
        canvas.drawLine(0, 0, 0, caseSize*dimension, paint); // left
        canvas.drawLine(caseSize*dimension, 0, caseSize*dimension, caseSize*dimension, paint); //right
        canvas.drawLine(0, caseSize*dimension, caseSize*dimension - caseSize, caseSize*dimension, paint); //down
    }

    void clear(Canvas canvas){
        canvas.drawColor(Color.WHITE);
    }
}
