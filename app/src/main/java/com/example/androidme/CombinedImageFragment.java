package com.example.androidme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CombinedImageFragment extends Fragment {
    // Initialize the indices of the selected head, body, and leg images
    private int mHeadIndex;
    private int mBodyIndex;
    private int mLegIndex;

    // Create an ArrayList to hold the selected images
    private ArrayList<Integer> mSelectedImages = new ArrayList<>();

    // Default constructor
    public CombinedImageFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.combined_image_fragment, container, false);

        // Get the ImageView and set the combined image resource
        ImageView imageView = rootView.findViewById(R.id.combined_image);
        int imageResId = AndroidImageAssets.getCombinedImageResId(getContext(), mHeadIndex, mBodyIndex, mLegIndex);
        if (getContext() != null) {
            imageView.setImageResource(imageResId);
        }

        // Set the click listener for the ImageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the selected images to the mSelectedImages list
                mSelectedImages.add(AndroidImageAssets.heads.get(mHeadIndex));
                mSelectedImages.add(AndroidImageAssets.bodies.get(mBodyIndex));
                mSelectedImages.add(AndroidImageAssets.legs.get(mLegIndex));

                // Get the container LinearLayout and remove any existing views
                LinearLayout linearLayout = rootView.findViewById(R.id.combined_image_fragment_container);
                linearLayout.removeAllViews();

                // Create a new ImageView for each selected image and add it to the container
                for (int i = 0; i < mSelectedImages.size(); i++) {
                    ImageView selectedImageView = new ImageView(getContext());
                    selectedImageView.setImageResource(mSelectedImages.get(i));
                    selectedImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    linearLayout.addView(selectedImageView);
                }
            }
        });

        return rootView;
    }

    // Setter method to update the indices of the selected body parts
    public void setBodyPartIndices(int headIndex, int bodyIndex, int legIndex) {
        mHeadIndex = headIndex;
        mBodyIndex = bodyIndex;
        mLegIndex = legIndex;
    }
}
