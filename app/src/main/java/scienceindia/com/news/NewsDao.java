package scienceindia.com.news;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by shashankreddy509 on 8/26/15.
 *
 */
interface NewsDao {

    //This method take url as input and return the Bitmap as output.
    Bitmap fetchImage(String url);

    //This method get the JsonData as output.
    List<CategoryData> fetchJsonData();

    void fetchString();
}
