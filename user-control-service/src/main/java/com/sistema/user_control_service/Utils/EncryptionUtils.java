package com.sistema.user_control_service.Utils;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptionUtils {

    // Método para incriptar a senha
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Método para verificar se a senha fornecida corresponde ao hash
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
