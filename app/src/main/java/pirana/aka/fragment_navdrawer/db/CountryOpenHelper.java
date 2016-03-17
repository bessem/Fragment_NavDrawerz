package pirana.aka.fragment_navdrawer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pirana.aka.fragment_navdrawer.models.Country;
import pirana.aka.fragment_navdrawer.models.CountryDetail;

/**
 * Created by Pirana on 17/03/2016.
 */
public class CountryOpenHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String COUNTRY_TABLE_NAME = "country";
    private static final String NAME = "_NAME";
    private static final String CAPITAL = "_CAPITAL";
    private static final String POULATION = "_POULATION";
    private static final String COUNTRY_TABLE_CREATE = "CREATE TABLE " + COUNTRY_TABLE_NAME + "(" + NAME + " TEXT PRIMARY KEY," +
            " " + CAPITAL + " TEXT,"
    + POULATION + " TEXT );";;

    private static Context mContext;
    private static CountryOpenHelper mInstance =null;

    public static CountryOpenHelper getInstance(Context ctx) {
        if(mInstance == null){
            mInstance = new CountryOpenHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    private CountryOpenHelper(Context context) {
        super(context, COUNTRY_TABLE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        Log.d(this.toString(),"data Base Operation:Table Created successfully");
        System.out.println();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(Count()<=1){
        db.execSQL(COUNTRY_TABLE_CREATE);
        Log.d(this.toString(), "data Base Operation:Table Upgraded successfully");}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //if(oldVersion != newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + COUNTRY_TABLE_NAME);
        Log.d(this.toString(), "DROP TABLE IF EXISTS ");
        onCreate(db);//}
    }

    public Country getCountry(String name) {
        Log.d(this.toString(),"get Country ...");
        Country country = new Country();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(COUNTRY_TABLE_NAME, new String[]{NAME}, NAME + "=?", new String[]{String.valueOf(name)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()){
            cursor.moveToFirst();
            country = new Country((cursor.getString(cursor.getColumnIndex(NAME))));
            Log.d(this.toString(),country.toString());
            cursor.close();
        }
        return country;
    }

    public boolean Insert(CountryDetail detailedCountry) {
            Log.d(this.toString(), "Insertion ...");
            ContentValues values = new ContentValues();
            values.put(NAME, detailedCountry.getName());
            values.put(CAPITAL, detailedCountry.getCapital());
            values.put(POULATION, detailedCountry.getPopulation());
            try {
                SQLiteDatabase db = this.getWritableDatabase();
                db.insert(COUNTRY_TABLE_NAME, null, values);
                Log.d(this.toString(), "Successufluly inserted");
                return true;
            } catch (SQLiteException e) {
                Log.d(this.toString(), "SQLiteException on inserted"+e.getLocalizedMessage());
                return false;
            }
    }
    public List<CountryDetail> findAllDetailedCountries() {
        Log.d(this.toString(),"finding all detailed countries ...");
        List<CountryDetail> detailedCountries = new ArrayList<CountryDetail>();
        String selectQuery = "SELECT  * FROM " + COUNTRY_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CountryDetail countryDetail = new CountryDetail(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                detailedCountries.add(countryDetail);
            } while (cursor.moveToNext());
        }
        return detailedCountries;
    }
    public List<Country> findAllCountries() {
        Log.d(this.toString(),"finding all countries ...");
        List<Country> countries = new ArrayList<Country>();
        String selectQuery = "SELECT  * FROM " + COUNTRY_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Country country = new Country(cursor.getString(0));
                countries.add(country);
            } while (cursor.moveToNext());
        }
        return countries;
    }
    public long Count() {
        Log.d(this.toString(),"Counting ...");
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db,COUNTRY_TABLE_NAME);
    }
}
