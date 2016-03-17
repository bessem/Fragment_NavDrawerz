package pirana.aka.fragment_navdrawer.models;

/**
 * Created by Pirana on 16/03/2016.
 */
public class CountryDetail {
    protected String name;
    protected String capital;
    protected String population;

    protected static final String NAME_PREFIX = "Name_";
    protected static final String CAPITAL_PREFIX = "Capital_";
    protected static final String POPULATION_PREFIX = "Population_";

    public String getCapital() {
        return capital;
    }

    public CountryDetail(String name, String capital, String population) {
        this.name = name;
        this.capital = capital;
        this.population = population;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
