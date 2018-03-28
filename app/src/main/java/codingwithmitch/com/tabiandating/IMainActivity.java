package codingwithmitch.com.tabiandating;

import codingwithmitch.com.tabiandating.models.Message;
import codingwithmitch.com.tabiandating.models.User;

/**
 * Created by User on 1/24/2018.
 */

public interface IMainActivity {

    void inflateViewProfileFragment(User user);

    void onMessageSelected(Message message);

    void onBackPressed();


}
