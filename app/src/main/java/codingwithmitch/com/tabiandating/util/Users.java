package codingwithmitch.com.tabiandating.util;

import android.net.Uri;

import codingwithmitch.com.tabiandating.R;
import codingwithmitch.com.tabiandating.models.User;


public class Users {

    public User[] USERS = {
            James, Elizabeth, Robert, Carol, Jennifer, Susan, Michael, William, Karen, Joseph, Nancy,
            Charles, Matthew, Sarah, Jessica, Donald, Mary, Paul,  Patricia,   Linda, Steve
    };
    

    /*
            Men
     */
    public static final User James = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.james).toString(),
            "James", "James@TabianDating.com", "1 604-851-1234", "Male","Female", "Looking");

    public static final User Robert = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.robert).toString(),
            "Robert", "Male","Robert@TabianDating.com", "1 604-551-1544","Female", "Looking");

    public static final User Michael = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.michael).toString(),
            "Michael", "Michael@TabianDating.com", "1 604-951-1211","Male","Female", "Looking");

    public static final User William = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.william).toString(),
            "William", "William@TabianDating.com", "1 604-222-1234","Male","Female", "Not Looking");

    public static final User Joseph = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.joseph).toString(),
            "Joseph", "Joseph@TabianDating.com", "1 604-765-1561","Male","Female", "Looking");

    public static final User Charles = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.charles).toString(),
            "Charles", "Charles@TabianDating.com", "1 604-444-8474","Male","Female", "Looking");

    public static final User Matthew = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.mattew).toString(),
            "Matthew", "Matthew@TabianDating.com", "1 604-786-1222","Male","Female", "Looking");

    public static final User Donald = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.donald).toString(),
            "Donald", "Donald@TabianDating.com", "1 604-855-9999","Male","Female", "Looking");

    public static final User Paul = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.paul).toString(),
            "Paul", "Paul@TabianDating.com", "1 604-823-4321","Male","Female", "Looking");

    public static final User Steve = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.steve).toString(),
            "Steve", "Steve@TabianDating.com", "1 604-888-8786","Male","Female", "Looking");


    /*
            Females
     */
    public static final User Mary = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.mary).toString(),
            "Mary", "Mary@TabianDating.com", "1 604-822-5031","Female","Male", "Looking");

    public static final User Patricia = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.patricia).toString(),
            "Patricia", "Patricia@TabianDating.com", "1 604-877-1673","Female","Male", "Looking");

    public static final User Jennifer = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.jennifer).toString(),
            "Jennifer", "Jennifer@TabianDating.com", "1 604-866-0931","Female","Male", "Looking");

    public static final User Elizabeth = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.elizabeth).toString(),
            "Elizabeth", "Elizabeth@TabianDating.com", "1 604-811-1111","Female","Male", "Looking");

    public static final User Linda = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.linda).toString(),
            "Linda", "Linda@TabianDating.com", "1 604-876-9011","Female","Male", "Looking");

    public static final User Susan = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.susan).toString(),
            "Susan", "Susan@TabianDating.com", "1 604-811-1277","Female","Male", "Looking");

    public static final User Jessica = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.jessica).toString(),
            "Jessica", "Jessica@TabianDating.com", "1 604-876-1010","Female","Male", "Looking");

    public static final User Sarah = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.sarah).toString(),
            "Sarah", "Sarah@TabianDating.com", "1 604-851-9911","Female","Male", "Looking");

    public static final User Karen = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.karen).toString(),
            "Karen", "Karen@TabianDating.com", "1 604-822-1284","Female","Male", "Looking");

    public static final User Nancy = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.nancy).toString(),
            "Nancy", "Nancy@TabianDating.com", "1 604-889-1536","Female","Male", "Looking");

    public static final User Carol = new User(Uri.parse("android.resource://codingwithmitch.com.tabiandating/" + R.drawable.carol).toString(),
            "Carol", "Carol@TabianDating.com", "1 604-844-9090","Do Not Identify","Anyone", "Looking");
}



















