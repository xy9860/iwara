package com.xy9860.iwara.data;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xy9860.iwara.R;

public class ItemDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;

    public ItemDecoration(Context context) {
        dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.x10);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }
}
