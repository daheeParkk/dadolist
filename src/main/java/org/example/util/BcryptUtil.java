package org.example.util;

import org.example.exception.DifferentPasswordException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;


@Component
public class BcryptUtil {

    public String encrypt(String pw) {

        return BCrypt.hashpw(pw, BCrypt.gensalt());

    }

    public void checkPassword(String password, String dbPassword) {

        if (!BCrypt.checkpw(password,dbPassword)){

            throw new DifferentPasswordException("different password");

        }

    }

}
