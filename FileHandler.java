package planty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Handles save files.
 *
 * @author Nereare
 * @version 1.0.00
 */
public class FileHandler {

    /**
     * The program's save file extension.
     */
    public static final String ext = "psav";

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

    /**
     * Runs the <code>read()</code> method and converts the input to <code>int</code> format.
     *
     * @return  the user input as a integer number.
     */
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

    /**
     * Prints something to the default error <code>PrintStream</code>.
     *
     * @param o the thing to print.
     */
    private static void echoErr(Object o) {
        System.err.println(o);
    }
    // </editor-fold>

    /**
     * Saves the given plant to a file.
     *
     * @param plant the plant whose data is to be saved as a <code>Sprouling</code> object.
     * @return      <code>true</code> if file was successfully saved, <code>false</code> otherwise.
     */
    public static boolean save(Sprouling plant) {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

        echo("Choose where to save your game.");
        if ( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
            try {
                BufferedWriter writer = new BufferedWriter( new FileWriter( chooser.getSelectedFile().getPath() + "/" + plant.getName() + "." + FileHandler.ext ) );

                writer.write("VIRTUAL_PLANT_PLANT_SAVE_FILE"); writer.newLine();
                //
                writer.write( plant.getName() ); writer.newLine();
                writer.write( plant.getSp() ); writer.newLine();
                writer.write( plant.getDescription() ); writer.newLine();
                //
                writer.write( String.valueOf(plant.getAge()) ); writer.newLine();
                writer.write( String.valueOf(plant.getHealth()) ); writer.newLine();
                //
                writer.write( String.valueOf(plant.getMinFruit()) ); writer.newLine();
                writer.write( String.valueOf(plant.getMaxFruit()) ); writer.newLine();
                writer.write( String.valueOf(plant.getFruitTime()) ); writer.newLine();
                //
                writer.write( String.valueOf(plant.getMaxAge()) );

                writer.close();
                echo("File wrote.");
                return true;
            }
            catch (IOException e) {
                echoErr("File could not be write.");
                return false;
            }
        }
        else { echoErr("Error on choosing path."); return false; }
    }

    /**
     * Loads a plant from a file.
     *
     * @return  the plant as a <code>Sprouling</code> object.
     */
    public static Sprouling load() {
        JFileChooser chooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Virtual Plant save file", "psav");
        chooser.setFileFilter(filter);
        Sprouling returnable;

        echo("From a file, then. Select it.");
        if ( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
            try {
                BufferedReader reader = new BufferedReader( new FileReader( chooser.getSelectedFile().getPath() ) );
                if ( reader.readLine().equals("VIRTUAL_PLANT_PLANT_SAVE_FILE") ) {
                    returnable = new planty.Sprouling(reader.readLine(), reader.readLine(), reader.readLine(), Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
                }
                else { echoErr("Not a suitable file."); returnable = null; }
                reader.close();
            }
            catch (IOException e) {
                echoErr("File could not be read.");
                returnable = null;
            }
        }
        else { echoErr("Error on choosing file path."); returnable = null; }
        return returnable;
    }

}