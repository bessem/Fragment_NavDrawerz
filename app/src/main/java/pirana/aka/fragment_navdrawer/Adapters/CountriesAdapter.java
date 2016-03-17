package pirana.aka.fragment_navdrawer.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pirana.aka.fragment_navdrawer.R;
import pirana.aka.fragment_navdrawer.models.Country;

/**
 * Created by Pirana on 15/03/2016.
 */
public class CountriesAdapter extends ArrayAdapter
{
    private Context currentContext;
    public CountriesAdapter(Context context, List countries) {
        super(context, R.layout.country_sample,countries);
        this.currentContext = context;
    }
    private static class ViewHolder{
        TextView countryName;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = new ViewHolder();
        Country countrySample =(Country)getItem(position);
        LayoutInflater mInflater = (LayoutInflater) currentContext
            .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.country_sample, parent, false);
            holder.countryName = (TextView) convertView.findViewById(R.id.countryName);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.countryName.setText(countrySample.getCountryName());
        return  convertView;
    }
}
