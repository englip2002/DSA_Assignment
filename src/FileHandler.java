
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Thong So Xue
 */
public class FileHandler<T> {
    private String filename;
    
    FileHandler(String filename) {
        this.filename = filename;
    }

    public void write(SetInterface<MenuItem> menuItemSet) {
        try {
            File file = new File(filename);
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(menuItemSet);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Cannot save to file");
            ex.printStackTrace();
        }
    }

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
    
}
