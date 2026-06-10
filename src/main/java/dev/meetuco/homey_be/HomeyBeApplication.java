package dev.meetuco.homey_be;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.meetuco.homey_be.Category.CategoryEntity;
import dev.meetuco.homey_be.Category.CategoryRepository;

@SpringBootApplication
public class HomeyBeApplication {
	private static final String defaultCategoryName = "Default";
	private static final String defaultCategoryColor = "#ffffff";

	public static void main(String[] args) {
		SpringApplication.run(HomeyBeApplication.class, args);
	}

	@Bean
	public CommandLineRunner seedDefaultCategory(CategoryRepository categoryRepository){

		return args -> {
			if (!categoryRepository.existsByNameIgnoreCase(defaultCategoryName)) {
				CategoryEntity defaultCategory = new CategoryEntity();
				defaultCategory.setName(defaultCategoryName);
				defaultCategory.setColor(defaultCategoryColor);
				categoryRepository.save(defaultCategory);
			}
		};
	}
}
