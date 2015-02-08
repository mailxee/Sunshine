package com.blogspot.xeeshan.sunshine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Zeeshan on 08/02/2015.
 */
public class MyArrayAdapter extends ArrayAdapter
{
    Context mContext;
    int mResource;
    List mList;
   public MyArrayAdapter(Context context, int resource, List list)
    {
        super(context, resource, list);
		
        mContext=context;
        mResource=resource;
        mList=list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater mInflater;
		View mView;
        String[] mCoordinates= (String[]) getItem(position);
        
        
		if(convertView==null)
		{
			mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mView=mInflater.inflate(mResource, parent, false);
		}
		else mView=convertView;
		
        TextView mLongitude=(TextView) mView.findViewById(R.id.list_item_textview_longitude);
        TextView mLatitude=(TextView) mView.findViewById(R.id.list_item_textview_latitude);
        TextView mName=(TextView) mView.findViewById(R.id.list_item_textview_name);

        mLongitude.setText(mCoordinates[0]);
        mLatitude.setText(mCoordinates[1]);
        mName.setText(mCoordinates[2]);

        return mView;
    }

}
