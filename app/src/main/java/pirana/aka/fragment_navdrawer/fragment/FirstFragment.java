package pirana.aka.fragment_navdrawer.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import pirana.aka.fragment_navdrawer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    private View mView;
    private OnFragmentInteractionListener mListener;

    public FirstFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_first, container, false);
//        Uri imageUri = Uri.parse("https://i.imgur.com/tGbaZCY.jpg");
//        SimpleDraweeView draweeView1 = (SimpleDraweeView) mView.findViewById(R.id.fresco_image_view1);
//        SimpleDraweeView draweeView2 = (SimpleDraweeView) mView.findViewById(R.id.fresco_image_view2);
//        SimpleDraweeView draweeView3 = (SimpleDraweeView) mView.findViewById(R.id.fresco_image_view3);
//        SimpleDraweeView draweeView4 = (SimpleDraweeView) mView.findViewById(R.id.fresco_image_view4);
//        draweeView1.setImageURI(imageUri);
//        draweeView2.setImageURI(imageUri);
//        draweeView3.setImageURI(imageUri);
//        draweeView4.setImageURI(imageUri);
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
}
