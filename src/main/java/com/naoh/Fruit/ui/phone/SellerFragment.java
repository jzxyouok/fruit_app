package com.naoh.Fruit.ui.phone;



import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;




import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Adapter.SortAdapter;
import com.naoh.Fruit.Data.SellerBean;
import com.naoh.Fruit.dao.SellerService;
import com.naoh.Fruit.util.PinYinKit;
import com.naoh.Fruit.util.PinyinComparator;
import com.naoh.Fruit.util.pingyin.model.SortModel;
import com.naoh.Fruit.view.SearchEditText;
import com.naoh.Fruit.view.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/5/28.
 */
public class SellerFragment extends Fragment {
    public PinyinComparator comparator = new PinyinComparator();

    private ImageView groupImg;
    private ImageView backImg;
    private TextView userListNumTxt;
    private String userListNumStr;

    private SideBar sideBar;
    private ListView sortListView;
    private TextView dialogTxt;
    private SearchEditText mSearchEditText;
    private SortAdapter adapter;

    private List<SortModel> sortModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_sellers, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        this.userListNumTxt = (TextView) view.findViewById(R.id.txt_user_list_user_num);

        this.backImg = (ImageView)view.findViewById(R.id.img_user_list_back);

        this.groupImg = (ImageView)view.findViewById(R.id.img_user_list_group);


        sideBar = (SideBar) view.findViewById(R.id.sild_bar);
        dialogTxt = (TextView) view.findViewById(R.id.txt_dialog);
        sideBar.setmTextDialog(dialogTxt);

        // on touching listener of side bar
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener()
        {

            public void onTouchingLetterChanged(String str)
            {
                int position =  adapter.getPositionForSection(str.charAt(0));
                if (position != -1)
                    sortListView.setSelection(position);
            }
        });

        sortListView = (ListView) view.findViewById(R.id.list_view_user_list);
        sortListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        sortListView.setOnItemClickListener(new OnItemClickListener()
        {

            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id)
            {
                Toast.makeText(getActivity(), ((SortModel)adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
            }
        });


        List<SellerBean> sellers = new SellerService(getActivity()).findAll();

        // call filledData to get datas
        try
        {
            sortModelList =  filledSeller(sellers);
        } catch (BadHanyuPinyinOutputFormatCombination e1)
        {
            e1.printStackTrace();
        }

        userListNumTxt.setText("全部："+"\t"+sortModelList.size()+"个联系人");

        // sort by a-z
        Collections.sort(sortModelList, comparator);
        adapter = new SortAdapter(getActivity(), sortModelList);
        sortListView.setAdapter(adapter);


        // search
        mSearchEditText = (SearchEditText) view.findViewById(R.id.txt_filter_edit);

        // filter
        mSearchEditText.addTextChangedListener(new TextWatcher()
        {

            public void onTextChanged(CharSequence str, int arg1, int arg2, int arg3)
            {
                try
                {
                    filerData(str.toString());
                }
                catch (BadHanyuPinyinOutputFormatCombination e)
                {
                    e.printStackTrace();
                }
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3)
            {
            }

            public void afterTextChanged(Editable arg0)
            {
            }
        });
    }

    private List<SortModel> filledSeller(List<SellerBean> sellers) throws BadHanyuPinyinOutputFormatCombination {
        List<SortModel> mSortList = new ArrayList<SortModel>();
        for(int i=0; i<sellers.size(); i++){
            SortModel sortModel = new SortModel();
            sortModel.setSellerBean(sellers.get(i));
            sortModel.setId(sellers.get(i).getId());
            sortModel.setName(sellers.get(i).getName());
            //汉字转换成拼音
            String pinyin = PinYinKit.getPingYin(sellers.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;
    }




    private void filerData(String str) throws BadHanyuPinyinOutputFormatCombination
    {
        List<SortModel> fSortModels = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(str))
            fSortModels = sortModelList;
        else
        {
            fSortModels.clear();
            for (SortModel sortModel : sortModelList)
            {
                String name = sortModel.getName();
                if (name.indexOf(str.toString()) != -1 ||
                        PinYinKit.getPingYin(name).startsWith(str.toString()) || PinYinKit.getPingYin(name).startsWith(str.toUpperCase().toString()))
                {
                    fSortModels.add(sortModel);
                }
            }

        }
        Collections.sort(fSortModels, comparator);
        adapter.updateListView(fSortModels);
    }

    public void changeDatas(List<SortModel> mSortList , String str)
    {
        userListNumTxt.setText(str+"："+"\t"+mSortList.size()+"个联系人");

        Collections.sort(mSortList, comparator);
        adapter.updateListView(mSortList);
    }

}
