package scienceindia.com.news;

import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shashankreddy509 on 8/27/15.
 * This class calls the server fetches the result from server, parser that data and stores in the
 * local variables.
 */
public class FierceNewsDao implements NewsDao {

    private String jsonResult = "";
    private ArrayList<CategoryData> mCategoryDatas = new ArrayList<>(); // header titles
    // child data in format of header title, child title
    private ExpandableListAdapter expandableListAdapter;

    @Override
    public Bitmap fetchImage(String url) {
        return null;
    }

    @Override
    public ArrayList<CategoryData> fetchJsonData() {
        try {
            JSONParser jParser = new JSONParser();
            JSONArray json = jParser.getJSONFromUrl("http://fierce-mesa-1366.herokuapp.com/");
            jsonResult = JSONParser.result;
            if (jsonResult.contains("SUCCESS")) {
                for (int i = 0; i < json.length(); i++) {
                    JSONObject mjsJsonObject = json.getJSONObject(i);
                    List<SubCategoryData> subCategoryDataItems = new ArrayList<>();
                    JSONArray mjsJsonArray = mjsJsonObject.getJSONArray("Subcategories");
                    for (int j = 0; j < mjsJsonArray.length(); j++) {
                        SubCategoryData mSubCategoryData = new SubCategoryData(mjsJsonArray.getJSONObject(j).get("subCategoryName").toString(), mjsJsonArray.getJSONObject(j).get("subCategoryId").toString(), mjsJsonArray.getJSONObject(j).get("ImageUrl").toString(), mjsJsonArray.getJSONObject(j).get("description").toString());
                        subCategoryDataItems.add(mSubCategoryData);
                    }
                    CategoryData mCategoryData = new CategoryData(mjsJsonObject.getString("CategoryName"), mjsJsonObject.getString("CategoryId"), mjsJsonObject.getString("ImageUrl"), subCategoryDataItems);
                    mCategoryDatas.add(mCategoryData);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mCategoryDatas;
    }

    @Override
    public void fetchString() {

    }

    public CategoryData getHeaderData(int location) {
        return this.mCategoryDatas.get(location);
    }

    public SubCategoryData getChildData(int locationHeader, int locationChild) {
        return this.mCategoryDatas.get(locationHeader).getSubCategoryData().get(locationChild);
    }

    public ExpandableListAdapter getExpandableAdapter(List<CategoryData> mData) {
        this.expandableListAdapter = new ExpandableListAdapter(News.mContext, mData);
        return this.expandableListAdapter;
    }
}
