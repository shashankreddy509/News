package scienceindia.com.news;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shashankreddy509 on 8/26/15.
 *
 */
interface DAO {

    List<CategoryData> _listDataHeader = new ArrayList<>(); // header titles
    // child data in format of header title, child title
    HashMap<CategoryData, List<SubCategoryData>> _listDataChild = new HashMap<>();

    String strUrl = "http://fierce-mesa-1366.herokuapp.com/";

    //This method take url as input and return the Bitmap as output.
    Bitmap fetchImage(String url);

    //This method get the JsonData as output.
    void fetchJsonData();

    void fetchString();
}
