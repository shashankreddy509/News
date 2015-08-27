package scienceindia.com.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by shashankreddy509 on 8/27/15.
 * This class is used as an adapter that hold the data from expandable list view.
 */
class ExpandableListAdapter extends BaseExpandableListAdapter {

    private final Context _context;
    private final List<CategoryData> _listDataHeader; // header titles
    // child data in format of header title, child title
    private final HashMap<CategoryData, List<SubCategoryData>> _listDataChild;

    public ExpandableListAdapter(Context context, List<CategoryData> listDataHeader,
                                 HashMap<CategoryData, List<SubCategoryData>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
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
            LayoutInflater infalInflater = (LayoutInflater) this._context
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
            LayoutInflater infalInflater = (LayoutInflater) this._context
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
