package com.example.androidme;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

// Class for storing all the image drawable resources in ArrayLists
public class AndroidImageAssets {

    // Lists for all AndroidMe images
    // Broken down into heads, bodies, legs, and all images
    static final List<Integer> heads = new ArrayList<Integer>() {{
        add(R.drawable.head1);
        add(R.drawable.head2);
        add(R.drawable.head3);
        add(R.drawable.head4);
        add(R.drawable.head5);
        add(R.drawable.head6);
        add(R.drawable.head7);
        add(R.drawable.head8);
        add(R.drawable.head9);
        add(R.drawable.head10);
        add(R.drawable.head11);
        add(R.drawable.head12);
    }};

    static final List<Integer> bodies = new ArrayList<Integer>() {{
        add(R.drawable.body1);
        add(R.drawable.body2);
        add(R.drawable.body3);
        add(R.drawable.body4);
        add(R.drawable.body5);
        add(R.drawable.body6);
        add(R.drawable.body7);
        add(R.drawable.body8);
        add(R.drawable.body9);
        add(R.drawable.body10);
        add(R.drawable.body11);
        add(R.drawable.body12);
    }};

    static final List<Integer> legs = new ArrayList<Integer>() {{
        add(R.drawable.legs1);
        add(R.drawable.legs2);
        add(R.drawable.legs3);
        add(R.drawable.legs4);
        add(R.drawable.legs5);
        add(R.drawable.legs6);
        add(R.drawable.legs7);
        add(R.drawable.legs8);
        add(R.drawable.legs9);
        add(R.drawable.legs10);
        add(R.drawable.legs11);
        add(R.drawable.legs12);
    }};

    // A list that contains all the images in the same order as they are listed in heads, bodies, and legs.
    private static final List<Integer> all = new ArrayList<Integer>() {{
        addAll(heads);
        addAll(bodies);
        addAll(legs);
    }};

    // Returns a list of all the images combined: heads, bodies, and legs in that order
    public static List<Integer> getAll() {
        return all;
    }

    /**
     * Returns the combined image resource ID for the specified head, body, and leg indices.
     *
     * @param context    The context of the calling activity or fragment.
     * @param headIndex  The index of the selected head image.
     * @param bodyIndex  The index of the selected body image.
     * @param legIndex   The index of the selected leg image.
     * @return           The resource ID of the combined image.
     */
    public static int getCombinedImageResId(Context context, int headIndex, int bodyIndex, int legIndex) {
        int headResId = heads.get(headIndex);
        int bodyResId = bodies.get(bodyIndex);
        int legResId = legs.get(legIndex);
        String resourceName = "combined_" + headIndex + "_" + bodyIndex + "_" + legIndex;
        return context.getResources().getIdentifier(
                resourceName,
                "drawable",
                context.getPackageName()
        );
    }
}
