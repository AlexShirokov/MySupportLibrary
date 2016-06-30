package com.doublebrain.mysupportlibrary.models;

import java.io.Serializable;

/**
 * Created by AlexShredder on 29.06.2016.
 */
public class ListItem implements Serializable {
    public String text1, text2;

    public ListItem(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
    }
}
