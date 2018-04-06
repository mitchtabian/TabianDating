package codingwithmitch.com.tabiandating.settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;

/**
 * Created by User on 3/30/2018.
 */

public class MyViewPager extends ViewPager {

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean executeKeyEvent(@NonNull KeyEvent event) {
        return false;
    }
}
