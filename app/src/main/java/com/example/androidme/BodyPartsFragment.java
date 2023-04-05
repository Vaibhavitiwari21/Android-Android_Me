// This is the BodyPartsFragment class that extends Fragment and implements the AdapterView.OnItemClickListener interface
// This fragment is responsible for displaying the grid of body part images in the AndroidMeActivity
// and handling user interactions with the grid

package com.example.androidme;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.fragment.app.Fragment;
import java.util.List;

public class BodyPartsFragment extends Fragment implements AdapterView.OnItemClickListener {

    private OnImageClickListener mCallback;
    private Context mContext;

    public BodyPartsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the body_parts_fragment layout
        View rootView = inflater.inflate(R.layout.body_parts_fragment, container, false);

        // Get the list of all images from AndroidImageAssets
        List<Integer> allImages = AndroidImageAssets.getAll();

        // Set up the adapter for the GridView with the list of all images
        GridView gridView = rootView.findViewById(R.id.body_parts_grid_view);
        MasterListAdapter adapter = new MasterListAdapter(getContext(), allImages);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    // This method is called when an item in the GridView is clicked
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mCallback != null) {
            mCallback.onImageSelected(position);
        }
    }

    public void setOnImageClickListener(OnImageClickListener listener) {
        mCallback = listener;
    }

    // This interface is used to communicate the clicked position to AndroidMeActivity
    public interface OnImageClickListener {
        void onImageSelected(int position);
    }

}
