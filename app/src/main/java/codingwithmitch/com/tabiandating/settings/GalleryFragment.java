package codingwithmitch.com.tabiandating.settings;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import codingwithmitch.com.tabiandating.R;
import codingwithmitch.com.tabiandating.util.FileSearch;


public class GalleryFragment extends Fragment {
    private static final String TAG = "GalleryFragment";


    //constants
    private static final int NUM_GRID_COLUMNS = 3;
    private static final int NEW_PHOTO_REQUEST = 3567;


    //widgets
    private GridView gridView;
    private ImageView galleryImage;
    private Spinner directorySpinner;

    //vars
    private ArrayList<String> directories;
    private String mSelectedImage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        galleryImage = (ImageView) view.findViewById(R.id.galleryImageView);
        gridView = (GridView) view.findViewById(R.id.gridView);
        directorySpinner = (Spinner) view.findViewById(R.id.spinnerDirectory);
        directories = new ArrayList<>();
        Log.d(TAG, "onCreateView: started.");

        ImageView close = (ImageView) view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing the gallery fragment.");
                getActivity().setResult(NEW_PHOTO_REQUEST);
                getActivity().finish();
            }
        });


        TextView choose = (TextView) view.findViewById(R.id.choose);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: photo has been chosen.");

                if(!mSelectedImage.equals("")){
                    getActivity().setResult(
                            NEW_PHOTO_REQUEST,
                            getActivity().getIntent().putExtra(getString(R.string.intent_new_gallery_photo), mSelectedImage));
                    getActivity().finish();
                }
            }
        });

        init();

        return view;
    }


    private void init(){
        String rootDir = Environment.getExternalStorageDirectory().getPath();

        //check for other folders indide "/storage/emulated/0/pictures"
        String picturesDir = rootDir + File.separator + "Pictures";
        directories.add(picturesDir);
        if (FileSearch.getDirectoryPaths(picturesDir) != null) {
            directories = FileSearch.getDirectoryPaths(picturesDir);
        }
        String cameraDir = rootDir + File.separator + "DCIM" + File.separator + "Camera";
        directories.add(cameraDir);

        ArrayList<String> directoryNames = new ArrayList<>();
        for (int i = 0; i < directories.size(); i++) {
            Log.d(TAG, "init: directory: " + directories.get(i));
            int index = directories.get(i).lastIndexOf("/");
            String string = directories.get(i).substring(index);
            directoryNames.add(string);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, directoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        directorySpinner.setAdapter(adapter);

        directorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: selected: " + directories.get(position));

                //setup our image grid for the directory chosen
                setupGridView(directories.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void setupGridView(String selectedDirectory){
        Log.d(TAG, "setupGridView: directory chosen: " + selectedDirectory);
        final ArrayList<String> imgURLs = FileSearch.getFilePaths(selectedDirectory);

        if(imgURLs.size() > 0){
            //set the grid column width
            int gridWidth = getResources().getDisplayMetrics().widthPixels;
            int imageWidth = gridWidth/NUM_GRID_COLUMNS;
            gridView.setColumnWidth(imageWidth);

            //use the grid adapter to adapter the images to gridview
            GridImageAdapter adapter = new GridImageAdapter(getActivity(), R.layout.layout_grid_imageview, imgURLs);
            gridView.setAdapter(adapter);

            //set the first image to be displayed when the activity fragment view is inflated
            try{
                setImage(imgURLs.get(0), galleryImage);
                mSelectedImage = imgURLs.get(0);
            }catch (ArrayIndexOutOfBoundsException e){
                Log.e(TAG, "setupGridView: ArrayIndexOutOfBoundsException: " +e.getMessage() );
            }

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d(TAG, "onItemClick: selected an image: " + imgURLs.get(position));

                    setImage(imgURLs.get(position), galleryImage);
                    mSelectedImage = imgURLs.get(position);
                }
            });
        }
    }


    private void setImage(String imgURL, ImageView imageView){
        Log.d(TAG, "setImage: setting image");

        Glide.with(getActivity())
                .load(imgURL)
                .into(imageView);
    }

}































