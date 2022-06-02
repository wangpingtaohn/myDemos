package com.wpt.javalib;

import java.util.HashMap;

public class JavaMain {

    public static final String HAND_RING = "R";
    public static final String HORIZONTAL_BAR = "B";

    public static void main(String[] args) {

        //type:R--吊环，type:B--单杠
        String male = "type:R;difficultDegree:1.8;qualityScore:9.2;errorScore:2.3";
        String female = "type:B;difficultDegree:1.7;qualityScore:9.7;errorScore:1.9";

        HashMap<String,String> maleMap = getMap(male);
        HashMap<String,String> femaleMap = getMap(female);
        System.out.println("男生的" + getGymnasticName(maleMap) + "分数为：" + getScore(maleMap));
        System.out.println("女生的" + getGymnasticName(femaleMap) + "分数为：" + getScore(femaleMap));
    }

    private static String getGymnasticName(HashMap<String,String> map){
        String gymnasticName = null;
        if (HAND_RING.equals(map.get("type"))){
            gymnasticName = "吊环";
        } else {
            gymnasticName = "单杠";
        }
        return gymnasticName;
    }

    private static double getScore(HashMap<String,String> map){
        Gymnastic gymnastic;
        double difficultDegree = Double.valueOf(map.get("difficultDegree"));
        double qualityScore = Double.valueOf(map.get("qualityScore"));
        double errorScore = Double.valueOf(map.get("errorScore"));
        if (HAND_RING.equals(map.get("type"))){
            gymnastic = new HandRing(difficultDegree,qualityScore,errorScore);
        } else {
            gymnastic = new HorizontalBar(difficultDegree,qualityScore,errorScore);
        }
        return gymnastic.getFinalScore();

    }

    private static HashMap<String,String> getMap(String content){
        HashMap<String,String> map = new HashMap<>();
        if (content == null || content.equals("")){
            return map;
        }
        String[] contents = content.split(";");
        for (String str: contents){
            String[] values = str.split(":");
            map.put(values[0],values[1]);
        }
//        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
//        while (entries.hasNext()) {
//            Map.Entry<String, String> entry = entries.next();
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
        return map;
    }
}