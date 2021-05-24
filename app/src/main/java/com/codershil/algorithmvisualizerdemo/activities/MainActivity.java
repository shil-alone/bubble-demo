package com.codershil.algorithmvisualizerdemo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.codershil.algorithmvisualizerdemo.utilities.DataUtils;
import com.codershil.algorithmvisualizerdemo.R;
import com.codershil.algorithmvisualizerdemo.algorithms.BubbleSort;
import com.codershil.algorithmvisualizerdemo.visualilzer.MyView;
import com.codershil.algorithmvisualizerdemo.visualilzer.SortingVisualizer;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout ;
    Button btnShuffle ;
    Button btnBubbleSort ;
    SeekBar seekBar;
    SeekBar seekBarTime;
    int sizeOfArray = 40;
    BubbleSort bubbleSort ;
    SortingVisualizer sortingVisualizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frameLayout);
        btnShuffle = findViewById(R.id.btnShuffle);
        btnBubbleSort = findViewById(R.id.btnBubbleSort);
        seekBar = findViewById(R.id.seekBar);
        seekBarTime= findViewById(R.id.seekBarTime);

        sortingVisualizer = new SortingVisualizer(MainActivity.this);
        bubbleSort = new BubbleSort(sortingVisualizer,sizeOfArray,MainActivity.this);
        frameLayout.addView(sortingVisualizer);
        seekBar.setMax(200);
        seekBar.setProgress(this.sizeOfArray);
        seekBarTime.setMax(1000);
        seekBarTime.setProgress(bubbleSort.getTime());
        initialize();
    }

    public void initialize(){

        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bubbleSort.setSorting(true);
                seekBarTime.setProgress(bubbleSort.getTime());
                sortingVisualizer.setRandomArray(DataUtils.generateRandomArray(sizeOfArray));
                bubbleSort = new BubbleSort(sortingVisualizer,sizeOfArray,MainActivity.this);
                sortingVisualizer.invalidate();
            }
        });

        btnBubbleSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bubbleSort.setSorting(false);
                bubbleSort.sort();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                bubbleSort.setSorting(true);
                sizeOfArray = progress;
                bubbleSort = new BubbleSort(sortingVisualizer,sizeOfArray,MainActivity.this);
                sortingVisualizer.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress<=0){
                    bubbleSort.setTime(0);
                }
                else {
                    bubbleSort.setTime(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}