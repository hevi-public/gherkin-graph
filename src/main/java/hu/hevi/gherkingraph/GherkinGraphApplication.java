package hu.hevi.gherkingraph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GherkinGraphApplication {

	public static void main(String[] args) {
		GtoJCore.asdf();
		SpringApplication.run(GherkinGraphApplication.class, args);
	}
}

