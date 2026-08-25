package dev.meetuco.homey_be;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.meetuco.homey_be.Category.CategoryEntity;
import dev.meetuco.homey_be.Category.CategoryRepository;

@SpringBootApplication
public class HomeyBeApplication {
	private static final String DEFAULT_CATEGORY_NAME = "Default";
	private static final String DEFAULT_CATEGORY_COLOR = "#ffffff";

	public static void main(String[] args) {
		SpringApplication.run(HomeyBeApplication.class, args);
	}

	@Bean
	public CommandLineRunner seedDefaultCategory(CategoryRepository categoryRepository){

		return args -> {
			if (!categoryRepository.existsByNameIgnoreCase(DEFAULT_CATEGORY_NAME)) {
				CategoryEntity defaultCategory = new CategoryEntity();
				defaultCategory.setName(DEFAULT_CATEGORY_NAME);
				defaultCategory.setColor(DEFAULT_CATEGORY_COLOR);
				categoryRepository.save(defaultCategory);
			}
		};
	}
}
