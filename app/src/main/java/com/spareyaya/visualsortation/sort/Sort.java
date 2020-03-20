package com.spareyaya.visualsortation.sort;

import java.util.ArrayList;

/**
 * 功能描述：
 *
 * @author ONCEsama.M
 * Created on 2019/9/20.
 */
public interface Sort {

    /**
     * 把数据按非递增排序
     *
     * @param data 待排序的数据
     *
     * @return 返回排序中间值的数组集合
     * */
    ArrayList<int[]> sort(int[] data);

}
