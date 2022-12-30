package org.example.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BcryptUtil {

    public String encrypt(String pw) {

        return BCrypt.hashpw(pw, BCrypt.gensalt());

    }

    public Boolean isEquals(String dbpw, String pw) {

        return BCrypt.checkpw(dbpw, pw);

    }

}
