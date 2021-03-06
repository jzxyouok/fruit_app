package com.naoh.Fruit.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.naoh.Fruit.Data.Data;
import com.example.administrator.myapplication.R;

import java.util.HashMap;

/**
 * Created by NaOH on 2016/4/19.
 */
public class DetailPopWindow implements View.OnClickListener {

    private TextView pop_choice_16g, pop_choice_32g, pop_choice_16m, pop_choice_32m, pop_choice_black, pop_choice_white, pop_add, pop_reduce, pop_num, pop_ok;
    private ImageView pop_del;

    private PopupWindow popupWindow;
    private OnPopWindowClickListener listener;
    private final int ADDORREDUCE = 1;
    private Context context;
    /**
     * 保存选择的颜色的数据
     */
    private String str_color = "";
    /**
     * 保存选择的类型的数据
     */
    private String str_type = "";

    public DetailPopWindow(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.popwindow_detail, null);
        pop_choice_16g = (TextView) view.findViewById(R.id.pop_choice_16g);
        pop_choice_32g = (TextView) view.findViewById(R.id.pop_choice_32g);
        pop_choice_16m = (TextView) view.findViewById(R.id.pop_choice_16m);
        pop_choice_32m = (TextView) view.findViewById(R.id.pop_choice_32m);
        pop_choice_black = (TextView) view.findViewById(R.id.pop_choice_black);
        pop_choice_white = (TextView) view.findViewById(R.id.pop_choice_white);
        pop_add = (TextView) view.findViewById(R.id.pop_add);
        pop_reduce = (TextView) view.findViewById(R.id.pop_reduce);
        pop_num = (TextView) view.findViewById(R.id.pop_num);
        pop_ok = (TextView) view.findViewById(R.id.pop_ok);
        pop_del = (ImageView) view.findViewById(R.id.pop_del);

        pop_choice_16g.setOnClickListener(this);
        pop_choice_32g.setOnClickListener(this);
        pop_choice_16m.setOnClickListener(this);
        pop_choice_32m.setOnClickListener(this);
        pop_choice_black.setOnClickListener(this);
        pop_choice_white.setOnClickListener(this);
        pop_add.setOnClickListener(this);
        pop_reduce.setOnClickListener(this);
        pop_ok.setOnClickListener(this);
        pop_del.setOnClickListener(this);

        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置popwindow的动画效果
        popupWindow.setAnimationStyle(R.style.popWindow_anim_style);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        popupWindow.setOnDismissListener(this);// 当popWindow消失时的监听
    }

    /**
     * 弹窗显示的位置
     */
    public void showAsDropDown(View parent) {
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
    }

    /**
     * 保存购物车的数据
     */
    private void setSaveData() {
//        Bitmap img= BitmapFactory.decodeFile("");

        SharedPreferences sp = context.getSharedPreferences("SAVE_CART", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("ArrayCart_size", Data.arrayList_cart.size());
        for (int i = 0; i < Data.arrayList_cart.size(); i++) {
            editor.remove("ArrayCart_type_" + i);
            editor.remove("ArrayCart_color_" + i);
            editor.remove("ArrayCart_num_" + i);
            editor.putString("ArrayCart_type_" + i, Data.arrayList_cart.get(i).get("type").toString());
            editor.putString("ArrayCart_color_" + i, Data.arrayList_cart.get(i).get("color").toString());
            editor.putString("ArrayCart_num_" + i, Data.arrayList_cart.get(i).get("num").toString());

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pop_choice_16g:
                pop_choice_16g.setBackgroundResource(R.drawable.yuanjiao_choice);
                pop_choice_32g.setBackgroundResource(R.drawable.yuanjiao);
                pop_choice_16m.setBackgroundResource(R.drawable.yuanjiao);
                pop_choice_32m.setBackgroundResource(R.drawable.yuanjiao);
                str_type = pop_choice_16g.getText().toString();
                break;
            case R.id.pop_choice_32g:
                pop_choice_16g.setBackgroundResource(R.drawable.yuanjiao);
                pop_choice_32g.setBackgroundResource(R.drawable.yuanjiao_choice);
                pop_choice_16m.setBackgroundResource(R.drawable.yuanjiao);
                pop_choice_32m.setBackgroundResource(R.drawable.yuanjiao);
                str_type = pop_choice_32g.getText().toString();
                break;
            case R.id.pop_choice_16m:
                pop_choice_16g.setBackgroundResource(R.drawable.yuanjiao);
                pop_choice_32g.setBackgroundResource(R.drawable.yuanjiao);
                pop_choice_16m.setBackgroundResource(R.drawable.yuanjiao_choice);
                pop_choice_32m.setBackgroundResource(R.drawable.yuanjiao);
                str_type = pop_choice_16m.getText().toString();
                break;
            case R.id.pop_choice_32m:
                pop_choice_16g.setBackgroundResource(R.drawable.yuanjiao);
                pop_choice_32g.setBackgroundResource(R.drawable.yuanjiao);
                pop_choice_16m.setBackgroundResource(R.drawable.yuanjiao);
                pop_choice_32m.setBackgroundResource(R.drawable.yuanjiao_choice);
                str_type = pop_choice_32m.getText().toString();
                break;
            case R.id.pop_choice_black:
                pop_choice_black.setBackgroundResource(R.drawable.yuanjiao_choice);
                pop_choice_white.setBackgroundResource(R.drawable.yuanjiao);
                str_color = pop_choice_black.getText().toString();
                break;
            case R.id.pop_choice_white:
                pop_choice_black.setBackgroundResource(R.drawable.yuanjiao);
                pop_choice_white.setBackgroundResource(R.drawable.yuanjiao_choice);
                str_color = pop_choice_white.getText().toString();
                break;
            case R.id.pop_add:
                if (!pop_num.getText().toString().equals("750")) {

                    String num_add = Integer.valueOf(pop_num.getText().toString()) + ADDORREDUCE + "";
                    pop_num.setText(num_add);
                } else {
                    Toast.makeText(context, "不能超过最大产品数量", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.pop_reduce:
                if (!pop_num.getText().toString().equals("1")) {
                    String num_reduce = Integer.valueOf(pop_num.getText().toString()) - ADDORREDUCE + "";
                    pop_num.setText(num_reduce);
                } else {
                    Toast.makeText(context, "购买数量不能低于1件", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.pop_del:
                listener.onClickCancelPop();
                popupWindow.dismiss();
                break;
            case R.id.pop_ok:
                if (str_color.equals("")) {
                    Toast.makeText(context, "亲，你还没有选择颜色哟~", Toast.LENGTH_SHORT).show();
                } else if (str_type.equals("")) {
                    Toast.makeText(context, "亲，你还没有选择类型哟~", Toast.LENGTH_SHORT).show();
                } else {
                    listener.onClickOKPop();
                    HashMap<String, Object> allHashMap = new HashMap<String, Object>();

                    allHashMap.put("color", str_color);
                    allHashMap.put("type", str_type);
                    allHashMap.put("num", pop_num.getText().toString());
                    allHashMap.put("id", Data.arrayList_cart_id += 1);

                    Data.arrayList_cart.add(allHashMap);
                    setSaveData();
                    popupWindow.dismiss();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 设置监听
     */
    public void setOnPopWindowClickListener(OnPopWindowClickListener listener) {
        this.listener = listener;
    }

    public interface OnPopWindowClickListener {
        /**
         * 设置点击确认按钮时监听接口
         */
        void onClickOKPop();
        void onClickCancelPop();
    }
}
