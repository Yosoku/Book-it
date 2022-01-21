package backend.database;

import java.io.*;

/**
 * <p>
 * An abstract Database base class for handling high level memory and files inside an Application</p>
 * <p>
 * Each class that inherits from Database has information about its filename,the file that the information is being stored.
 * It provides a mutator and an accessor method for filename as well as a Constructor which initializes the filename.
 * </p>
 * <p>
 * The Database also provides 2 methods for reading/writing to a file(filename) using Serializing and Object Streams
 * </p>
 * <p>Use this class as a blueprint to create different types of Databases in an Application using different types of
 * Java collections as a member field and query methods</p>
 *
 * @author Edward Koulakidis
 * @see java.io.Serializable
 * @see ObjectInputStream
 * @see ObjectOutputStream
 * @see CredentialsUserDB
 */

public abstract class Database implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * The filename used to read/write Database in files
     */
    protected String filename;

    /**
     * A basic constructor to initialize the filename. Often used in kid classes in the form of super("file/path/to/store/objects");
     *
     * @param filename The path from Source directory often src/config/filename
     */
    public Database(String filename) {
        this.filename = filename;
    }


    /**
     * The method used for writing Database kids in files using the technique of Serialization and ObjectOutputStream.
     * write() also makes sure the specified filename <b>exists</b> before writing the object. The method doesn't take
     * any parameters as it writes this object in the ObjectOutputStream
     *
     * @see <a href="https://www.tutorialspoint.com/java/java_serialization.htm">Serialization in Java</a>
     */
    public void write() {
        try {
            File temp = new File(this.filename);
            temp.createNewFile(); // create file if not present
            FileOutputStream fileOut = new FileOutputStream(this.filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            fileOut.close();
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The method used for reading Database kids from files using the technique of Serialization and ObjectInputStream.
     * read() also makes sure the specified filename <b>exists</b> before reading the object.
     *
     * @return the <b>Object</b> read from <code>ObjectInputStream</code> and <b>null</b> otherwise
     * @see <a href="https://www.tutorialspoint.com/java/java_serialization.htm">Serialization in Java</a>
     */
    public Object read() {
        Object obj = null;
        try {
            File temp = new File(this.filename);
            temp.createNewFile(); // create file if not present
            FileInputStream fileIn = new FileInputStream(this.filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            obj = objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return obj;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


}

