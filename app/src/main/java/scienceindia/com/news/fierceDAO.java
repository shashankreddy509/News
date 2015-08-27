package scienceindia.com.news;

import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shashankreddy509 on 8/27/15.
 * This class calls the server fetches the result from server, parser that data and stores in the local variables.
 */
public class fierceDAO implements DAO {

    private String jsonResult = "";

    @Override
    public Bitmap fetchImage(String url) {
        return null;
    }

    @Override
    public void fetchJsonData() {
        try {
            Log.d("Starting Service", "Fetching data from server");
            JSONParser jParser = new JSONParser();
            JSONArray json = jParser.getJSONFromUrl(DAO.strUrl);
            Log.d("Starting Service", "Fetching data from server finished");
            Log.d("Result", jsonResult);
            jsonResult = JSONParser.result;
            if (jsonResult.contains("SUCCESS")) {
                for (int i = 0; i < json.length(); i++) {

                    JSONObject mjsJsonObject = json.getJSONObject(i);

                    CategoryData mCategoryData = new CategoryData(mjsJsonObject.getString("CategoryName"), mjsJsonObject.getString("CategoryId"), mjsJsonObject.getString("ImageUrl"));

                    _listDataHeader.add(mCategoryData);

                    List<SubCategoryData> subCategoryDataItems = new ArrayList<>();

                    JSONArray mjsJsonArray = mjsJsonObject.getJSONArray("Subcategories");
                    for (int j = 0; j < mjsJsonArray.length(); j++) {
                        SubCategoryData mSubCategoryData = new SubCategoryData(mjsJsonArray.getJSONObject(j).get("subCategoryName").toString(), mjsJsonArray.getJSONObject(j).get("subCategoryId").toString(), mjsJsonArray.getJSONObject(j).get("ImageUrl").toString(), mjsJsonArray.getJSONObject(j).get("description").toString());
                        subCategoryDataItems.add(mSubCategoryData);
                    }

                    assert _listDataChild != null;
                    _listDataChild.put(mCategoryData, subCategoryDataItems);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void fetchString() {

    }
}
