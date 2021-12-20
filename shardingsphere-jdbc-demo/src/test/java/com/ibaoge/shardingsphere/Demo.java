package com.ibaoge.shardingsphere;

import java.util.Arrays;
import java.util.Comparator;

public class Demo {

    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, Comparator.comparingInt(task -> task[0] - task[1]));

        return 0;
    }
}
