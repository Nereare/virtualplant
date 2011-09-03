package planty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * A list of precreated plants to ease plant creation.
 *
 * @author Nereare
 * @version 1.2.00
 */
public class PlantList {

    // <editor-fold defaultstate="collapsed" desc="constant">
    /**
     * An array of plants. They have no name, and it must be explictly set.
     */
    public static final Sprouling plants[] = {
        new Sprouling("", "Apple tree", "A large especimen of the angiosperms, bearing many of its deep red traditional-looking fruits.",
        5, 30, 10, 150),
        new Sprouling("", "Pear tree", "A large especimen of the angiosperms, bearing many of its paleish yellow fruits.",
        5, 30, 10, 150),
        new Sprouling("", "Orange tree", "A medium especimen of the angiosperms, bearing many of its yellow circular fruits.",
        8, 20, 5, 150),
        new Sprouling("", "Lemon tree", "A medium especimen of the angiosperms, bearing some of its green circular fruits.",
        8, 15, 5, 150),
        new Sprouling("", "Strawberry bush", "A very small especimen of the angiosperms, bearing some of its red heart-shaped fruits.",
        0, 10, 5, 80),
        new Sprouling("", "Almond tree", "A large especimen of the gimnosperms, bearing some of its hard-shelled seeds.",
        5, 30, 10, 150),
        new Sprouling("", "Crystal tree", "A small sized tree of a alien vegetal branch, bearing perfectly sphearical fruits which are completly translucid and of a celestial blue. The fruits are sweet as honey and fresh as clear water.",
        0, 5, 10, 50),
        new Sprouling("", "Pine tree", "An once tall and imponent member of the gimnosperms, who now is trimmed to a small-sized house tiny little garden.",
        0, 5, 10, 50)
    };
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="echoes and reads">
    /**
     * Executes a line-reading of the default <code>InputStream</code>.
     *
     * @return  either the data inputed to the command-line or <code>null</code> in case of error.
     */
    private static String read() {
        try {
            return (new BufferedReader(new InputStreamReader(System.in))).readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int readInt() {
        int returnable = 0;
        boolean ok = false;

        while (!ok) {
            try {
                returnable = Integer.parseInt( read() );
                ok = true;
            }
            catch (NumberFormatException e) {
                echoErr("USE NUMBERS ONLY!");
            }
        }

        return returnable;
    }

    /**
     * Reades the default <code>InputStream</code> for an &quot;yes&quot; or &quot;no&quot;.
     * 
     * @return  <code>false</code> if the user input "n", <code>true</code> otherwise.
     */
    private static boolean yes() {
        if ( !read().equalsIgnoreCase("n") ) return true;
        else return false;
    }

    /**
     * Prints an empty line in the default <code>PrintStream</code>.
     */
    private static void echo() {
        System.out.println();
    }

    /**
     * Prints the given content.
     *
     * @param o something to print.
     */
    private static void echo(Object o) {
        System.out.println(o);
    }

    private static void echoErr(Object o) {
        System.err.println(o);
    }
    // </editor-fold>

    /**
     * Loads a plant from the list registered.
     *
     * @return  a <code>Sprouling</code>.
     */
    public static Sprouling loadFromList() {
        Sprouling plant = null;

        boolean again = true;
        while (again) {
            echo("Select one from the list by its index, raging from 1 to " + plants.length + ":");
            boolean input_err = true;
            while (input_err) {
                try { plant = plants[readInt() - 1]; input_err = false; }
                catch (ArrayIndexOutOfBoundsException e) { echoErr("MUST BE BETWEEN 1 AND 7!"); }
            }
            echo(plant.getSp() + ". " + plant.getDescription());
            echo("Is this what you want? [Y/n]");
            if (yes()) again = false;
        }
        echo("Your " + plant.getSp() + " needs a name, which will be?");
        plant.setName(read());

        return plant;
    }

}
