package com.example.finalproject;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
    private final int spacing;

    public RecyclerViewItemDecoration(Context context) {
        this.spacing = context.getResources().getDimensionPixelSize(R.dimen.item_spacing);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = spacing;
        outRect.right = spacing;
        outRect.bottom = spacing;

        // Tambahkan kondisi untuk item pertama (atas) jika diperlukan
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = spacing;
        }
    }
}
