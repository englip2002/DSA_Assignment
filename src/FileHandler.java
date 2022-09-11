import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Utility class used to support file handling operations, including reading and
 * writing Java Serializable objects. Each filehandler instance can only handle
 * one file, thus multiple files are needed if there is a need to store multiple
 * objects.
 * 
 * @author Thong So Xue
 */
public class FileHandler<T> {
    /**
     * The filename of this filehandler instance
     */
    private String filename;

    /**
     * Creates a filehandler instance binded to the given filename.
     * 
     * @param filename the name of the file that this filehandler will be reading
     *                 data from or writing data into
     */
    public FileHandler(String filename) {
        this.filename = filename;
    }

    /**
     * Writes the given Java Serializable object into the binded filename. Prints
     * the error when it encounters one.
     * 
     * @param object the Java Serializable object to be written into the file.
     */
    public void write(T object) {
        try {
            File file = new File(filename);
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(object);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Cannot save to file");
            ex.printStackTrace();
        }
    }

    /**
     * Reads data from the binded filename. Prints error when it encounters one.
     * 
     * @return the data that was read from the binded file.
     */
    @SuppressWarnings("unchecked")
    public T read() {
        T output;
        try {
            File file = new File(filename);
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            output = (T) (oiStream.readObject());
            oiStream.close();
            return output;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Cannot read from file");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Writes the given Java Serializable object into the binded filename. Allows
     * user to provide a boolean value to determine whether to print errors when
     * it encounters one.
     * 
     * @param object     the Java Serializable object to be written into the file.
     * @param printError determines whether to print errors when it encounters one.
     */
    public void write(T object, boolean printError) {
        try {
            File file = new File(filename);
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(object);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            if (printError) {
                System.out.println("File not found");
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            if (printError) {
                System.out.println("Cannot save to file");
                ex.printStackTrace();
            }
        }
    }

    /**
     * Reads data from the binded filename. Allows user to provide a boolean value
     * to determine whether to print errors when it encounters one.
     * 
     * @param printError determines whether to print errors when it encounters one. 
     * @return the data that was read from the binded file.
     */
    @SuppressWarnings("unchecked")
    public T read(boolean printError) {
        T output;
        try {
            File file = new File(filename);
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            output = (T) (oiStream.readObject());
            oiStream.close();
            return output;
        } catch (FileNotFoundException ex) {
            if (printError) {
                System.out.println("File not found");
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            if (printError) {
                System.out.println("Cannot read from file");
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            if (printError) {
                System.out.println("Class not found");
                ex.printStackTrace();
            }
        }
        return null;

    }

    public boolean fileExists() {
        return new File(filename).isFile();
    }

}