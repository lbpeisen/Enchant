package me.wcy.music.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import me.wcy.music.R;


public class ItemDecorationMy extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int distance;

    Drawable drawable;
    Drawable verticalLine;

    Paint textPaint;
    Rect textRect;
    String end="END";

    public ItemDecorationMy(Context context, int distance) {
        mContext = context;
        this.distance = distance;
        verticalLine = ContextCompat.getDrawable(mContext, R.drawable.white_line);
        drawable = ContextCompat.getDrawable(mContext, R.drawable.dot);
        textPaint = new Paint();
        textPaint.setColor(0xFFFFFFFF);
        textPaint.setTextSize(18);
        textRect = new Rect();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = 20;
        outRect.right = 20;
        outRect.bottom = distance;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = 0;
        } else if (parent.getChildAdapterPosition(view) == 1) {
            outRect.top = 2 * distance;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int parentWidth = parent.getMeasuredWidth();

        final int childCount = parent.getChildCount();
        View lastChild = parent.getChildAt(childCount - 1);
        verticalLine.setBounds(parentWidth / 2 - 1, top, parentWidth / 2 + 1, lastChild.getBottom());
        verticalLine.draw(c);
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int parentWidth = parent.getMeasuredWidth();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {//-1最后一个不画
            final View child = parent.getChildAt(i);

            int top = child.getTop() + 10;
            int bottom = top + drawable.getIntrinsicHeight();

            int drawableLeft = parentWidth / 2 - drawable.getIntrinsicWidth() / 2;
            int drawableRight = parentWidth / 2 + drawable.getIntrinsicWidth() / 2;

            drawable.setBounds(drawableLeft, top, drawableRight, bottom);
            drawable.draw(c);

            if (i == childCount - 1) {
                top = child.getBottom();
                bottom = child.getBottom() + drawable.getIntrinsicHeight();
                drawable.setBounds(drawableLeft, top, drawableRight, bottom);
                drawable.draw(c);

                textPaint.getTextBounds(end, 0, end.length(), textRect);
                c.drawText(end, parentWidth / 2 - textRect.width()/2, bottom + 30, textPaint);
            }
        }
    }
}