package scienceindia.com.news;

/**
 * Created by shashankreddy509 on 8/27/15.
 * This calls is used as a template for storing the News Category with all the details about the News Category.
 */
class CategoryData {
    private final String categoryName;
    private final String categoryId;
    private final String imageUrl;

    public CategoryData(String mCategoryName, String mCategoryId, String mImageUrl) {
        this.categoryId = mCategoryId;
        this.categoryName = mCategoryName;
        this.imageUrl = mImageUrl;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

}
