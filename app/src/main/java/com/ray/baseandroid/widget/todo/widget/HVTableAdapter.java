package com.ray.baseandroid.widget.todo.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

/**
 * @author tianlupan 2015/10/13
 */
public abstract class HVTableAdapter extends BaseAdapter {

    private int scrollX=0;

    private static final String TAG=HVTableAdapter.class.getSimpleName();

    private static final int SCROLL_MAX=30000;

    public void setScrollX(int value){
        scrollX=value;
    }

    protected final Context mContext;

    private LinearLayout mHeader;

    public HVTableAdapter(Context context){
        mContext=context;
        mHeader=getTableTitleInner();
    }


    @Override
    public final int getCount() {
        return getRowCount();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LinearLayout row=newRow(true);
            fillEmptyRow(row, position);
            convertView=row;
        }else{
            resetRow((LinearLayout) convertView,position);
        }
        //同步每项scrollX
        getScrollViewGroup((LinearLayout)convertView).setScrollX(scrollX);
        return convertView;
    }

    /**
     * 填充内容row
     * @param row
     */
    private void fillEmptyRow(LinearLayout row, int rowIndex){
        row.addView(getRowTitle(rowIndex,null));
        row.addView(newRow(false),new AbsListView.LayoutParams(SCROLL_MAX, ViewGroup.LayoutParams.WRAP_CONTENT));

        LinearLayout viewGroup=getScrollViewGroup(row);
        for(int c=0;c<getColumnCount();c++){
            viewGroup.addView(getRow(rowIndex,c,null,null));
        }
    }

    private LinearLayout getScrollViewGroup(LinearLayout row){
        return  (LinearLayout) row.getChildAt(1);
    }


    private void replaceChild(LinearLayout row, int index, View child){

        if(child==row.getChildAt(index)){
            return;
        }

        row.removeViewAt(index);
        row.addView(child,index);
    }

    private void resetRow(LinearLayout row, int rowIndex){
        replaceChild(row,0,getRowTitle(rowIndex,row.getChildAt(0)));
        LinearLayout viewGroup=getScrollViewGroup(row);
        for(int c=0;c<getColumnCount();c++){
            View convertView=viewGroup.getChildAt(c);
            replaceChild(viewGroup,c,getRow(rowIndex,c,convertView,row));
        }
    }

    private LinearLayout newRow(boolean matchParent){
        LinearLayout layout=new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        if(matchParent){
            layout.setLayoutParams(new AbsListView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }else{
            layout.setLayoutParams(new AbsListView.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }

        return layout;
    }

    /**
     * 获取表头,仅供 HVListView调用
     * @return
     */
    private LinearLayout getTableTitleInner(){
        LinearLayout row=newRow(false);
        View topLeftView=getColumnTitle(-1);
        if(topLeftView==null){
            throw new IllegalStateException("");//com.hecom.mgm.R.string.zuoshangjiaodeneigeVIEW)
        }
        row.addView(topLeftView);
        LinearLayout scrollGroup=newRow(false);
        for(int i=0;i<getColumnCount();i++){
            View columnTitleView=getColumnTitle(i);
            if(columnTitleView==null){
                throw new IllegalStateException("");//biaotilanbunengweikong_qingzai
            }
            scrollGroup.addView(columnTitleView);
        }
        row.addView(scrollGroup,new AbsListView.LayoutParams(SCROLL_MAX, ViewGroup.LayoutParams.WRAP_CONTENT));
        return row;
    }

     protected LinearLayout getTableTitle(){
        return mHeader;
     }


    /**
     * 获取第row行的标头
     * @param row 从0开始,(0代表第一行,不把最上面的columnTitle包含在内）
     * @return
     */
    protected abstract View getRowTitle(int row, View convertView);

    protected abstract int getRowCount();

    /**
     * Table Item 内容
     * @param row
     * @param column
     * @param convertView
     * @param parent
     * @return
     */
    protected abstract View getRow(int row, int column, View convertView, ViewGroup parent);

    /**
     * 获取列数，不包含最左边的rowTitle那一列
     * @return
     */
    protected abstract int getColumnCount();


    /**
     * 获取最上一排的列Title,注意column从-1开始,-1代表表格左上角，通常是空内容
     * @param column [-1,getColumnCount())
     * @return
     */
    protected abstract View getColumnTitle(int column);

    final View getTableTitlePart(boolean left){
        if(mHeader==null){
            return null;
        }else{
            return mHeader.getChildAt( left ? 0 : 1);
        }
    }

}
