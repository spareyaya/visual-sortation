package com.spareyaya.visualsortation.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 功能描述：
 *
 * @author ONCEsama.M
 * Created on 2020/3/19.
 */
public class ShellSort implements Sort {

    @Override
    public ArrayList<int[]> sort(int[] data) {
        ArrayList<int[]> datas = new ArrayList<>();
        for (int gap = data.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < data.length; i++) {
                int j = i;
                int temp = data[j];
                if (data[j] > data[j - gap]) {
                    while (j - gap >= 0 && temp > data[j - gap]) {
                        data[j] = data[j - gap];
                        j -= gap;
                        datas.add(Arrays.copyOf(data, data.length));
                    }
                    data[j] = temp;
                    datas.add(Arrays.copyOf(data, data.length));
                }
            }
        }
        return datas;
    }
}
