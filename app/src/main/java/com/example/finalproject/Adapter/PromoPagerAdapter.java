package com.example.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.finalproject.R;

public class PromoPagerAdapter extends PagerAdapter {

    private Context context;
    private int[] promoImages;

    public PromoPagerAdapter(Context context, int[] promoImages) {
        this.context = context;
        this.promoImages = promoImages;
    }

    @Override
    public int getCount() {
        return promoImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_promo, container, false);

        ImageView imageView = itemView.findViewById(R.id.imageView);
        imageView.setImageResource(promoImages[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
