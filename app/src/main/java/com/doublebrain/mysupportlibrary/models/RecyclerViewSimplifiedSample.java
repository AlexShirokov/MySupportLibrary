package com.doublebrain.mysupportlibrary.models;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.doublebrain.mysupportlibrary.R;
import com.doublebrain.mysupportlibrary.library.recyclerViewSimplified.RecyclerViewSimplified;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AlexShredder on 30.06.2016.
 */
public class RecyclerViewSimplifiedSample extends RecyclerViewSimplified{
    public RecyclerViewSimplifiedSample(Context context) {
        super(context);
    }

    public RecyclerViewSimplifiedSample(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewSimplifiedSample(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void processView(ViewHolder viewHolder, final List<?> itemsList, int position) {
        final ListItem listItem = (ListItem) itemsList.get(position);
        TextView textView1 = (TextView) viewHolder.getView("Line1");
        TextView textView2 = (TextView) viewHolder.getView("Line2");
        ImageButton imageButton = (ImageButton) viewHolder.getView("Button");
        textView1.setText(listItem.text1);
        textView2.setText(listItem.text2);

        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                itemsList.remove(listItem);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    protected Map<String, Integer> fillListItemWidgetsNameAndID() {
        Map<String, Integer> result = new HashMap<>();
        result.put("Line1", R.id.listItemText1);
        result.put("Line2", R.id.listItemText2);
        result.put("Button",R.id.imageButtonTrash);
        return result;
    }
}
