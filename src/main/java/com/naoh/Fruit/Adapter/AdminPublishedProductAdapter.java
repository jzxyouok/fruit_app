package com.naoh.Fruit.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.naoh.Fruit.Data.ProductBean;

import java.util.List;

/**
 * 根据卖家ID获取卖家发布的商品
 */
public class AdminPublishedProductAdapter extends BaseAdapter {
    Context context;
    List<ProductBean> productBeanList;

    public  AdminPublishedProductAdapter(Context context, List<ProductBean> productBeanList)
    {
        this.context = context;
        this.productBeanList = productBeanList;
    }

    @Override
    public int getCount() {
        if(null!=productBeanList)
        {
            return productBeanList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(productBeanList!=null)
        {
            return productBeanList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return productBeanList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        if (convertView == null) {
            holderView = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_published_product, null);
            holderView.iv_pic = (ImageView) convertView.findViewById(R.id.iv_adapter_product_list_pic);
            holderView.tv_name = (TextView) convertView.findViewById(R.id.tv_adapter_product_name);
            holderView.tv_price = (TextView) convertView.findViewById(R.id.tv_adapter_product_price);
            holderView.tv_sale_num = (TextView) convertView.findViewById(R.id.tv_adapter_product_market_price);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        if (productBeanList.size() != 0) {
            Bitmap bit = decodeSampledBitmapFromResource(productBeanList.get(position).getImage(), 60, 60);
            holderView.iv_pic.setImageBitmap(bit);

            holderView.tv_name.setText(productBeanList.get(position).getName());
            holderView.tv_price.setText("￥" + productBeanList.get(position).getPrice() + "元");
            holderView.tv_sale_num.setText("市场价:￥" + productBeanList.get(position).getMarketPrice() + "元");
        }
        return convertView;
    }

    public class HolderView {
        private ImageView iv_pic;
        private TextView tv_sale_num;
        private TextView tv_name;
        private TextView tv_price;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and
            // keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(String strPath,int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(strPath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options,reqWidth,
                reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(strPath, options);
    }
}
