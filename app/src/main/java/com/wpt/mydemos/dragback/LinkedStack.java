package com.wpt.mydemos.dragback;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by HanHailong on 2017/6/20.
 */

public class LinkedStack<K, V> {

    private LinkedList<K> mLinkedList = new LinkedList<K>();
    private LinkedHashMap<K, V> mTraceInfoHashMap = new LinkedHashMap<K, V>();

    public void put(K k, V v) {
        mLinkedList.add(k);
        mTraceInfoHashMap.put(k, v);
    }

    public void remove(K k) {
        mLinkedList.remove(k);
        mTraceInfoHashMap.remove(k);
    }

    public K before(K k) {
        int index = mLinkedList.indexOf(k);
        if (index < 1)
            return null;
        return mLinkedList.get(index - 1);
    }

    public V get(K k) {
        return mTraceInfoHashMap.get(k);
    }

    public K getKey(int index) {
        return mLinkedList.get(index);
    }

    public int size() {
        return mLinkedList.size();
    }
}
