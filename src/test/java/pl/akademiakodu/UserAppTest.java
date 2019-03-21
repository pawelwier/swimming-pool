package pl.akademiakodu;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserAppTest {

    private PasswordEncoder passwordEncoder;

    @Test
    public void getHash() {
        passwordEncoder = new BCryptPasswordEncoder();
        String passA = passwordEncoder.encode("admin");
        String passU = passwordEncoder.encode("user");
        System.out.println("admin " + passA);
        System.out.println("user " + passU);
    }

}
