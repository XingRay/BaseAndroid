package com.ray.linkifytext;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.text.util.LinkifyCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.ArrowKeyMovementMethod;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 解决TextView在同时设置 autoLink和 isTextSelectable 会导致spannable点击错位的问题
 * {@see <a href="https://stackoverflow.com/questions/14862750/textview-that-is-linkified-and-selectable">StackOverflow</a>}
 * <p>
 * The autoLink attribute has an annoying bug: if you click in your example on the phone number,
 * then return back and click on the second url link - it will open the phone number again.
 * This attribute works so bad with multiple links , that I have implemented my own class,
 * here is the link on Github ClickableLinksTextView.java
 * <p>
 * In your example you can replace your TextView class by my ClickableLinksTextView class in the
 * xml-layout and change the code like this:
 * <p>
 * <code>
 * ClickableLinksTextView textView = (ClickableLinksTextView)view.findViewById(R.id.mytext);
 * textView.setText("My text: +4412345678 Go to website: www.google.com Blah blah");
 * Linkify.addLinks(textView, Linkify.ALL);
 * <p>
 * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
 * textView.setMovementMethod(ArrowKeyMovementMethod.getInstance());
 * textView.setTextIsSelectable(true);
 * // the autoLink attribute must be removed, if you hasn't set it then ok, otherwise call textView.setAutoLink(0);
 * }
 * </code>
 * <p>
 * <p>
 * The TextView that handles correctly clickable spans.
 */
public class LinkifyTextView extends AppCompatTextView {
    public static final String TAG = "ClickableLinksTextView";

    private boolean mBaseEditorCopied = false;
    private Object mBaseEditor = null;
    private Field mDiscardNextActionUpField = null;
    private Field mIgnoreActionUpEventField = null;

    public LinkifyTextView(Context context) {
        super(context);
    }

    public LinkifyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LinkifyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTextLinkifyAndSelectable(CharSequence text, @LinkifyCompat.LinkifyMask int mask, boolean selectable) {
        setText(text);
        Linkify.addLinks(this, mask);
        setMovementMethod(ArrowKeyMovementMethod.getInstance());
        setTextIsSelectable(selectable);
    }

    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();

        // listview scrolling behaves incorrectly after you select and copy some text, so I've added this code
        if (this.isFocused()) {
            Log.d(TAG, "clear focus");
            this.clearFocus();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // the base TextView class checks if getAutoLinkMask != 0, so I added a similar code for == 0
        if (CompatibilityUtils.isTextSelectable(this) && this.getText() instanceof Spannable && this.getAutoLinkMask() == 0 && this.getLinksClickable() && this.isEnabled() && this.getLayout() != null) {
            return this.checkLinksOnTouch(event);
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Reset the MyLeadingMarginSpan2 state
        CharSequence text = this.getText();
        if (text != null && text instanceof Spannable) {
            CompatibilityUtils.resetMyLeadingMarginSpanState((Spannable) text);
        }

        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        CharSequence text = this.getText();
        if (text != null && text instanceof Spannable) {
            CompatibilityUtils.callMyLeadingMarginSpanMeasure((Spannable) text);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public int getOffsetForPosition(float x, float y) {
        // Set the current line for MyLeadingMarginSpan2
        CharSequence text = this.getText();
        if (text != null && text instanceof Spannable) {
            int line = this.getLineAtCoordinate(y);

            CompatibilityUtils.setMyLeadingMarginSpanCurrentLine((Spannable) text, line);
        }

        return super.getOffsetForPosition(x, y);
    }

    public void startSelection() {
        if (StringUtils.isEmpty(this.getText())) {
            return;
        }

        this.copyBaseEditorIfNecessary();

        Selection.setSelection((Spannable) this.getText(), 0, this.getText().length());

        try {
            Method performLongClick = this.mBaseEditor.getClass().getMethod("performLongClick", Boolean.TYPE);
            performLongClick.invoke(this.mBaseEditor, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getLineAtCoordinate(float y) {
        y -= getTotalPaddingTop();
        // Clamp the position to inside of the view.
        y = Math.max(0.0f, y);
        y = Math.min(getHeight() - getTotalPaddingBottom() - 1, y);
        y += getScrollY();
        return getLayout().getLineForVertical((int) y);
    }

    private boolean checkLinksOnTouch(MotionEvent event) {
        this.copyBaseEditorIfNecessary();

        int action = event.getAction() & 0xff; // getActionMasked()
        boolean discardNextActionUp = this.getDiscardNextActionUp();

        // call the base method anyway
        final boolean superResult = super.onTouchEvent(event);

        // the same check as in the super.onTouchEvent(event)
        if (discardNextActionUp && action == MotionEvent.ACTION_UP) {
            return superResult;
        }

        boolean isLinkClick = MyLinkMovementMethod.getInstance().isLinkClickEvent(this, (Spannable) this.getText(), event);
        boolean isTouchStarted = action == MotionEvent.ACTION_DOWN;
        boolean isTouchFinished = (action == MotionEvent.ACTION_UP) && !this.getIgnoreActionUpEvent();
        if (isLinkClick && (isTouchStarted || isTouchFinished) && this.isFocused()) {
            return true;
        }

        return superResult;
    }

    private void copyBaseEditorIfNecessary() {
        if (this.mBaseEditorCopied) {
            return;
        }

        try {
            Field field = TextView.class.getDeclaredField("mEditor");
            field.setAccessible(true);
            this.mBaseEditor = field.get(this);

            if (this.mBaseEditor != null) {
                Class editorClass = this.mBaseEditor.getClass();
                this.mDiscardNextActionUpField = editorClass.getDeclaredField("mDiscardNextActionUp");
                this.mDiscardNextActionUpField.setAccessible(true);

                this.mIgnoreActionUpEventField = editorClass.getDeclaredField("mIgnoreActionUpEvent");
                this.mIgnoreActionUpEventField.setAccessible(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.mBaseEditorCopied = true;
        }
    }

    private boolean getDiscardNextActionUp() {
        if (this.mBaseEditor == null) {
            return false;
        }

        try {
            return this.mDiscardNextActionUpField.getBoolean(this.mBaseEditor);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean getIgnoreActionUpEvent() {
        if (this.mBaseEditor == null) {
            return false;
        }

        try {
            return this.mIgnoreActionUpEventField.getBoolean(this.mBaseEditor);
        } catch (Exception e) {
            return false;
        }
    }
}
