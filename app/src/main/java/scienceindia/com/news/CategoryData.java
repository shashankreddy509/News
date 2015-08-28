package scienceindia.com.news;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shashankreddy509 on 8/27/15.
 * This calls is used as a template for storing the
 * News Category with all the details about the News Category.
 */
class CategoryData{
    private final String categoryName;
    private final String categoryId;
    private final String imageUrl;
    private final List<SubCategoryData> subCategoryData = new ArrayList<>();

    public CategoryData(String mCategoryName, String mCategoryId,
                        String mImageUrl, List<SubCategoryData> mSubCategoryData) {
        this.categoryId = mCategoryId;
        this.categoryName = mCategoryName;
        this.imageUrl = mImageUrl;
        for(int i=0;i<mSubCategoryData.size();i++){
            this.subCategoryData.add(mSubCategoryData.get(i));
        }
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public List<SubCategoryData> getSubCategoryData(){
        return this.subCategoryData;
    }

}
