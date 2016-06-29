package com.doublebrain.mysupportlibrary.abstracts;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by AlexShredder on 29.06.2016.
 */
public abstract class ListViewSimplified extends ListView{

    private int listItemID, textViewID1, textViewID2;
    private Context context;
    private List<?> itemsList;
    private LVAdapter adapter;

    public ListViewSimplified(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public ListViewSimplified(Context context) {
        super(context);
        this.context = context;
    }

    public ListViewSimplified(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public List getItemsList() {
        return itemsList;
    }

    public void initAdapter(int listItemID, int textViewID1, int textViewID2, List<?> itemsList){
        this.itemsList = itemsList;
        this.textViewID1 = textViewID1;
        this.textViewID2 = textViewID2;
        this.listItemID = listItemID;
        adapter = new LVAdapter(context,listItemID,textViewID1,itemsList);
        this.setAdapter(adapter);
        if (itemsList instanceof LVList) ((LVList) itemsList).setListViewSimplified(this);

    }

    public void notifyDataSetChanged(){
        if (adapter!=null) adapter.notifyDataSetChanged();
    }

    private class LVAdapter extends ArrayAdapter{

        public LVAdapter(Context context, int resource, int textViewResourceId, List<?> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;

            if (row == null) {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row = inflater.inflate(listItemID, parent, false);
            }

            TextView textView1 = (TextView) row.findViewById(textViewID1);

            TextView textView2 = (TextView) row.findViewById(textViewID2);

            processView(row,textView1,textView2,position);

            return row;

        }

    }

    protected abstract void processView(View row, TextView textView1, TextView textView2, int position);

    public static class LVList<T> extends ArrayList<T> {

        private ListViewSimplified listViewSimplified;

        public void setListViewSimplified(ListViewSimplified listViewSimplified) {
            this.listViewSimplified = listViewSimplified;
        }

        private void updateAdapter(){
            if (listViewSimplified!=null) listViewSimplified.notifyDataSetChanged();
        }

        @Override
        public boolean add(T object) {
            boolean result = super.add(object);
            updateAdapter();
            return result;
        }

        @Override
        public void add(int index, T object) {
            super.add(index, object);
            updateAdapter();
        }

        @Override
        public boolean addAll(Collection<? extends T> collection) {
            boolean result = super.addAll(collection);
            updateAdapter();
            return result;
        }

        @Override
        public boolean addAll(int index, Collection<? extends T> collection) {
            boolean result = super.addAll(index, collection);
            updateAdapter();
            return result;
        }

        @Override
        public void clear() {
            super.clear();
            updateAdapter();
        }

        @Override
        public T remove(int index) {
            T result = super.remove(index);
            updateAdapter();
            return result;
        }

        @Override
        public boolean remove(Object object) {
            boolean result = super.remove(object);
            updateAdapter();
            return result;
        }

        @Override
        protected void removeRange(int fromIndex, int toIndex) {
            super.removeRange(fromIndex, toIndex);
            updateAdapter();
        }

        @Override
        public T set(int index, T object) {
            T result = super.set(index, object);
            updateAdapter();
            return result;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            boolean result = super.removeAll(collection);
            updateAdapter();
            return result;
        }
    }

}
