package pirana.aka.fragment_navdrawer.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import java.net.UnknownHostException;
import java.util.List;

import pirana.aka.fragment_navdrawer.Adapters.DetailedCountyAdapter;
import pirana.aka.fragment_navdrawer.R;
import pirana.aka.fragment_navdrawer.db.CountryOpenHelper;
import pirana.aka.fragment_navdrawer.models.CountryDetail;
import pirana.aka.fragment_navdrawer.utils.CountryAPIParser;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private CountryOpenHelper countryOpenHelper;
    private View mView;
    private DetailedCountyAdapter detailedCountyAdapter;
    final private String TAG ="ThirdFragment";
    private List countryList;
    private boolean restResultIsReady = false;
    private ProgressDialog dialog;
    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_third, container, false);
        dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        while (!restResultIsReady){
        }

        RecyclerView recList = (RecyclerView) mView.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        detailedCountyAdapter = new DetailedCountyAdapter(countryList);
        recList.setLayoutManager(linearLayoutManager);
        recList.setAdapter(detailedCountyAdapter);
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        new HttpRequsetTask(context).execute();
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog.dismiss();
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
            countryOpenHelper = CountryOpenHelper.getInstance(getActivity());
            if(countryOpenHelper.Count() > 1){
                countryList=countryOpenHelper.findAllDetailedCountries();
            }else {
                try {
                    CountryAPIParser parser = new CountryAPIParser();
                    countryList = parser.getCountryAPIParsingDetailedResult();
                    for (Object countryDetail : countryList) {
                        countryOpenHelper.Insert((CountryDetail) countryDetail);
                    }
                    restResultIsReady = true;
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage(), e);
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
