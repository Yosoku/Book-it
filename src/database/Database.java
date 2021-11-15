package database;

public abstract class Database {

    protected final DatabaseType type;

    public DatabaseType getType() {
        return type;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    protected String filename;

    public Database(DatabaseType type, String filename) {
        this.filename = filename;
        this.type = type;
    }


    public abstract void write();

    public abstract void read();
}
