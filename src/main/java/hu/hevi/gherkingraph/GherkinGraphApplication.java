package hu.hevi.gherkingraph;

import io.cucumber.messages.Messages;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class GherkinGraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(GherkinGraphApplication.class, args);
	}
}

