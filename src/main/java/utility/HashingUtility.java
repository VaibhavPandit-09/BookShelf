package utility;

import org.mindrot.jbcrypt.BCrypt;

public class HashingUtility {

    public static String hashPassword(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
        return hashed;
    }
    
    public static boolean checkPassword(String password, String storedHash) {
        if (BCrypt.checkpw(password, storedHash)) {
            return true; // Password matches
        } else {
            return false; // Password does not match
        }
    }
}
