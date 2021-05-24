package com.codershil.algorithmvisualizerdemo.visualilzer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.View;

import com.codershil.algorithmvisualizerdemo.R;

import java.util.Random;

public class MyView extends View {

    Paint mPaint , outerPaint ;
    DisplayMetrics displayMetrics;
    final Context context;
    Activity activity;
     float screenWidth ;
     float screenHeight;
     float startX ;
     float startY ;
     float lineGap;
     int highPos1 = -1, highPos2 = -1 ;
     public static int sizeOfArray = 15;
    public static boolean firstTimeDraw = true ;
    int[] randomArray = new int[sizeOfArray];

    // global variables for bubble sort
    public int i , j ;

    public MyView(Context context) {
        super(context);
        this.context = context;
        this.activity = (Activity) context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getResources().getColor(R.color.purple_500));
        mPaint.setStrokeWidth(20);

        outerPaint = new Paint();
        outerPaint.setStyle(Paint.Style.FILL);
        outerPaint.setColor(Color.YELLOW);

        displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        // getting the screenWidth and screenHeight using the displayMetrics class
         screenWidth = displayMetrics.widthPixels;
         screenHeight = displayMetrics.heightPixels;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(outerPaint);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        startX = 10;
        startY = 10;
        lineGap = 1;

        if (firstTimeDraw) {
            i= 0;
            j= 0;
            randomArray = generateRandomArray(sizeOfArray);
            drawLines(canvas);
            firstTimeDraw = false;
        }
        else {
                screenWidth = screenWidth - (sizeOfArray * lineGap);
                mPaint.setStrokeWidth(screenWidth / sizeOfArray);
                if (i<sizeOfArray){

                    for (j= 0 ; j<sizeOfArray-i-1; j++){
                        if (randomArray[j]>randomArray[j+1]){

                            highPos1 = j;
                            highPos2 = j+1;
                            int temp = randomArray[j] ;
                            randomArray[j] = randomArray[j+1];
                            randomArray[j+1]  = temp ;

                        }
                        synchronized (context)
                        {
                            for (int l = 0; l < sizeOfArray; l++) {
                                if (l == highPos1){
                                    mPaint.setColor(Color.RED);
                                    canvas.drawLine(startX, startY, startX, (randomArray[l]) * ( screenHeight / sizeOfArray+1), mPaint);
                                    startX += (screenWidth / sizeOfArray) + lineGap;
                                    mPaint.setColor(getResources().getColor(R.color.purple_500));
                                }
                                canvas.drawLine(startX, startY, startX, (randomArray[l]) * ( screenHeight / sizeOfArray+1), mPaint);
                                startX += (screenWidth / sizeOfArray) + lineGap;
                            }

                        }

                    }
                    i++;
                    invalidate();
                }

                else {
                    screenWidth = displayMetrics.widthPixels;
                    screenHeight = displayMetrics.heightPixels;
                    i=0;j=0;
                    drawLines(canvas);
                }


            }
    }




    public int[] generateRandomArray(int size){
        int[] randomArray = new int[size];
        Random rnd = new Random();

        for (int i=0; i<size; i++){
            int randomNumber = rnd.nextInt(size);
            if (randomNumber == 0){
                randomArray[i] = 1;
            }
            else if (randomNumber == size-1){
                    randomArray[i] = randomNumber - rnd.nextInt(randomNumber / 2);
            }
            else {
                randomArray[i] = randomNumber + 1;
            }
        }
        return randomArray;
    }

    public void drawLines(Canvas canvas){
        startX = 10;
        startY = 10;
        lineGap = 1;
        screenWidth = screenWidth - (sizeOfArray * lineGap);
        mPaint.setStrokeWidth(screenWidth / sizeOfArray);
        for (int l = 0; l < sizeOfArray; l++) {
            canvas.drawLine(startX, startY, startX, (randomArray[l]) * ( screenHeight / sizeOfArray+1), mPaint);
            startX += (screenWidth / sizeOfArray) + lineGap;
        }
    }


}