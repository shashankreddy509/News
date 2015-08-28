package scienceindia.com.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shashankreddy509 on 8/27/15.
 * This class is used as an adapter that hold the data from expandable list view.
 */
class ExpandableListAdapter extends BaseExpandableListAdapter {

    private final Context mContext;
    private final List<CategoryData> listDataHeader; // header titles

    public ExpandableListAdapter(Context context, List<CategoryData> listDataHeader) {
        this.mContext = context;
        this.listDataHeader = listDataHeader;
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listDataHeader.get(groupPosition).getSubCategoryData().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataHeader.get(groupPosition).getSubCategoryData().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        CategoryData mCategoryData = (CategoryData) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.category, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.tvCategory);
        lblListHeader.setText(mCategoryData.getCategoryName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SubCategoryData mSubCategoryData = (SubCategoryData) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.subcategory, null);
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.tvSubCategory);
        txtListChild.setText(mSubCategoryData.getSubCategoryName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
