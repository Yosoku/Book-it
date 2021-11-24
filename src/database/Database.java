package database;

import java.io.*;

public abstract class Database implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;


    protected String filename;

    public Database(String filename) {
        this.filename = filename;
    }


    public void write() {
        try {
            File temp = new File(this.filename);
            temp.createNewFile(); // create file if not present
            FileOutputStream fileOut = new FileOutputStream(this.filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //check
    public Object read() {
        Object obj = null;
        try {
            File temp = new File(this.filename);
            temp.createNewFile(); // create file if not present
            FileInputStream fileIn = new FileInputStream(this.filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            obj = objectIn.readObject();
            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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

