package com.wpt.javalib;

/**
 * Author: wpt
 * Time: 2022/6/2
 *
 * @Desc：
 */
public abstract class Gymnastic {

    protected double difficultDegree;
    protected double qualityScore;
    protected double errorScore;

    public Gymnastic(double difficultDegree,double qualityScore, double errorScore){
        this.difficultDegree = difficultDegree;
        this.qualityScore = qualityScore;
        this.errorScore = errorScore;
    }


    abstract double getFinalScore();
}
