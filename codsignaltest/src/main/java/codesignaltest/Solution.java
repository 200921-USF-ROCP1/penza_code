package codesignaltest;

import java.io.*;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

/* data.json
 * {
    "id": string,
    "id2": string,
    "key": string,
    "created": number, // UNIX timestamp (UTC) in milliseconds
    "district": string
}
 */

// Main class should be named 'Solution'
class Solution {
    
    static Integer[] meanValuesPerDay = new Integer[7]; // 0-6; Sun-Sat
    
    static ArrayList<dataJson> jSonList = new ArrayList<dataJson>();
    static ArrayList<dataCsv> csvList = new ArrayList<dataCsv>();
    static HashMap<String, valuesToAverage> avHashMap = new HashMap<String, valuesToAverage>();
    
    public static void main(String[] args) {
        
        // Add some fake csv data
        csvList.add( new dataCsv( "1", 10.911, -4.03, 7.223, "A" ) );
        csvList.add( new dataCsv( "2", 14.732, -14.13, 100.5, "A" ) );
        csvList.add( new dataCsv( "3", 15.221, 24.83, 100.5, "D" ) );
        csvList.add( new dataCsv( "4", 229.083, 17.91, 100.5, "A" ) );
        csvList.add( new dataCsv( "5", 64.914, 284.874, 100.5, "B" ) );
        csvList.add( new dataCsv( "6", -18.492, -68.721, 100.5, "C" ) );
        csvList.add( new dataCsv( "7", 77.323, -8.533, 100.5, "B" ) );
        csvList.add( new dataCsv( "8", -54.619, 29.410, 100.5, "D" ) );
        csvList.add( new dataCsv( "9", 320.491, -73.567, 100.5, "B" ) );
        csvList.add( new dataCsv( "10", -12.000, -88.412, 100.5, "C" ) );
        
        // Add some fake JSON data
        // I don't remember anything about timezones from the test
        jSonList.add( new dataJson( "1", 1603417587 ) ); // 10-23-2020 Fri
        jSonList.add( new dataJson( "2", 1603238400 ) ); // 10-21-2020 Wed
        jSonList.add( new dataJson( "3", 1603152000 ) ); // 10-20-2020 Tues
        jSonList.add( new dataJson( "4", 1603080780 ) ); // 10-19-2020 Mon
        jSonList.add( new dataJson( "5", 1603026600 ) ); // 10-18-2020 Sun
        jSonList.add( new dataJson( "6", 1602948060 ) ); // 10-17-2020 Sat
        jSonList.add( new dataJson( "7", 1602810000 ) ); // 10-16-2020 Fri
        jSonList.add( new dataJson( "8", 1602731940 ) ); // 10-15-2020 Thur
        jSonList.add( new dataJson( "9", 1602637740 ) ); // 10-14-2020 Wed
        jSonList.add( new dataJson( "10", 1602087720 ) ); // 10-07-2020 Wed
        
        System.out.println( "Day of week 0: " + getDayOfWeek( jSonList.get(0).getCreated() ) );
        System.out.println( "Day of week 1: " + getDayOfWeek( jSonList.get(1).getCreated() ) );
        System.out.println( "Day of week 2: " + getDayOfWeek( jSonList.get(2).getCreated() ) );
        
        getValuesToAverage();
        
        getLowestAverageDay();
    }
    
    public static DayOfWeek getDayOfWeek( long created ) {
    	Instant instant = Instant.ofEpochSecond(created);
    	LocalDate localDate = LocalDate.ofInstant( instant, ZoneOffset.UTC );
    	//System.out.println( "Created: "  + created );
    	//System.out.println( "utc date: " + localDate.toString() );
    	
    	// Find the day from the local date 
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
        
        return dayOfWeek;
    }
    
    public static void getValuesToAverage() {
    	for( int i=0; i<jSonList.size(); i++ ) {
   			DayOfWeek dow = getDayOfWeek( jSonList.get(i).getCreated() );
   			System.out.println( "dow: " + dow.name() );
    		for( int j=0; j<csvList.size(); j++ ) {
    			if( csvList.get(j).getId().equals( jSonList.get(i).getId() )) {
    				
	       			if( avHashMap.get( dow.name()) == null ) {
	       				avHashMap.put( dow.name(), new valuesToAverage(csvList.get(i).getSomeValueToAverage()) );
	       			} else {
	       				avHashMap.get( dow.name() ).increaseSomeValueToAverage(csvList.get(i).getSomeValueToAverage() );
	       			}
    			}
    		}
    	}
    }
    
    public static void getLowestAverageDay() {
    	String lowestAvgDay = "";
    	Double lowestAvg = null;
    	
    	for( int i=1; i<=7; i++ ) {
	    	DayOfWeek dow = DayOfWeek.of(i);
	    	double curAvg = avHashMap.get(dow.name()).getAverage();
	    	System.out.println( "Avg for : " + dow.name() + ": " + curAvg );
	    	
	    	if( lowestAvg == null || lowestAvg > curAvg ) {
	    		lowestAvg = curAvg;
	    		lowestAvgDay = dow.name();
	    	}
    	}
    	
    	System.out.println( "Lowest avg day: " + lowestAvgDay );
    	
    }

}

class valuesToAverage {
	double someValueToAverage;
	double count=0;
	double average;
	
	valuesToAverage() {};
	
	valuesToAverage( double someValueToAverage ) {
		this.someValueToAverage = someValueToAverage;
		count = 1;
		this.average = someValueToAverage/count;
	}
	public void increaseSomeValueToAverage( double valueToAdd ) {
		this.someValueToAverage = this.someValueToAverage + valueToAdd;
		count++;
		this.average = someValueToAverage/count;
	}
	public double getAverage() {
		return this.average;
	}
}

class dataCsv {
	
	String id;
	double someValueToAverage;
	double x;
	double y;
	String district;
	
	dataCsv() {};
	
	dataCsv( String id, double someValueToAverage, double x, double y, String district ) {
		this.id = id;
		this.someValueToAverage = someValueToAverage;
		this.x = x;
		this.y = y;
		this.district = district;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getSomeValueToAverage() {
		return someValueToAverage;
	}
	public void setSomeValueToAverage(Integer someValueToAverage) {
		this.someValueToAverage = someValueToAverage;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
}

class dataJson {
	String id;
	long created;
	
	dataJson() {}
	
	dataJson( String id, long created ) {
		this.id = id;
		this.created = created;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	
}