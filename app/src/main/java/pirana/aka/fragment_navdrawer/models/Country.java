package pirana.aka.fragment_navdrawer.models;

/**
 * Created by Pirana on 15/03/2016.
 */
public class Country {
    private String countryName;

    public Country() {}

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
