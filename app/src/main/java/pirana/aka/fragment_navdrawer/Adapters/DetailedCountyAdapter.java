package pirana.aka.fragment_navdrawer.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pirana.aka.fragment_navdrawer.R;
import pirana.aka.fragment_navdrawer.models.CountryDetail;
import pirana.aka.fragment_navdrawer.models.CountryDetailViewHolder;

/**
 * Created by Pirana on 16/03/2016.
 */
public class DetailedCountyAdapter extends RecyclerView.Adapter<CountryDetailViewHolder> {

    private List<CountryDetail> detailedCountries ;

    public DetailedCountyAdapter(List<CountryDetail> detailedCountry) {
        this.detailedCountries = detailedCountry;
    }


    @Override
    public CountryDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View countySampleView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_view , parent , false );

        return new CountryDetailViewHolder(countySampleView);
    }

    @Override
    public void onBindViewHolder(CountryDetailViewHolder holder, int position) {
        CountryDetail countryDetailSample = detailedCountries.get(position);
        holder.setvName(countryDetailSample.getName());
        holder.setvCapital(countryDetailSample.getCapital());
        holder.setvPopulation(countryDetailSample.getPopulation());
    }

    @Override
    public int getItemCount() {
        return this.detailedCountries.size();
    }
}
