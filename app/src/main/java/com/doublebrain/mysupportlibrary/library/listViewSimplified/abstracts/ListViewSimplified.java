package com.doublebrain.mysupportlibrary.library.listViewSimplified.abstracts;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by AlexShredder on 29.06.2016.
 */
public abstract class ListViewSimplified extends ListView{

    private int listItemID;
    private List<?> itemsList;
    private LVAdapter adapter;

    public ListViewSimplified(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ListViewSimplified(Context context) {
        super(context);
    }

    public ListViewSimplified(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public List getItemsList() {
        return itemsList;
    }

    public void init(int listItemID, List<?> itemsList){
        this.itemsList = itemsList;
        this.listItemID = listItemID;
        adapter = new LVAdapter(getContext(),listItemID,itemsList);
        this.setAdapter(adapter);

        if (itemsList instanceof LVList) ((LVList) itemsList).setListViewSimplified(this);

    }

    public void notifyDataSetChanged(){
        if (adapter!=null) adapter.notifyDataSetChanged();
    }

    private class LVAdapter extends ArrayAdapter{

        public LVAdapter(Context context, int resource, List<?> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder viewHolder;
            if (row == null) {
                LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
                row = inflater.inflate(listItemID, parent, false);

                viewHolder = new ViewHolder();
                viewHolder.views = new HashMap<>();

                Map<String, Integer> widgets = fillListItemWidgetsNameAndID();
                for (Map.Entry<String,Integer> entry: widgets.entrySet())
                    viewHolder.views.put(entry.getKey(),row.findViewById(entry.getValue()));

                row.setTag(viewHolder);

            } else viewHolder = (ViewHolder) row.getTag();


            /*TextView textView1 = (TextView) row.findViewById(textViewID1);

            TextView textView2 = (TextView) row.findViewById(textViewID2);*/

            processView(row,viewHolder,itemsList,position);

            return row;

        }

    }

    protected abstract void processView(View row, ViewHolder viewHolder, List<?> itemsList, int position);

    protected abstract Map<String,Integer> fillListItemWidgetsNameAndID();

    protected static class ViewHolder {
        Map<String,View> views;

        public View getView(String name) {
            return views.get(name);
        }
    }


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
