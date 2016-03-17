package pirana.aka.fragment_navdrawer.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pirana.aka.fragment_navdrawer.R;

/**
 * Created by Pirana on 16/03/2016.
 */
public class CountryDetailViewHolder extends RecyclerView.ViewHolder {
    protected TextView vName;
    protected TextView vCapital;
    protected TextView vPopulation;
    public CountryDetailViewHolder(View view) {
        super(view);
        vName = (TextView) view.findViewById(R.id.txtName);
        vCapital = (TextView) view.findViewById(R.id.txtCapital);
        vPopulation = (TextView) view.findViewById(R.id.txtPopulation);
    }

    public TextView getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName.setText(vName);
    }

    public TextView getvCapital() {
        return vCapital;
    }

    public void setvCapital(String vCapital) {
        this.vCapital.setText(vCapital);
    }

    public TextView getvPopulation() {
        return vPopulation;
    }

    public void setvPopulation(String vPopulation) {
        this.vPopulation.setText(vPopulation);
    }
}
