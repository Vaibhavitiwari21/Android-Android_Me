package com.example.androidme;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class AndroidMeActivity extends AppCompatActivity implements BodyPartsFragment.OnImageClickListener {

    private int mHeadIndex;
    private int mBodyIndex;
    private int mLegIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        // Create the BodyPartsFragment instance and add it to the activity
        BodyPartsFragment bodyPartsFragment = new BodyPartsFragment();
        bodyPartsFragment.setOnImageClickListener(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.body_parts_fragment, bodyPartsFragment)
                .commit();

        FragmentManager fragmentManager = getSupportFragmentManager();

        // Add the CombinedImageFragment to the activity's layout
        CombinedImageFragment combinedImageFragment = new CombinedImageFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.combined_image_fragment, combinedImageFragment);
        fragmentTransaction.commit();

        // Set the body part indices to display the combined image
        combinedImageFragment.setBodyPartIndices(mHeadIndex, mBodyIndex, mLegIndex);
    }

    @Override
    public void onImageSelected(int position) {
        int bodyPartNumber = position / 12;
        int listIndex = position - 12 * bodyPartNumber;

        int PartNumber = position+1;

        Toast.makeText(this, "Image selected: " + PartNumber, Toast.LENGTH_SHORT).show();

        switch (bodyPartNumber) {
            case 0:
                mHeadIndex = listIndex;
                break;
            case 1:
                mBodyIndex = listIndex;
                break;
            case 2:
                mLegIndex = listIndex;
                break;
            default:
                break;
        }

        // Create the CombinedImageFragment instance and update the body part indices
        CombinedImageFragment combinedImageFragment = new CombinedImageFragment();
        combinedImageFragment.setBodyPartIndices(mHeadIndex, mBodyIndex, mLegIndex);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.combined_image_fragment, combinedImageFragment)
                .commit();
    }
}
