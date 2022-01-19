package com.example.diploma;

import com.example.diploma.entity.*;
import com.example.diploma.repas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DiplomaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiplomaApplication.class, args);
	}

	@Autowired
	IRolesRep rolesRep;
	@Autowired
	IUserRep userRep;
	@Autowired
	ICategory categoryRep;
	@Autowired
	IProductRep productRep;
	@Autowired
	IProductCategory productCategoryRep;
	@Autowired
	IBrands brandRep;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	IProductRatingRep productRatingRep;
	@Autowired
	INewsRep newsRep;


	@PostConstruct
	private void init() {
		if (categoryRep.count() < 1) {
			Category category1 = new Category("Здоровое питание");
			Category category2 = new Category("Для быта");
			Category category3 = new Category("Украшения");
			Category category4 = new Category("Экотуризм");
			categoryRep.save(category1);
			categoryRep.save(category2);
			categoryRep.save(category3);
			categoryRep.save(category4);

			Brand brand1 = new Brand("Мед", "/img/brands/med.jpeg");
			Brand brand2 = new Brand("Чаи", "/img/brands/Chai.jpeg");
			Brand brand3 = new Brand("Украшения", "/img/brands/Ukrasheniay.jpg");
			Brand brand4 = new Brand("Для быта", "/img/brands/Для быта.jpeg");
			Brand brand5 = new Brand("Экотуризм", "/img/brands/Ekotur.jpeg");
			brandRep.save(brand1);
			brandRep.save(brand2);
			brandRep.save(brand3);
			brandRep.save(brand4);
			brandRep.save(brand5);

			Product product1 = new Product("Мед алтайский горный",
					brand1,
					600,
					10,
					"Мед горный алтайский",
					"Медо добывается в экологическом месте на алтае, боганый мин вещ и мин",
					"/img/product/med.jpeg",
					category1);
			Product product2 = new Product("Иван Чай",
					brand2,
					235,
					10,
					"Иван чай с равнин Урала",
					"СОбирается в экологически чистом районе Урала",
					"/img/product/ivan.jpeg",
					category1);
			Product product3 = new Product("Подвеска золотая",
					brand3,
					335,
					10,
					"прекрасная золотая подвеска",
					"Украшение",
					"/img/product/podveskaZolotaya.jpeg",
					category3);
			Product product4 = new Product("Кирка",
					brand4,
					235,
					10,
					"Кирка из титана",
					"Для быта кирка",
					"/img/product/Kirka.jpeg",
					category2);
			Product product5 = new Product("Поход на урал",
					brand5,
					235,
					10,
					"Поход по заповедным местам",
					"Экотуризм",
					"/img/product/PohodNaUral.jpeg",
					category4);
			ProductRating productRating1 = new ProductRating(1L, 5L);
			productRatingRep.save(productRating1);
			product1.setProductRating(productRating1);
			productRep.save(product1);

			ProductRating productRating2 = new ProductRating(1L, 5L);
			productRatingRep.save(productRating2);
			product2.setProductRating(productRating2);
			productRep.save(product2);

			ProductRating productRating3 = new ProductRating(1L, 5L);
			productRatingRep.save(productRating3);
			product3.setProductRating(productRating3);
			productRep.save(product3);

			ProductRating productRating4 = new ProductRating(1L, 5L);
			productRatingRep.save(productRating4);
			product4.setProductRating(productRating4);
			productRep.save(product4);

			ProductRating productRating5 = new ProductRating(1L, 5L);
			productRatingRep.save(productRating5);
			product5.setProductRating(productRating5);
			productRep.save(product5);

			Roles admin = new Roles("ROLE_ADMIN");
			Roles user = new Roles("ROLE_USER");
			rolesRep.save(admin);
			rolesRep.save(user);

			User users = new User("Aleksandr",
					"Sergeevich",
					"Petyanin",
					"Ekaterinburg",
					"Russia",
					"79193730028",
					"spirit6288@gmail.com",
					"user",
					passwordEncoder.encode("user123")
			);
			User admins = new User("Admin",
					"Admin",
					"Admin",
					"Ekaterinburg",
					"Russia",
					"78887776655",
					"spirit6288@mail.ru",
					"admin",
					passwordEncoder.encode("admin123")
			);
			users.setRole(user);
			admins.setRole(admin);
			userRep.save(users);
			userRep.save(admins);
			//add news
			News news1 = new News("Какие продукты помогают противостоять простуде", "https://здоровое-питание.рф/healthy-nutrition/kakie-produkty-pomogayut-protivostoyat-prostudam-/", "/img/news/news1_.jpeg");
			News news2 = new News("Питание по сезону. Овощи и зелень января",  "https://здоровое-питание.рф/healthy-nutrition/pitanie-po-sezonu-ovoshchi-i-zelen-yanvarya-/", "/img/news/news2.jpeg");
			News news3 = new News("Утренний салат из фруктов и мюсли",  "https://рецепты.здоровое-питание.рф/recipes/utrenniy_salat_iz_fruktov_i_myusli/", "/img/news/news3.jpeg");
			News news4 = new News("Рыба с овощами в конверте",  "https://рецепты.здоровое-питание.рф/recipes/ryba_s_ovoshchami_v_konverte/", "/img/news/news4.jpeg");
			News news5 = new News("Окрошка для диабетиков",  "https://рецепты.здоровое-питание.рф/recipes/okroshka-dlya-diabetikov/", "/img/news/news5.jpeg");
			newsRep.save(news1);
			newsRep.save(news2);
			newsRep.save(news3);
			newsRep.save(news4);
			newsRep.save(news5);
		}
	}

}
