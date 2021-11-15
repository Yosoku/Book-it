import database.Database;
import database.DatabaseType;

import java.util.HashMap;

public class Application {

    private HashMap<DatabaseType, Database> databases;

    public Application() {
        initDatabases();
        //loadApplication(file/path/with/settings/);

    }

    private void initDatabases() {

    }
}
