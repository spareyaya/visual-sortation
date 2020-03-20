package com.spareyaya.visualsortation.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 功能描述：
 *
 * @author ONCEsama.M
 * Created on 2020/3/19.
 */
public class InsertionSort implements Sort {

    @Override
    public ArrayList<int[]> sort(int[] data) {
        ArrayList<int[]> datas = new ArrayList<>();
        int temp;
        for (int i = 1; i < data.length; i++) {
            temp = data[i];
            for (int j = i - 1; j >= -1; j--) {
                if (j == -1) {
                    data[0] = temp;
                    datas.add(Arrays.copyOf(data, data.length));
                    break;
                }
                if (temp > data[j]) {
                    data[j+1] = data[j];
                    datas.add(Arrays.copyOf(data, data.length));
                } else {
                    data[j+1] = temp;
                    datas.add(Arrays.copyOf(data, data.length));
                    break;
                }
            }
        }
        return datas;
    }
}
