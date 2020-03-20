package com.spareyaya.visualsortation.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 功能描述：
 *
 * @author ONCEsama.M
 * Created on 2020/3/19.
 */
public class SelectionSort implements Sort {

    @Override
    public ArrayList<int[]> sort(int[] data) {
        ArrayList<int[]> datas = new ArrayList<>();
        int max, index;
        for (int i = 0; i < data.length; i++) {
            max = data[i];
            index = i;
            for (int j = i + 1; j < data.length; j++) {
                if (max < data[j]) {
                    max = data[j];
                    index = j;
                }
            }
            if (index != i) {
                data[index] = data[i];
                data[i] = max;
                datas.add(Arrays.copyOf(data, data.length));
            }
        }
        return datas;
    }
}
