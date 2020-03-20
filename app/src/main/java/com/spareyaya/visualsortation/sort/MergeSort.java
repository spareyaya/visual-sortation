package com.spareyaya.visualsortation.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 功能描述：
 *
 * @author ONCEsama.M
 * Created on 2020/3/19.
 */
public class MergeSort implements Sort {

    @Override
    public ArrayList<int[]> sort(int[] data) {
        ArrayList<int[]> datas = new ArrayList<>();
        int[] temp = new int[data.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(datas, data, 0, data.length - 1, temp);
        return datas;
    }

    private void sort(ArrayList<int[]> datas, int[] data, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(datas, data, left, mid, temp);//左边归并排序，使得左子序列有序
            sort(datas, data, mid + 1, right, temp);//右边归并排序，使得右子序列有序
            merge(datas, data, left, mid, right, temp);//将两个有序子数组合并操作
        }
    }

    private void merge(ArrayList<int[]> datas, int[] data, int left, int mid, int right, int[] temp) {
        int i = left;//左序列指针
        int j = mid + 1;//右序列指针
        int t = 0;//临时数组指针
        while (i <= mid && j <= right) {
            if (data[i] >= data[j]) {
                temp[t++] = data[i++];
            } else {
                temp[t++] = data[j++];
            }
        }
        while (i <= mid) {//将左边剩余元素填充进temp中
            temp[t++] = data[i++];
        }
        while (j <= right) {//将右序列剩余元素填充进temp中
            temp[t++] = data[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            data[left++] = temp[t++];
            datas.add(Arrays.copyOf(data, data.length));
        }
    }
}
