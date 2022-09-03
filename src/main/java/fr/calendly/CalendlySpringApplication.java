package fr.calendly;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.calendly.utils.Utils;

@SpringBootApplication
public class CalendlySpringApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CalendlySpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
	

}
