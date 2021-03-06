package com.doublebrain.mysupportlibrary.models;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.doublebrain.mysupportlibrary.R;
import com.doublebrain.mysupportlibrary.library.listViewSimplified.abstracts.ListViewSimplified;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AlexShredder on 29.06.2016.
 */
public class ListViewSimplifiedSample extends ListViewSimplified {

    public ListViewSimplifiedSample(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ListViewSimplifiedSample(Context context) {
        super(context);
    }

    public ListViewSimplifiedSample(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void processView(View row, ViewHolder viewHolder, final List<?> itemsList, int position) {
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
