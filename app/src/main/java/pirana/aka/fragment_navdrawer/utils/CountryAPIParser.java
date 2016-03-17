package pirana.aka.fragment_navdrawer.utils;

import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import pirana.aka.fragment_navdrawer.models.Country;
import pirana.aka.fragment_navdrawer.models.CountryDetail;

/**
 * Created by Pirana on 15/03/2016.
 */
public class CountryAPIParser {
    private Object parseResult;
    public CountryAPIParser(){}
    public List<Country> getCountryAPIParsingResult(){

        List<Country> countries = new ArrayList<Country>();
        try {
            final String url = "https://restcountries.eu/rest/v1/subregion/western asia";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            parseResult = restTemplate.getForObject(url, Object.class);
            List<LinkedHashMap<String,String>> resultMap = new ArrayList<LinkedHashMap<String,String>>();
            resultMap = (List<LinkedHashMap<String,String>>) parseResult;
            Country preResut;
            for (LinkedHashMap<String,String> lm : resultMap){
                preResut = new Country(lm.get("name"));
                countries.add(preResut);
            }
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
        return countries;
    }
    public List<CountryDetail> getCountryAPIParsingDetailedResult(){

        List<CountryDetail> countries = new ArrayList<CountryDetail>();
        try {
            final String url = "https://restcountries.eu/rest/v1/subregion/western asia";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            parseResult = restTemplate.getForObject(url, Object.class);
            List<LinkedHashMap<String,String>> resultMap = new ArrayList<LinkedHashMap<String,String>>();
            resultMap = (List<LinkedHashMap<String,String>>) parseResult;
            CountryDetail preResut;
            for (LinkedHashMap<String,String> lm : resultMap){
                preResut= new CountryDetail(lm.get("name"),lm.get("capital"),String.valueOf(lm.get("population")));
                countries.add(preResut);
            }
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
        return countries;
    }
}
