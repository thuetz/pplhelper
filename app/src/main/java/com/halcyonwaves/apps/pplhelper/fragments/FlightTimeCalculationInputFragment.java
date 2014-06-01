package com.halcyonwaves.apps.pplhelper.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.halcyonwaves.apps.pplhelper.R;

public class FlightTimeCalculationInputFragment extends Fragment {
	private OnFragmentInteractionListener mListener;


	public FlightTimeCalculationInputFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of this fragment using the provided
	 * parameters.
	 *
	 * @return A new instance of fragment {@link com.halcyonwaves.apps.pplhelper.fragments.FlightTimeCalculationInputFragment}.
	 */
	public static FlightTimeCalculationInputFragment newInstance() {
		FlightTimeCalculationInputFragment fragment = new FlightTimeCalculationInputFragment();
		Bundle args = new Bundle();
		fragment.setArguments( args );
		return fragment;
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
		View inflatedView = inflater.inflate( R.layout.fragment_flight_time_calculation_input, container, false );
		Button calcResults = (Button) inflatedView.findViewById( R.id.calculate_results );
		calcResults.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View view ) {
				if ( FlightTimeCalculationInputFragment.this.mListener != null ) {
					FlightTimeCalculationInputFragment.this.mListener.onFragmentInteraction( 0.0f, 0.0f, 0, 0 );
				}
			}
		} );
		return inflatedView;
	}

	@Override
	public void onAttach( Activity activity ) {
		super.onAttach( activity );
		try {
			this.mListener = (OnFragmentInteractionListener) activity;
		} catch ( ClassCastException e ) {
			throw new ClassCastException( activity.toString() + " must implement OnFragmentInteractionListener" );
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		this.mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 */
	public interface OnFragmentInteractionListener {
		/**
		 * @param hoursBeforeFlight The running hours of the engine before the flight.
		 * @param hoursAfterFlight  The running ours of the engine after the flight.
		 * @param takeoffTimeHour   The hour of the takeoff.
		 * @param takeoffTimeMinute The minute of the takeoff.
		 */
		public void onFragmentInteraction( float hoursBeforeFlight, float hoursAfterFlight, int takeoffTimeHour, int takeoffTimeMinute );
	}
}
