package com.spareyaya.visualsortation.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 功能描述：
 *
 * @author ONCEsama.M
 * Created on 2020/3/19.
 */
public class HeapSort implements Sort {

    @Override
    public ArrayList<int[]> sort(int[] data) {
        ArrayList<int[]> datas = new ArrayList<>();
        for (int i = data.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            heapAdjust(datas, data, i, data.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        int temp;
        for (int j = data.length - 1; j > 0; j--) {
            //将堆顶元素与末尾元素进行交换
            temp = data[0];
            data[0] = data[j];
            data[j] = temp;
            datas.add(Arrays.copyOf(data, data.length));

            heapAdjust(datas, data, 0, j);//重新对堆进行调整
        }
        return datas;
    }

    private void heapAdjust(ArrayList<int[]> datas, int[] data, int i, int length) {
        int temp = data[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && data[k] > data[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (data[k] < temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                data[i] = data[k];
                i = k;
                datas.add(Arrays.copyOf(data, data.length));
            } else {
                break;
            }
        }
        data[i] = temp;//将temp值放到最终的位置
        datas.add(Arrays.copyOf(data, data.length));
    }
}
