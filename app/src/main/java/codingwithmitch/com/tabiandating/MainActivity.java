package codingwithmitch.com.tabiandating;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import codingwithmitch.com.tabiandating.models.FragmentTag;
import codingwithmitch.com.tabiandating.models.Message;
import codingwithmitch.com.tabiandating.models.User;
import codingwithmitch.com.tabiandating.settings.SettingsFragment;
import codingwithmitch.com.tabiandating.util.PreferenceKeys;

public class MainActivity extends AppCompatActivity implements
        IMainActivity, BottomNavigationViewEx.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener,
        KeyEvent.Callback{

    private static final String TAG = "MainActivity";

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Handle navigation view item clicks here.
        switch (item.getItemId()) {


            case R.id.home: {
                mFragmentsTags.clear();
                mFragmentsTags = new ArrayList<>();
                init();
                break;
            }

            case R.id.settings: {
                Log.d(TAG, "onNavigationItemSelected: Settings.");
                if (mSettingsFragment == null) {
                    mSettingsFragment = new SettingsFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mSettingsFragment, getString(R.string.tag_fragment_settings));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_settings));
                    mFragments.add(new FragmentTag(mSettingsFragment, getString(R.string.tag_fragment_settings)));
                }
                else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_settings));
                    mFragmentsTags.add(getString(R.string.tag_fragment_settings));
                }
                setFragmentVisibilities(getString(R.string.tag_fragment_settings));
                break;
            }

            case R.id.agreement: {
                Log.d(TAG, "onNavigationItemSelected: Agreement.");
                if (mAgreementFragment == null) {
                    mAgreementFragment = new AgreementFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mAgreementFragment, getString(R.string.tag_fragment_agreement));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_agreement));
                    mFragments.add(new FragmentTag(mAgreementFragment, getString(R.string.tag_fragment_agreement)));
                }
                else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_agreement));
                    mFragmentsTags.add(getString(R.string.tag_fragment_agreement));
                }
                setFragmentVisibilities(getString(R.string.tag_fragment_agreement));
                break;
            }

            case R.id.bottom_nav_home: {
                Log.d(TAG, "onNavigationItemSelected: HomeFragment.");

                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mHomeFragment, getString(R.string.tag_fragment_home));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_home));
                    mFragments.add(new FragmentTag(mHomeFragment, getString(R.string.tag_fragment_home)));
                }
                else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_home));
                    mFragmentsTags.add(getString(R.string.tag_fragment_home));
                }
                setFragmentVisibilities(getString(R.string.tag_fragment_home));
                item.setChecked(true);
                break;
            }

            case R.id.bottom_nav_connections: {
                Log.d(TAG, "onNavigationItemSelected: ConnectionsFragment.");

                if (mSavedConnectionsFragment == null) {
                    mSavedConnectionsFragment = new SavedConnectionsFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mSavedConnectionsFragment, getString(R.string.tag_fragment_saved_connections));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_saved_connections));
                    mFragments.add(new FragmentTag(mSavedConnectionsFragment, getString(R.string.tag_fragment_saved_connections)));
                }
                else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_saved_connections));
                    mFragmentsTags.add(getString(R.string.tag_fragment_saved_connections));
                }
                setFragmentVisibilities(getString(R.string.tag_fragment_saved_connections));
                item.setChecked(true);
                break;
            }

            case R.id.bottom_nav_messages: {
                Log.d(TAG, "onNavigationItemSelected: MessagesFragment.");
                if (mMessagesFragment == null) {
                    mMessagesFragment = new MessagesFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_content_frame, mMessagesFragment, getString(R.string.tag_fragment_messages));
                    transaction.commit();
                    mFragmentsTags.add(getString(R.string.tag_fragment_messages));
                    mFragments.add(new FragmentTag(mMessagesFragment, getString(R.string.tag_fragment_messages)));
                }
                else {
                    mFragmentsTags.remove(getString(R.string.tag_fragment_messages));
                    mFragmentsTags.add(getString(R.string.tag_fragment_messages));
                }
                setFragmentVisibilities(getString(R.string.tag_fragment_messages));
                item.setChecked(true);
                break;
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    //constants
    private static final int HOME_FRAGMENT = 0;
    private static final int CONNECTIONS_FRAGMENT = 1;
    private static final int MESSAGES_FRAGMENT = 2;


    //Fragments
    private HomeFragment mHomeFragment;
    private SavedConnectionsFragment mSavedConnectionsFragment;
    private MessagesFragment mMessagesFragment;
    private SettingsFragment mSettingsFragment;
    private ViewProfileFragment mViewProfileFragment;
    private ChatFragment mChatFragment;
    private AgreementFragment  mAgreementFragment;

    //widgets
    private BottomNavigationViewEx mBottomNavigationViewEx;
    private ImageView mHeaderImage;
    private DrawerLayout mDrawerLayout;

    //vars
    private ArrayList<String> mFragmentsTags = new ArrayList<>();
    private ArrayList<FragmentTag> mFragments = new ArrayList<>();
    private int mExitCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationViewEx = findViewById(R.id.bottom_nav_view);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        mHeaderImage = headerView.findViewById(R.id.header_image);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        isFirstLogin();
        setHeaderImage();
        initBottomNavigationView();
        setNavigationViewListener();
        init();
    }

    private void init(){
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_content_frame, mHomeFragment, getString(R.string.tag_fragment_home));
            transaction.commit();
            mFragmentsTags.add(getString(R.string.tag_fragment_home));
            mFragments.add(new FragmentTag(mHomeFragment, getString(R.string.tag_fragment_home)));

        }
        else {
            mFragmentsTags.remove(getString(R.string.tag_fragment_home));
            mFragmentsTags.add(getString(R.string.tag_fragment_home));
        }
        setFragmentVisibilities(getString(R.string.tag_fragment_home));
    }

    @Override
    public void onBackPressed() {

        int backStackCount = mFragmentsTags.size();
        if(backStackCount > 1){
            String topFragmentTag = mFragmentsTags.get(backStackCount - 1);

            String newTopFragmentTag = mFragmentsTags.get(backStackCount - 2);
            setFragmentVisibilities(newTopFragmentTag);

            mFragmentsTags.remove(topFragmentTag);

            mExitCount = 0;
        }
        else if( backStackCount == 1){
            mExitCount++;
            Toast.makeText(this, "1 more click to exit", Toast.LENGTH_SHORT).show();
        }
        if(mExitCount >= 2){
            super.onBackPressed();
        }

        hideKeyboard();
    }

    private void setNavigationIcon(String tagname) {
        Menu menu = mBottomNavigationViewEx.getMenu();
        MenuItem menuItem = null;
        if (tagname.equals(getString(R.string.tag_fragment_home))) {
            Log.d(TAG, "setNavigationIcon: home fragment is visible");
            menuItem = menu.getItem(HOME_FRAGMENT);
            menuItem.setChecked(true);
        }
        else if (tagname.equals(getString(R.string.tag_fragment_saved_connections))) {
            Log.d(TAG, "setNavigationIcon: connections fragment is visible");
            menuItem = menu.getItem(CONNECTIONS_FRAGMENT);
            menuItem.setChecked(true);
        }
        else if (tagname.equals(getString(R.string.tag_fragment_messages))) {
            Log.d(TAG, "setNavigationIcon: messages fragment is visible");
            menuItem = menu.getItem(MESSAGES_FRAGMENT);
            menuItem.setChecked(true);
        }
    }

    private void setFragmentVisibilities(String tagname){
        if(tagname.equals(getString(R.string.tag_fragment_home)))
            showBottomNavigation();
        else if(tagname.equals(getString(R.string.tag_fragment_saved_connections)))
            showBottomNavigation();
        else if(tagname.equals(getString(R.string.tag_fragment_messages)))
            showBottomNavigation();
        else if(tagname.equals(getString(R.string.tag_fragment_settings)))
            hideBottomNavigation();
        else if(tagname.equals(getString(R.string.tag_fragment_view_profile)))
            hideBottomNavigation();
        else if(tagname.equals(getString(R.string.tag_fragment_chat)))
            hideBottomNavigation();
        else if(tagname.equals(getString(R.string.tag_fragment_agreement)))
            hideBottomNavigation();

        for(int i = 0; i < mFragments.size(); i++){
            if(tagname.equals(mFragments.get(i).getTag())){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.show((mFragments.get(i).getFragment()));
                transaction.commit();
            }
            else{
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.hide((mFragments.get(i).getFragment()));
                transaction.commit();
            }
        }
        setNavigationIcon(tagname);
        printBackStack();
    }

    private void setHeaderImage() {
        Log.d(TAG, "setHeaderImage: setting header image for navigation drawer.");

        // better to set the header with glide. It's more efficient than setting the source directly
        Glide.with(this)
                .load(R.drawable.couple)
                .into(mHeaderImage);
    }

    private void hideBottomNavigation() {
        if (mBottomNavigationViewEx != null) {
            mBottomNavigationViewEx.setVisibility(View.GONE);
        }
    }

    private void showBottomNavigation() {
        if (mBottomNavigationViewEx != null) {
            mBottomNavigationViewEx.setVisibility(View.VISIBLE);
        }
    }

    private void setNavigationViewListener() {
        Log.d(TAG, "setNavigationViewListener: initializing navigation drawer onclicklistener.");
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initBottomNavigationView() {
        Log.d(TAG, "initBottomNavigationView: initializing bottom navigation view.");
        mBottomNavigationViewEx.enableAnimation(false);
        mBottomNavigationViewEx.setOnNavigationItemSelectedListener(this);
    }

    private void isFirstLogin() {
        Log.d(TAG, "isFirstLogin: checking if this is the first login.");

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstLogin = preferences.getBoolean(PreferenceKeys.FIRST_TIME_LOGIN, true);

        if (isFirstLogin) {
            Log.d(TAG, "isFirstLogin: launching alert dialog.");

            // launch the info dialog for first-time-users
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(getString(R.string.first_time_user_message));
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d(TAG, "onClick: closing dialog");
                    // now that the user has logged in, save it to shared preferences so the dialog won't
                    // pop up again
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(PreferenceKeys.FIRST_TIME_LOGIN, false);
                    editor.commit();
                    dialogInterface.dismiss();
                }
            });
            alertDialogBuilder.setTitle(" ");
            alertDialogBuilder.setIcon(R.drawable.tabian_dating);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    @Override
    public void inflateViewProfileFragment(User user) {

        if(mViewProfileFragment != null){
            getSupportFragmentManager().beginTransaction().remove(mViewProfileFragment).commitAllowingStateLoss();
        }
        mViewProfileFragment = new ViewProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.intent_user), user);
        mViewProfileFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_content_frame, mViewProfileFragment, getString(R.string.tag_fragment_view_profile));
        transaction.commit();
        mFragmentsTags.add(getString(R.string.tag_fragment_view_profile));
        mFragments.add(new FragmentTag(mViewProfileFragment, getString(R.string.tag_fragment_view_profile)));

        setFragmentVisibilities(getString(R.string.tag_fragment_view_profile));
    }

    @Override
    public void onMessageSelected(Message message) {

        if(mChatFragment != null){
            getSupportFragmentManager().beginTransaction().remove(mChatFragment).commitAllowingStateLoss();
        }
        mChatFragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.intent_message), message);
        mChatFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_content_frame, mChatFragment, getString(R.string.tag_fragment_chat));
        transaction.commit();
        mFragmentsTags.add(getString(R.string.tag_fragment_chat));
        mFragments.add(new FragmentTag(mChatFragment, getString(R.string.tag_fragment_chat)));

        setFragmentVisibilities(getString(R.string.tag_fragment_chat));
    }

    @Override
    public void setBottomNavigationVisibility(boolean visibility) {
        if(visibility)
            showBottomNavigation();
        else
            hideBottomNavigation();
    }

    @Override
    public void hideKeyboard() {
        if(getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            try{
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }
// FOR MORE INFORMATION: https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
//    public static void hideKeyboard(Context context, View view) {
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }

    private void printBackStack() {
        Log.d(TAG, "printBackStack: ----------------------------------- ");
        for (int i = 0; i < mFragmentsTags.size(); i++) {
            Log.d(TAG, "printBackStack: " + i + ": " + mFragmentsTags.get(i));
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(isChildFragmentVisible()){
            if(keyCode == KeyEvent.KEYCODE_TAB){
                moveFocusInFragment();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isChildFragmentVisible(){
        if(mViewProfileFragment!= null){
            if(mViewProfileFragment.isVisible()){
                return true;
            }
        }
        if(mChatFragment!= null){
            if(mChatFragment.isVisible()){
                return true;
            }
        }
        if(mSettingsFragment!= null){
            if(mSettingsFragment
                    .isVisible()){
                return true;
            }
        }
        return false;
    }

    private void moveFocusInFragment(){
        if(mViewProfileFragment != null){
            if(mViewProfileFragment.isVisible()){
                mViewProfileFragment.moveFocusForward();
            }
        }
        if(mChatFragment != null){
            if(mChatFragment.isVisible()){
                mChatFragment.moveFocusForward();
            }
        }
        if(mSettingsFragment != null){
            if(mSettingsFragment.isVisible()){
                mSettingsFragment.moveFocusForward();
            }
        }
    }
}












