package planty;

import java.util.Random;

/**
 * Implements weather behaviours and bonuses.
 *
 * @author Nereare
 * @version 1.2.03
 */
public class Weather {

    // <editor-fold defaultstate="collapsed" desc="constants">
    /**
     * The cold weather (climatological status on cold temperatures) names.
     */
    private static final String COLD_WEATHERS[] = {
        "snowing heavily", "snowing", "cloudy", "clear", "dry", "desertic"
    };
    /**
     * The warm and mild weather (climatological status on warm and mild temperatures) names.
     */
    private static final String WARM_WEATHERS[] = {
        "storming", "raining", "cloudy", "clear", "dry", "desertic"
    };
    /**
     * The temperatures names.
     */
    private static final String TEMPERATURES[] = {
        "freezing", "cold", "mild", "warm", "frying"
    };
    /**
     * Represents the Hyper Humid weather (either heavy snow or storm).
     */
    public static final int WEATHER_XX_HUMID = 0;
    /**
     * Represents the Highly Humid weather (either snow or rain).
     */
    public static final int WEATHER_X_HUMID = 1;
    /**
     * Represents the Humid weather (cloudy day).
     */
    public static final int WEATHER_HUMID = 2;
    /**
     * Represents the Normal weather (clear day).
     */
    public static final int WEATHER_NORMAL = 3;
    /**
     * Represents the Dry weather (clear low relative humidity day).
     */
    public static final int WEATHER_DRY = 4;
    /**
     * Represents the Desertic weather (clear extremily low relative humidity day).
     */
    public static final int WEATHER_DESERTIC = 5;
    
    /**
     * Represents the Freezing temperature.
     */
    public static final int TEMP_FREEZING = 0;
    /**
     * Represents the Cold temperature.
     */
    public static final int TEMP_COLD = 1;
    /**
     * Represents the Mild temperature.
     */
    public static final int TEMP_MILD = 2;
    /**
     * Represents the Warm temperature.
     */
    public static final int TEMP_WARM = 3;
    /**
     * Represents the Frying temperature.
     */
    public static final int TEMP_FRYING = 4;
    /**
     * Represents Spring.
     */
    private static final int spring = 0;
    /**
     * Represents Summer.
     */
    private static final int summer = 1;
    /**
     * Represents Autumn.
     */
    private static final int autumn = 2;
    /**
     * Represents Winter.
     */
    private static final int winter = 3;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="weather data (variables)">
    /**
     * Pseudorandom number generator.
     */
    private Random rnd = new Random((int) (Math.random() * 1000000000));
    /**
     * The current weather status.
     */
    private int weather;
    /**
     * The current temperature.
     */
    private int temperature;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="get's and set's">
    /**
     * Does what you'd expect it to.
     *
     * @return  the current weather.
     */
    public int getWeather() {
        return weather;
    }

    /**
     * Does what you'd expect it to.
     *
     * @return  the name of the current weather.
     */
    public String getWeatherName() {
        if ( getTemperature() < TEMP_MILD ) return COLD_WEATHERS[ getWeather() ];
        else return WARM_WEATHERS[ getWeather() ];
    }

    /**
     * Does what you'd expect it to.
     *
     * @return  the current temperature.
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Does what you'd expect it to.
     *
     * @return  the current temperature's name.
     */
    public String getTemperatureName() {
        return TEMPERATURES[ getTemperature() ];
    }

    /**
     * Sets the current weather to the given status.
     *
     * @param newWeather    the new weather, spaning from 0 (Hyper Humid) to 5 (Desertic).
     */
    public void setWeather(int newWeather) {
        if ( newWeather >= WEATHER_XX_HUMID && newWeather <= WEATHER_DESERTIC )
            weather = newWeather;
        else weather = WEATHER_NORMAL;
    }

    /**
     * Sets the current temperature to the given one.
     *
     * @param newTemperature    the new temperature, spaning from 0 (Freezing) to 4 (Frying).
     */
    public void setTemperature(int newTemperature) {
        if ( newTemperature >= TEMP_FREEZING && newTemperature <= TEMP_FRYING )
            temperature = newTemperature;
        else temperature = TEMP_MILD;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="utilities">
    /**
     * Rerandomizes the weather.
     * @deprecated  Due to the excessive randomicity of the resulting climates. Use <code>newDay(temperature, weather)</code> instead.
     */
    public void newDay() {
        setTemperature(rnd.nextInt(5));
        setWeather(rnd.nextInt(6));
    }

    /**
     * Sets the weather to the given values;
     *
     * @param temperature   the new temperature, at least 0 (Freezing), at most 4 (Frying).
     * @param weather       the new weather, at least 0 (Hyper Humid), at most 5 (Desertic).
     */
    public void newDay(int temperature, int weather) {
        if ( temperature >= TEMP_FREEZING && temperature <= TEMP_FRYING ) setTemperature(temperature);
        else setTemperature(TEMP_MILD);
        if ( weather >= WEATHER_XX_HUMID && weather <= WEATHER_DESERTIC ) setWeather(weather);
        else setWeather(WEATHER_NORMAL);
    }

    /**
     * Rerandomizes the wheather accordingly to the season.
     *
     * @param season    the season id, ranging from 0 (Spring) to 3 (Winter).
     */
    public void newDay(int season) {
        switch (season) {
            case spring:
                setTemperature( 1 + rnd.nextInt(3) );
                setWeather( 0 + rnd.nextInt(4) );
                break;
            case summer:
                setTemperature( 2 + rnd.nextInt(3) );
                setWeather( 2 + rnd.nextInt(4) );
                break;
            case autumn:
                setTemperature( 1 + rnd.nextInt(3) );
                setWeather( 2 + rnd.nextInt(4) );
                break;
            case winter:
                setTemperature( 0 + rnd.nextInt(3) );
                setWeather( 0 + rnd.nextInt(4) );
                break;
            default:
                newDay(TEMP_MILD, WEATHER_NORMAL);
                break;
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="constructor methods">
    /**
     * Constructor method.
     */
    public Weather() {
        setTemperature(TEMP_MILD);
        setWeather(WEATHER_NORMAL);
    }

    /**
     * Constructor method.
     *
     * @param startMild whether or not the weather should be initialized with a mild set.
     */
    public Weather(boolean startMild) {
        if (startMild) {
            setTemperature(TEMP_MILD);
            setWeather(WEATHER_NORMAL);
        }
        else {
            setTemperature( 1 + rnd.nextInt(3) );
            setWeather( 0 + rnd.nextInt(4) );
        }
    }
    // </editor-fold>

}