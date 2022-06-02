package com.wpt.javalib;

/**
 * Author: wpt
 * Time: 2022/6/2
 *
 * @Desc： 单杠类
 */
public class HorizontalBar extends Gymnastic{

    public HorizontalBar(double difficultDegree, double qualityScore, double errorScore) {
        super(difficultDegree, qualityScore, errorScore);
    }

    @Override
    double getFinalScore() {
        return qualityScore * difficultDegree - errorScore;
    }
}
