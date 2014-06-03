package com.halcyonwaves.apps.pplhelper.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.halcyonwaves.apps.pplhelper.FlightHours;
import com.halcyonwaves.apps.pplhelper.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class FlightTimeCalculationResultsFragment extends Fragment {
	private static final String ARG_HOURS_BEFORE_FLIGHT = "hoursBeforeFlight";
	private static final String ARG_HOURS_AFTER_FLIGHT = "hoursAfterFlight";
	private static final String ARG_TAKEOFF_TIME_HOURS = "takeoffTimeHours";
	private static final String ARG_TAKEOFF_TIME_MINUTES = "takeoffTimeMinutes";

	private FlightHours mHoursBeforeFlight;
	private FlightHours mHoursAfterFlight;
	private Calendar mLocalTakeoffTime;

	public FlightTimeCalculationResultsFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param hoursBeforeFlight  TODO
	 * @param hoursAfterFlight   TODO
	 * @param takeoffTimeHours   TODO
	 * @param takeoffTimeMinutes TODO
	 * @return A new instance of fragment FlightTimeCalculationResultsFragment.
	 */
	public static FlightTimeCalculationResultsFragment newInstance( FlightHours hoursBeforeFlight, FlightHours hoursAfterFlight, int takeoffTimeHours, int takeoffTimeMinutes ) {
		FlightTimeCalculationResultsFragment fragment = new FlightTimeCalculationResultsFragment();
		Bundle args = new Bundle();
		args.putString( ARG_HOURS_BEFORE_FLIGHT, hoursBeforeFlight.toString() );
		args.putString( ARG_HOURS_AFTER_FLIGHT, hoursAfterFlight.toString() );
		args.putInt( ARG_TAKEOFF_TIME_HOURS, takeoffTimeHours );
		args.putInt( ARG_TAKEOFF_TIME_MINUTES, takeoffTimeMinutes );
		fragment.setArguments( args );
		return fragment;
	}

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		if ( this.getArguments() != null ) {
			this.mHoursBeforeFlight = new FlightHours( getArguments().getString( ARG_HOURS_BEFORE_FLIGHT ) );
			this.mHoursAfterFlight = new FlightHours( getArguments().getString( ARG_HOURS_AFTER_FLIGHT ) );
			this.mLocalTakeoffTime = new GregorianCalendar();
			this.mLocalTakeoffTime.set( Calendar.HOUR_OF_DAY, getArguments().getInt( ARG_TAKEOFF_TIME_HOURS ) );
			this.mLocalTakeoffTime.set( Calendar.MINUTE, getArguments().getInt( ARG_TAKEOFF_TIME_MINUTES ) );
		}
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
		View inflatedView = inflater.inflate( R.layout.fragment_flight_time_calculation_results, container, false );

		//
		EditText flightTimeResult = (EditText) inflatedView.findViewById( R.id.flight_time_result );
		EditText takeoffTimeUTCResult = (EditText) inflatedView.findViewById( R.id.takeoff_time_utc );

		//
		Calendar takeoffTimeUTC = new GregorianCalendar( TimeZone.getTimeZone( "UTC" ) );
		takeoffTimeUTC.setTimeInMillis( this.mLocalTakeoffTime.getTimeInMillis() );

		//
		flightTimeResult.setText( this.mHoursAfterFlight.difference( this.mHoursBeforeFlight ).toString() );
		takeoffTimeUTCResult.setText( String.format( "%02d:%02d", takeoffTimeUTC.get( Calendar.HOUR_OF_DAY ), takeoffTimeUTC.get( Calendar.MINUTE ) ) );

		//
		return inflatedView;
	}
}
