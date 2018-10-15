package com.xy9860.iwara.data;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xy9860.iwara.R;

public class ItemDecoration extends RecyclerView.ItemDecoration {

    private int divider2;
    private int divider3;
    private int divider5;
    private Paint dividerPaint;

    public ItemDecoration(Context context) {
        dividerPaint = new Paint();
        dividerPaint.setColor(context.getResources().getColor(R.color.divider));
        divider5 = context.getResources().getDimensionPixelSize(R.dimen.x5);
        divider3 = context.getResources().getDimensionPixelSize(R.dimen.x3);
        divider2 = context.getResources().getDimensionPixelSize(R.dimen.x2);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0){
            outRect.top = divider5;
        }
        outRect.bottom = divider5;
        outRect.left = divider2;
        outRect.right = divider2;
    }
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull  RecyclerView parent, @NonNull  RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom() + divider2;
            float bottom = view.getBottom() + divider3;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }
}
