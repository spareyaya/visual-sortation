package com.spareyaya.visualsortation.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 功能描述：
 *
 * @author ONCEsama.M
 * Created on 2019/9/20.
 */
public class BubbleSort implements Sort {

    @Override
    public ArrayList<int[]> sort(int[] data) {
        ArrayList<int[]> datas = new ArrayList<>();
        boolean isSwap;
        int temp;
        for (int i = 0; i < data.length; i++) {
            isSwap = false;
            for (int j = data.length - 1; j > i; j--) {
                if (data[j] > data[j-1]) {
                    temp = data[j];
                    data[j] = data[j-1];
                    data[j-1] = temp;
                    isSwap = true;
                    datas.add(Arrays.copyOf(data, data.length));
                }
            }
            if (!isSwap) {
                break;
            }
        }
        return datas;
    }
}
