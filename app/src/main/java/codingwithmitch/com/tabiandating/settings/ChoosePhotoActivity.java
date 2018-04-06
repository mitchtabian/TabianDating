package codingwithmitch.com.tabiandating.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import codingwithmitch.com.tabiandating.R;


public class ChoosePhotoActivity extends AppCompatActivity implements KeyEvent.Callback{

    private static final String TAG = "ChoosePhotoActivity";
    private static final int GALLERY_FRAGMENT = 0;
    private static final int PHOTO_FRAGMENT = 1;

    //fragments
    private GalleryFragment mGalleryFragment;
    private PhotoFragment mPhotoFragment;

    //widgets
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_container);

        setupViewPager();
    }

    /**
     * setup viewpager for manager the tabs
     */
    private void setupViewPager(){
        MyPagerAdapter adapter =  new MyPagerAdapter(getSupportFragmentManager());
        mGalleryFragment = new GalleryFragment();
        mPhotoFragment = new PhotoFragment();
        adapter.addFragment(mGalleryFragment);
        adapter.addFragment(mPhotoFragment);
        mViewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_bottom);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(GALLERY_FRAGMENT).setText(getString(R.string.tag_fragment_gallery));
        tabLayout.getTabAt(PHOTO_FRAGMENT).setText(getString(R.string.tag_fragment_photo));

    }



    private void tabRight(){
        if(mViewPager.getCurrentItem() == 0){
            mViewPager.setCurrentItem(1);
        }
    }

    private void tabLeft(){
        if(mViewPager.getCurrentItem() == 1){
            mViewPager.setCurrentItem(0);
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if(event.isCtrlPressed()){
                    tabLeft();
                }
                return false;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if(event.isCtrlPressed()){
                    tabRight();
                }
                return false;

            default:
                return super.onKeyUp(keyCode, event);
        }
    }
}










