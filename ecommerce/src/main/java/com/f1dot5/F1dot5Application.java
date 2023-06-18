package com.f1dot5;

import com.f1dot5.data.Article;
import com.f1dot5.data.Customer;
import com.f1dot5.data.repository.ArticleRepository;
import com.f1dot5.data.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class F1dot5Application {

	public static void main(String[] args) {
		SpringApplication.run(F1dot5Application.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(CustomerRepository customerRepo, ArticleRepository articleRepo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				customerRepo.save(new Customer(null, "admin", "admin", null, null));
				articleRepo.save(new Article(1L, null, "Red Bull", "T-Short XL", "/images/rb.png", 1.0F, "USD", 1));
				articleRepo.save(new Article(2L, null, "Mercedes Petronas", "T-Short L", "/images/mp.png", 2.0F, "USD", 2));
				articleRepo.save(new Article(3L, null, "Alpine", "T-Short M", "/images/a.png", 3.0F, "USD", 3));
				articleRepo.save(new Article(4L, null, "Ferrary", "T-Short XL", "/images/f.png", 4.0F, "USD", 4));
				articleRepo.save(new Article(5L, null, "Aston Martin", "T-Short XXL", "/images/am.png", 5.0F, "USD", 5));
				articleRepo.save(new Article(6L, null, "McLaren", "T-Short L", "/images/ml.png", 6.0F, "USD", 6));
				articleRepo.save(new Article(7L, null, "Haas", "T-Short S", "/images/h.png", 7.0F, "USD", 7));
				articleRepo.save(new Article(8L, null, "Alpha Taury", "T-Short M", "/images/at.png", 8.0F, "USD", 8));
				articleRepo.save(new Article(9L, null, "Alpha Romeo", "T-Short M", "/images/ar.png", 9.0F, "USD", 9));
				articleRepo.save(new Article(10L, null, "Williams", "T-Short XS", "/images/w.png", 10.0F, "USD", 10));
			}
		};
	}
}
