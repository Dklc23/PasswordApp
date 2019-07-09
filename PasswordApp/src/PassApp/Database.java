package PassApp;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Database
{
    private static Map<String,Account> accMap = new HashMap<>();
    public Database() throws FileNotFoundException {
        readAccToMap();
    }

    static void readAccToMap() throws FileNotFoundException {
        Scanner s = new Scanner(new File("Accounts.txt"));


            while (s.hasNextLine()) {

                String user = s.next();
                String Hash = s.next();
                String Salt = s.next();
                System.out.println(user + " " + Hash + " " + Salt);
                Account n = new Account(user, Hash, Salt);
                accMap.put(user, n);
                s.nextLine();
            }


    }



    static Map<String, Account> getAccMap() {
        return accMap;
    }

    static void writeAcc() throws IOException {
        BufferedWriter bf = null;
        try   {
            bf = new BufferedWriter(new FileWriter("Accounts.txt",true));
            for (Map.Entry<String,Account> n: accMap.entrySet()) {
           bf.append(n.getValue().User).append(" ").append(n.getValue().Hash).append(" ").append(String.valueOf(n.getValue().Salt.toString()));
           bf.newLine();
            }

        } catch(Exception e){System.out.println(e);}
        finally {
            bf.close();
        }

}
}
