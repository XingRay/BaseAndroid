package com.ray.linkifytext;

import android.text.Layout;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Author      : leixing
 * Date        : 2017-08-09
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : 解决点击spannable时TextView会自动滚动的问题
 * {@see <a href="https://stackoverflow.com/questions/14579785/can-i-disable-the-scrolling-in-textview-when-using-linkmovementmethod">Stack Overflow</a>}
 */

public class LinkMovementMethodOverride implements View.OnTouchListener {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TextView widget = (TextView) v;
        Object text = widget.getText();
        if (text instanceof Spanned) {
            Spanned buffer = (Spanned) text;

            int action = event.getAction();

            if (action == MotionEvent.ACTION_UP
                    || action == MotionEvent.ACTION_DOWN) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                x -= widget.getTotalPaddingLeft();
                y -= widget.getTotalPaddingTop();

                x += widget.getScrollX();
                y += widget.getScrollY();

                Layout layout = widget.getLayout();
                int line = layout.getLineForVertical(y);
                int off = layout.getOffsetForHorizontal(line, x);

                ClickableSpan[] link = buffer.getSpans(off, off,
                        ClickableSpan.class);

                if (link.length != 0) {
                    if (action == MotionEvent.ACTION_UP) {
                        link[0].onClick(widget);
                    } else if (action == MotionEvent.ACTION_DOWN) {
                        // Selection only works on Spannable text. In our case setSelection doesn't work on spanned text
                        //Selection.setSelection(buffer, buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]));
                    }
                    return true;
                }
            }

        }

        return false;
    }
}
