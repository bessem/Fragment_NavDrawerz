package pirana.aka.fragment_navdrawer.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.net.UnknownHostException;
import java.util.List;

import pirana.aka.fragment_navdrawer.Adapters.CountriesAdapter;
import pirana.aka.fragment_navdrawer.R;
import pirana.aka.fragment_navdrawer.db.CountryOpenHelper;
import pirana.aka.fragment_navdrawer.models.CountryDetail;
import pirana.aka.fragment_navdrawer.utils.CountryAPIParser;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private boolean restResultIsReady = false;
    final String TAG= "SecondFragment";
    private List countryList;
    private List countryDetailedList;
    private ListAdapter mAdapter;
    private OnFragmentInteractionListener mListener;
    private ProgressDialog spinnerPopUp;
    private ListView countriesListView;
    protected View mView;
    private CountryOpenHelper countryOpenHelper;
    public SecondFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mView =  inflater.inflate(R.layout.fragment_second, container, false);
        while (!restResultIsReady){
        }
        countriesListView = (ListView) mView.findViewById(R.id.countrylistView);
        mAdapter = new CountriesAdapter(getActivity(),countryList);
        countriesListView.setAdapter(mAdapter);
        countriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        spinnerPopUp.dismiss();
    }

    @Override
    public void onAttach(Context context) {

        spinnerPopUp = new ProgressDialog(getActivity());
        spinnerPopUp.setIndeterminate(true);
        spinnerPopUp.setCanceledOnTouchOutside(false);
        spinnerPopUp.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        spinnerPopUp.setMessage("Loading. Please wait...");
        spinnerPopUp.show();
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        new HttpRequsetTask(context).execute();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private class HttpRequsetTask extends AsyncTask<Void,Void,List>
    {
        private Context inheritedContext ;
        public HttpRequsetTask(Context context){
            this.inheritedContext = context;
        }
        @Override
        protected List doInBackground(Void... params) {
            countryOpenHelper = CountryOpenHelper.getInstance(inheritedContext);
            long cachCount =0;
            try {
                cachCount = countryOpenHelper.Count();
            }catch (Exception e){
                Log.d(this.toString(),e.getMessage());
            }
            if( cachCount> 1){
                countryList=countryOpenHelper.findAllCountries();
            }else {
                try {
                    CountryAPIParser parser = new CountryAPIParser();
                    countryList = parser.getCountryAPIParsingResult();
                    countryDetailedList = parser.getCountryAPIParsingDetailedResult();
                    for(Object countryDetail : countryDetailedList){
                        countryOpenHelper.Insert((CountryDetail)countryDetail);
                    }

                }catch (Exception e){
                    Log.e(TAG,e.getMessage(),e);
                    if (e instanceof UnknownHostException){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage(R.string.alert_connection_lost).setTitle(R.string.alert_title_connection_lost);
                        builder.create();
                    }

                }
            }
            restResultIsReady = true;
            return null;
        }
    }
}



