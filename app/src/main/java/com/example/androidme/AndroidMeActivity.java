package com.example.androidme;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Random;

public class AndroidMeActivity extends AppCompatActivity implements BodyPartsFragment.OnImageClickListener {

    private int mHeadIndex;
    private int mBodyIndex;
    private int mLegIndex;

    private int[] headIds = {R.drawable.head1, R.drawable.head2, R.drawable.head3, R.drawable.head4, R.drawable.head5, R.drawable.head6, R.drawable.head7, R.drawable.head8, R.drawable.head9, R.drawable.head10, R.drawable.head11, R.drawable.head12};
    private int[] bodyIds = {R.drawable.body1, R.drawable.body2, R.drawable.body3, R.drawable.body4, R.drawable.body5, R.drawable.body6, R.drawable.body7, R.drawable.body8, R.drawable.body9, R.drawable.body10, R.drawable.body11, R.drawable.body12};
    private int[] legIds = {R.drawable.legs1, R.drawable.legs2, R.drawable.legs3, R.drawable.legs4, R.drawable.legs5, R.drawable.legs6, R.drawable.legs7, R.drawable.legs8, R.drawable.legs9, R.drawable.legs10, R.drawable.legs11, R.drawable.legs12};

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

        // Add the initial CombinedImageFragment to the activity's layout
        CombinedImageFragment combinedImageFragment = new CombinedImageFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.combined_image_fragment, combinedImageFragment);
        fragmentTransaction.commit();

        // Set the body part indices to display the initial combined image
        combinedImageFragment.setBodyPartIndices(mHeadIndex, mBodyIndex, mLegIndex);

        // Get a reference to the random button and add an OnClickListener
        Button randomButton = findViewById(R.id.random_button);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate random indices for the head, body, and leg images
                Random random = new Random();
                int headIndex = random.nextInt(12);
                int bodyIndex = random.nextInt(12);
                int legIndex = random.nextInt(12);

                // Update the body part indices in AndroidMeActivity
                mHeadIndex = headIndex;
                mBodyIndex = bodyIndex;
                mLegIndex = legIndex;

                // Create a new CombinedImageFragment instance with the updated body part indices
                CombinedImageFragment newCombinedImageFragment = new CombinedImageFragment();
                newCombinedImageFragment.setBodyPartIndices(mHeadIndex, mBodyIndex, mLegIndex);

                // Replace the existing CombinedImageFragment in the activity's layout with the new one
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.combined_image_fragment, newCombinedImageFragment);
                fragmentTransaction.commit();
            }
        });
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
