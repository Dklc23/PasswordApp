package PassApp;

import java.util.HashMap;
import java.util.Map;

public class Database
{
    private static Map<String,Account> accMap = new HashMap<>();


    public static Map<String, Account> getAccMap() {
        return accMap;
    }
}
