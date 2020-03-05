package com.wpt.mydemos.map;

interface HttpCallBack<T> {

  void onSuccess(T result);

  void onFailed(String msg,int error);
}