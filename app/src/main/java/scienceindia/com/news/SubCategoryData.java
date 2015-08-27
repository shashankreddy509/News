package scienceindia.com.news;

/**
 * Created by shashankreddy509 on 8/27/15.
 * This calls is used as a template for storing the News Sub-Category with all the details about the News Sub-Category.
 */
class SubCategoryData {
    private final String subCategoryName;
    private final String subCategoryId;
    private final String imageUrl;
    private final String description;

    public SubCategoryData(String mCategoryName,String mCategoryId,String mImageUrl,String mDescription){
        this.subCategoryId = mCategoryId;
        this.subCategoryName = mCategoryName;
        this.imageUrl = mImageUrl;
        this.description = mDescription;
    }

    public String getSubCategoryName() {
        return this.subCategoryName;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
}
