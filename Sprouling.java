package planty;

/**
 * Defines the plant being.
 * 
 * @version 1.6.14r06
 * @author Nereare
 */
public class Sprouling {

    // <editor-fold defaultstate="collapsed" desc="constants">
    /**
     * The class placeholder for not-defined string data.
     */
    private static final String foo = "NO_DATA";

    /**
     * The health-status names.
     */
    private static final String HEALTH[] = {
        "Dead", "Dying", "Worst", "Bad", "Neutral", "Good", "Great", "Perfect"
    };
    /**
     * Plant health constant.
     */
    public static final int DEAD = 0, DYING = 1, WORST = 2, BAD = 3,
            NEUTRAL = 4, GOOD = 5, GREAT = 6, PERFECT = 7;

    /**
     * Maximum health.
     */
    public static final int MAX_HEALTH = 7;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="plant data (variables)">
    /**
     * The plant\'s name.
     */
    private String strName;
    /**
     * The plant\'s species.
     */
    private String strSp;
    /**
     * The plant\'s description -as the creator defines-.
     */
    private String strDesc;
    /**
     * The plant\'s age, in days.
     */
    private int intAge;
    /**
     * The plant\'s health.
     * <br />
     * It <strong>can\'t</strong> reach zero!
     */
    private int intHealth;
    /**
     * The minimum of fruits the plant can bare.
     */
    private int intMinFruit;
    /**
     * The maximum of fruits the plant can bare.
     */
    private int intMaxFruit;
    /**
     * The maximum age for the plant.
     */
    private int intMaxAge;
    /**
     * The amount of time the fruit need to bear a fruit.
     */
    private int intFruitTime;
    /**
     * The number of waterings the plant has.
     */
    private int intWater;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="get's">
    /**
     * Does what you'd expect it to do.
     *
     * @return  the plant's name.
     */
    public String getName() {
        return strName;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @return  the plant's species.
     */
    public String getSp() {
        return strSp;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @return  the plant's description.
     */
    public String getDescription() {
        return strDesc;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @return  the plant's age.
     */
    public int getAge() {
        return intAge;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @return  the plant's health.
     */
    public int getHealth() {
        return intHealth;
    }

    /**
     * Returns the health name.
     *
     * @param health    the health number.
     * @return          the health name, by the given health number;
     */
    public String getHealth(int health) {
        return HEALTH[health];
    }

    /**
     * Does what you'd expect it to do.
     *
     * @return  the plant's minimum of fruits.
     */
    public int getMinFruit() {
        return intMinFruit;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @return  the plant's maximum of fruits.
     */
    public int getMaxFruit() {
        return intMaxFruit;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @return  the plant's time to bear fruits.
     */
    public int getFruitTime() {
        return intFruitTime;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @return  the plant's maximum age.
     */
    public int getMaxAge() {
        return intMaxAge;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @return  the plant's number of waterings.
     */
    public int getWaterings() {
        return intWater;
    }

    /**
     * Gets the number of fruits it'll bear and resets its watering state.
     *
     * @return  the number of fruits it'll bear, zero if the plant is dead or <code>-1</code> if it the fruits are not ripe. *LoL*
     */
    public int harvest() {
        int returnable = getMinFruit();

        if (wasWatered()) {
            returnable = ( getMaxFruit() * getHealth() ) / ( ( getFruitTime() - getWaterings() + 1 ) * MAX_HEALTH );
            if ( returnable < getMinFruit() ) returnable = getMinFruit();
        }

        setWaterings(0);
        if (isFruitTime()) return returnable;
        else return -1;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="is'es and has'es">
    /**
     * Checks if the plant has a name.
     *
     * @return  <code>true</code> if it has one, <code>false</code> otherwise.
     */
    public boolean hasName() {
        if ( getName().equals(foo) ) return false;
        else return true;
    }

    /**
     * Checks if the plant has a species.
     *
     * @return  <code>true</code> if it has one, <code>false</code> otherwise.
     */
    public boolean hasSp() {
        if ( getSp().equals(foo) ) return false;
        else return true;
    }

    /**
     * Checks if the plant has a description.
     *
     * @return  <code>true</code> if it has one, <code>false</code> otherwise.
     */
    public boolean hasDescription() {
        if ( getDescription().equals(foo) ) return false;
        else return true;
    }

    /**
     * Checks if the plant is alive.
     *
     * @return  <code>true</code> if it is, <code>false</code> otherwise.
     */
    public boolean isAlive() {
        if (getHealth() > 0) return true;
        else return false;
    }

    /**
     * Checks whether it is day for the fruits to be ripe or not.
     * 
     * @return  <code>true</code> if it is day, <code>false</code> otherwise.
     */
    public boolean isFruitTime() {
        if ( getAge() != 0 && getAge() % getFruitTime() == 0 ) return true;
        else return false;
    }

    /**
     * Checks if the plant can still age up.
     *
     * @return  <code>true</code> if it can, <code>false</code> otherwise.
     */
    public boolean canAge() {
        if ( getAge() < getMaxAge() ) return true;
        else return false;
    }

    /**
     * Checks if the heal() method can be run.
     *
     * @return  <code>true</code> if the health is beneath the maximum, <code>false</code> false otherwise.
     */
    public boolean canHeal() {
        if ( getHealth() < MAX_HEALTH ) return true;
        else return false;
    }

    /**
     * Checks if the plant was watered at least once.
     *
     * @return  <code>true</code> if it was, <code>false</code> otherwise.
     */
    public boolean wasWatered() {
        if ( getWaterings() > 0 ) return true;
        else return false;
    }

    /**
     * Checks whether it is the plant's last day of life.
     *
     * @return  <code>true</code> if it is, <code>false</code> otherwise.
     */
    public boolean isLastDay() {
        if ( getAge() == getMaxAge() ) return true;
        else return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="set's">
    /**
     * Does what you'd expect it to do.
     *
     * @param name  the new name.
     */
    public void setName(String name) {
        if ( !name.isEmpty() ) strName = name;
        else strName = foo;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @param species   the new species.
     */
    public void setSp(String species) {
        if ( !species.isEmpty() ) strSp = species;
        else strSp = foo;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @param desc  the new description.
     */
    public void setDescription(String desc) {
        if ( !desc.isEmpty() ) strDesc = desc;
        else strDesc = foo;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @param age   the new age.
     */
    public void setAge(int age) {
        if ( age > 0 ) intAge = age;
        else intAge = 0;
    }

    /**
     * Adds one day to the plant.
     */
    public void age() {
        if (canAge()) intAge++;
        else kill();
    }

    /**
     * Does what you'd expect it to do.
     *
     * @param health    the new health.
     */
    public void setHealth(int health) {
        if ( health > 1 && health <= MAX_HEALTH ) intHealth = health;
        else intHealth = NEUTRAL;
    }

    /**
     * Sums the given amount to the plant's health, as long as the resulting value is possible.
     *
     * @param modifier  any integer number.
     * @return          <code>true</code> if the sum was possible and made, <code>false</code> otherwise.
     * @deprecated      Not working correctly, for now, handle modifiers to health by using <code>setHealth( getHealth() + modifier )</code>.
     */
    public boolean setHealthBy(int modifier) {
        if ( (getHealth() + modifier) >= 0 && (getHealth() + modifier) <= MAX_HEALTH ) {
            setHealth( getHealth() + modifier );
            return true;
        }
        else return false;
    }

    /**
     * Heals the plant\'s health by one.
     */
    public void heal() {
        if ( canHeal() && isAlive() ) intHealth++;
    }

    /**
     * Damages the plant\'s health by one.
     */
    public void harm() {
        if ( getHealth() != DEAD ) intHealth--;
    }

    /**
     * Set\'s the plants health to zero, hence killing it.
     */
    public void kill() {
        intHealth = DEAD;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @param minFruit  new minimum of fruits.
     */
    private void setMinFruit(int minFruit) {
        if ( minFruit > 0 ) intMinFruit = minFruit;
        else intMinFruit = 0;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @param maxFruit  new maximum of fruits.
     */
    private void setMaxFruit(int maxFruit) {
        if ( maxFruit > 1 ) intMaxFruit = maxFruit;
        else intMaxFruit = 1;
    }

    private void setFruitTime(int fruitTime) {
        if ( fruitTime > 0 ) intFruitTime = fruitTime;
        else intFruitTime = 2;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @param maxAge    new maximum age.
     */
    private void setMaxAge(int maxAge) {
        if ( maxAge > 1 ) intMaxAge = maxAge;
        else intMaxAge = 1;
    }

    /**
     * Does what you'd expect it to do.
     *
     * @param waterings new number of waterings.
     */
    public void setWaterings(int waterings) {
        if ( waterings > 0 ) intWater = waterings;
        else intWater = 0;
    }

    /**
     * Water once (adds one to watering count).
     */
    public void water() {
        intWater++;
    }

    /**
     * Returns the plant's state to a neutral (alive, zero-age and so on) state.
     */
    public void renew() {
        setAge(0);
        setHealth(NEUTRAL);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="constructor's">
    /**
     * Constructor method.
     *
     * @param name          guess what?
     * @param species       guess what?
     * @param description   guess what?
     * @param maxFruit      the maximum of fruits this plant will be able to bear, given the minimun as zero.
     * @param fruitTime     the number of days between one frutification and the next.
     * @param maxAge        the maximum age, given it's starting at age zero (days).
     */
    public Sprouling(String name, String species, String description,
            int maxFruit, int fruitTime, int maxAge) {
        setName(name);
        setSp(species);
        setDescription(description);

        setAge(0);
        setHealth(NEUTRAL);

        setMinFruit(0);
        setMaxFruit(maxFruit);
        setFruitTime(fruitTime);

        setMaxAge(maxAge);
    }

    /**
     * Constructor method.
     *
     * @param name          guess what?
     * @param species       guess what?
     * @param description   guess what?
     * @param minFruit      the minimum of fruits this plant will be able to bear.
     * @param maxFruit      the maximum of fruits this plant will be able to bear.
     * @param fruitTime     the number of days between one frutification and the next.
     * @param maxAge        the maximum age for this plant, given it's starting at age zero (days).
     */
    public Sprouling(String name, String species, String description,
            int minFruit, int maxFruit, int fruitTime, int maxAge) {
        setName(name);
        setSp(species);
        setDescription(description);

        setAge(0);
        setHealth(NEUTRAL);

        setMinFruit(minFruit);
        setMaxFruit(maxFruit);
        setFruitTime(fruitTime);

        setMaxAge(maxAge);
    }

    /**
     * Constructor method.
     *
     * @param name          guess what?
     * @param species       guess what?
     * @param description   guess what?
     * @param age           the starting age.
     * @param health        the starting health.
     * @param minFruit      the minimum of fruits this plant will be able to bear.
     * @param maxFruit      the maximum of fruits this plant will be able to bear.
     * @param fruitTime     the number of days between one frutification and the next.
     * @param maxAge        the maximum age.
     */
    public Sprouling(String name, String species, String description, int age,
            int health, int minFruit, int maxFruit, int fruitTime, int maxAge) {
        setName(name);
        setSp(species);
        setDescription(description);

        setAge(age);
        setHealth(health);

        setMinFruit(minFruit);
        setMaxFruit(maxFruit);
        setFruitTime(fruitTime);

        setMaxAge(maxAge);
    }
    // </editor-fold>

}