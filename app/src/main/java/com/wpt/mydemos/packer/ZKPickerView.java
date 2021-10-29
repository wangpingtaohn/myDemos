package com.wpt.mydemos.packer;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.wpt.mydemos.R;

import java.util.List;

/**
 * Author: wpt
 * Time: 2021/10/28
 *
 * @Desc：
 */
public class ZKPickerView {

    public static<T> void showPicker(Context context,String title,List<T> list, OnOptionsSelectListener listener){
        showPicker(context,title,list,null,listener);
    }

    public static<T> void showPicker(Context context,String title,List<T> list,List<List<T>> list2, OnOptionsSelectListener listener){
        showPicker(context,title,list,list2,null,listener);
    }

    public static<T> void showPicker(Context context,String title,List<T> list,List<List<T>> list2, List<List<List<T>>> list3,OnOptionsSelectListener listener){
        int op1= 0;
        int op2 = 0;
        int op3= 0;
        View view= null;
        OptionsPickerView customOptions = null;
        OptionsPickerView finalCustomOptions = customOptions;
        OptionsPickerView pvCustomOptions = new OptionsPickerBuilder(context, listener)
                .setLayoutRes(R.layout.pickerview_custom_options, v -> {
                    //自定义布局中的控件初始化及事件处理
                    TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
//                        final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
//                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                    tvSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finalCustomOptions.returnData();
                        }
                    });
                    /*ivCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pvCustomOptions.dismiss();
                        }
                    });*/

                    /*tvAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                                getData();
                            pvCustomOptions.setPicker(cardItem);
                        }
                    });*/

                })
                .build();
        pvCustomOptions.setPicker(list,list2,list3);//添加数据
        customOptions = pvCustomOptions;
        pvCustomOptions.show();
    }



}
