package de.hsaugsburg;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  @Autowired
  private TestDelegate testDelegate;

  public static void main(String[] args) {
    SpringApplication.run(Application.class);

    val test = TestObjekt.builder()
            .meineProperty("hallo")
            .build();

  }

}