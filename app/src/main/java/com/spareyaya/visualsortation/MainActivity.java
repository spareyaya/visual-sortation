package com.spareyaya.visualsortation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.spareyaya.visualsortation.sort.BubbleSort;
import com.spareyaya.visualsortation.sort.HeapSort;
import com.spareyaya.visualsortation.sort.InsertionSort;
import com.spareyaya.visualsortation.sort.MergeSort;
import com.spareyaya.visualsortation.sort.QuickSort;
import com.spareyaya.visualsortation.sort.SelectionSort;
import com.spareyaya.visualsortation.sort.ShellSort;
import com.spareyaya.visualsortation.sort.Sort;
import com.spareyaya.visualsortation.view.DataView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "MainActivity";

    private final int BUBBLE = 0;
    private final int INSERTION = 1;
    private final int QUICK = 2;
    private final int HEAP = 3;
    private final int SHELL = 4;
    private final int MERGE = 5;
    private final int SELECTION = 6;

    private int size = 100;
    private final int MAX = 300;

    private int[] originalData;
    private int[] currentData;
    private ArrayList<int[]> dataFrames;
    private Random random;

    private DataView dataView;
    private AppCompatSpinner spinner;
    private AppCompatSpinner numSpinner;
    private Button changeBtn;
    private Button resetBtn;
    private Button sortBtn;

    private Sort sort;
    private int sortAlgorithm = BUBBLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataView = findViewById(R.id.data_view);
        spinner = findViewById(R.id.spinner);
        numSpinner = findViewById(R.id.spinner_num);
        changeBtn = findViewById(R.id.change);
        resetBtn = findViewById(R.id.reset);
        sortBtn = findViewById(R.id.sort);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sortAlgorithm = position;
                reset();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        numSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] numberArray = getResources().getStringArray(R.array.numbers);
                size = Integer.valueOf(numberArray[position]);
                change();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        changeBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        sortBtn.setOnClickListener(this);

        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change:
                change();
                break;
            case R.id.reset:
                reset();
                break;
            case R.id.sort:
                sort();
                break;
        }

    }

    private void init() {
        numSpinner.setSelection(1);
        originalData = new int[size];
        currentData = new int[size];
        dataFrames = new ArrayList<>();
        random = new Random();
        for (int i = 0; i < size; i++) {
            originalData[i] = random.nextInt(MAX) + 1;
            currentData[i] = originalData[i];
        }
        dataView.setMax(MAX);
        dataFrames.add(currentData);
        dataView.setDataFrames(dataFrames);
    }

    private void change() {
        if (dataView.isRunning()) {
            Toast.makeText(this, "正在排序...", Toast.LENGTH_SHORT).show();
            return;
        }
        originalData = new int[size];
        for (int i = 0; i < size; i++) {
            originalData[i] = random.nextInt(MAX) + 1;
        }
        currentData = Arrays.copyOf(originalData, size);
        dataFrames.clear();
        dataFrames.add(currentData);
        dataView.setDataFrames(dataFrames);
    }

    private void reset() {
        if (dataView.isRunning()) {
            Toast.makeText(this, "正在排序...", Toast.LENGTH_SHORT).show();
            return;
        }
        currentData = Arrays.copyOf(originalData, size);
        dataFrames.clear();
        dataFrames.add(currentData);
        dataView.setDataFrames(dataFrames);
    }

    private void sort() {
        if (dataView.isRunning()) {
            Toast.makeText(this, "正在排序...", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (sortAlgorithm) {
            case BUBBLE:
                sort = new BubbleSort();
                break;
            case INSERTION:
                sort = new InsertionSort();
                break;
            case QUICK:
                sort = new QuickSort();
                break;
            case HEAP:
                sort = new HeapSort();
                break;
            case SHELL:
                sort = new ShellSort();
                break;
            case MERGE:
                sort = new MergeSort();
                break;
            case SELECTION:
                sort = new SelectionSort();
                break;
        }

        if (sort != null) {
            reset();
            dataFrames = sort.sort(currentData);
            dataView.startAnim(dataFrames);
        } else {
            Toast.makeText(this, "该算法尚未实现", Toast.LENGTH_SHORT).show();
        }

    }

}
