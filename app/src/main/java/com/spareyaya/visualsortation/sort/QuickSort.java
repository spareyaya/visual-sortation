package com.spareyaya.visualsortation.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 功能描述：
 *
 * @author ONCEsama.M
 * Created on 2020/3/19.
 */
public class QuickSort implements Sort {

    @Override
    public ArrayList<int[]> sort(int[] data) {
        ArrayList<int[]> datas = new ArrayList<>();
        qSort(datas, data, 0, data.length - 1);
        return datas;
    }

    private void qSort(ArrayList<int[]> datas, int[] data, int low, int high) {
        if (low < high) {
            int pivot = partition(datas, data, low, high);
            qSort(datas, data, low, pivot - 1);
            qSort(datas, data, pivot + 1, high);
        }
    }

    private int partition(ArrayList<int[]> datas, int[] data, int low, int high) {
        int pivotKey = data[low];
        while (low < high) {
            while (low < high && data[high] < pivotKey) {
                high--;
            }
            data[low] = data[high];
            datas.add(Arrays.copyOf(data, data.length));

            while (low < high && data[low] >= pivotKey) {
                low++;
            }
            data[high] = data[low];
            datas.add(Arrays.copyOf(data, data.length));
        }
        data[low] = pivotKey;
        return low;
    }
}
