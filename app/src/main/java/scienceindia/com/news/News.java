package scienceindia.com.news;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;

import java.util.ArrayList;

//This deprecation is because of setNavigationMode is been deprecated in new version to avoid the conflict we have added the deprecation.
@SuppressWarnings("deprecation")
public class News extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */

    static Context mContext;
    private CharSequence mTitle;
    private String strJsonResult = "";
    private FierceNewsDao mFierceDAO;
    static String strPresentUrl = "", SelectedName = "";

    private ProgressDialog progress;
    private final Handler mHandler = new Handler();
    private final Runnable mRunnable = new Runnable() {
        @SuppressWarnings("deprecation")
        @Override
        public void run() {
            //After fetching the data from server it check for the success or failure and does the operation accordingly.
            if (!strJsonResult.equalsIgnoreCase("")) {
                final AlertDialog alert = new AlertDialog.Builder(News.this).create();
                alert.setCanceledOnTouchOutside(false);
                alert.setTitle("Information");
                alert.setMessage(strJsonResult);
                alert.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //perform when ok button is pressed.
                    }
                });
                alert.show();
                strJsonResult = "";
            } else {
                //The server data has been fetched, parsed and saved to the local variables and doing the next operations.
                mNavigationDrawerFragment = (NavigationDrawerFragment)
                        getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
                mNavigationDrawerFragment.getCategoryData(mCategoryDatas);
                mTitle = getTitle();

                // Set up the drawer.
                mNavigationDrawerFragment.setUp(
                        R.id.navigation_drawer,
                        (DrawerLayout) findViewById(R.id.drawer_layout));

            }
            progress.dismiss();
        }
    };

    private ArrayList<CategoryData> mCategoryDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mFierceDAO = new FierceNewsDao();
        mContext = this;

        //Get the data from server and parses the data.
        progress = new ProgressDialog(News.this);
        progress.setMessage("Please wait..");
        progress.setCancelable(false);
        progress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mCategoryDatas = mFierceDAO.fetchJsonData();
                mFierceDAO.getExpandableAdapter(mCategoryDatas);
                mHandler.post(mRunnable);
            }
        }).start();


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    //This method is used to get the title of the Navigation control.
    private void onSectionAttached(String Name) {
        mTitle = Name;
    }

    //This method is used to restore the action bar and sets the title of the action with the updated values.
    private void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(mTitle);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mNavigationDrawerFragment != null) {
            if (!mNavigationDrawerFragment.isDrawerOpen()) {
                // Only show items in the action bar relevant to this screen
                // if the drawer is not showing. Otherwise, let the drawer
                // decide what to show in the action bar.
                getMenuInflater().inflate(R.menu.news, menu);
                restoreActionBar();
                return true;
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_news, container, false);
            try {
                Log.d("URL", strPresentUrl);
                Log.d("Is Empty", strPresentUrl.equalsIgnoreCase("") + "");
                if (!strPresentUrl.equalsIgnoreCase("")) {
                    new DownloadImageTask((ImageView) rootView.findViewById(R.id.imgShowImage))
                            .execute(strPresentUrl);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((News) activity).onSectionAttached(SelectedName);
        }
    }
}
