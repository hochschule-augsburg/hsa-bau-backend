package de.hsaugsburg;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public void sendMail() {
        System.out.println("Erinnerungsmail wurde versendet");
    }

}
