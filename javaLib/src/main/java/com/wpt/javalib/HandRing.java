package com.wpt.javalib;

/**
 * Author: wpt
 * Time: 2022/6/2
 *
 * @Desc：吊环类
 */
public class HandRing extends Gymnastic{

    public HandRing(double difficultDegree, double qualityScore, double errorScore) {
        super(difficultDegree, qualityScore, errorScore);
    }

    @Override
    double getFinalScore() {
        return (qualityScore - errorScore * 0.7) * difficultDegree;
    }
}
