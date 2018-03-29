package codingwithmitch.com.tabiandating;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import codingwithmitch.com.tabiandating.models.User;
import codingwithmitch.com.tabiandating.util.PreferenceKeys;
import codingwithmitch.com.tabiandating.util.Users;


public class MessagesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "MessagesFragment";

    //widgets
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MessagesRecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;

    //vars
    private ArrayList<User> mUsers = new ArrayList<>();
    private IMainActivity mIMainActivity;
    static int mAppHeight;
    static int currentOrientation = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        Log.d(TAG, "onCreateView: started.");
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mSearchView = (SearchView) view.findViewById(R.id.action_search);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        getConnections();
        initSearchView();
        setKeyboardVisibilityListener();

        return view;
    }

    private void initSearchView(){
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        mSearchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        mSearchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mRecyclerViewAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mRecyclerViewAdapter.getFilter().filter(query);
                return false;
            }
        });
    }

    private void getConnections(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_CONNECTIONS, new HashSet<String>());
        Users users = new Users();
        if(mUsers != null){
            mUsers.clear();
        }
        for(User user: users.USERS){
            if(savedNames.contains(user.getName())){
                mUsers.add(user);
            }
        }
        if(mRecyclerViewAdapter == null){
            initRecyclerView();
        }
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mRecyclerViewAdapter = new MessagesRecyclerViewAdapter(getActivity(), mUsers);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onRefresh() {
        getConnections();
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        mRecyclerViewAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: called.");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity) getActivity();
    }

    public void setKeyboardVisibilityListener() {

        final View contentView = getActivity().findViewById(android.R.id.content);

        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private int mPreviousHeight;

            @Override
            public void onGlobalLayout() {

                int newHeight = contentView.getHeight();

                if (newHeight == mPreviousHeight)

                    return;

                mPreviousHeight = newHeight;

                Log.d(TAG, "onGlobalLayout: new height: " + newHeight);
                if (getActivity().getResources().getConfiguration().orientation != currentOrientation) {

                    currentOrientation = getActivity().getResources().getConfiguration().orientation;

                    mAppHeight =0;
                    Log.d(TAG, "onGlobalLayout: current Orientation: " + currentOrientation);
                    Log.d(TAG, "onGlobalLayout: app height: " + mAppHeight);
                }

                if (newHeight >= mAppHeight) {

                    mAppHeight = newHeight;
                    Log.d(TAG, "onGlobalLayout: app height: " + mAppHeight);
                }
                Log.d(TAG, "onGlobalLayout: -------------------------\n");

                if (newHeight != 0) {
                    MessagesFragment messagesFragment = (MessagesFragment) getActivity()
                            .getSupportFragmentManager().findFragmentByTag(getActivity().getString(R.string.tag_fragment_messages));
                    if(messagesFragment.isVisible()){
                        if (mAppHeight > newHeight) {
                            Log.d(TAG, "onGlobalLayout: hiding bottom nav");
                            // Height decreased: keyboard was shown
                            mIMainActivity.setBottomNavigationVisibility(false);

                        }
                        else {
                            Log.d(TAG, "onGlobalLayout: showing bottom nav");
                            // Height increased: keyboard was hidden
                            mIMainActivity.setBottomNavigationVisibility(true);
                        }
                    }
                }

            }

        });

    }

}
