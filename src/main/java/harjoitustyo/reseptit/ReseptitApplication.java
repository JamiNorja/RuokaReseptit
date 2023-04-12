package harjoitustyo.reseptit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import harjoitustyo.reseptit.domain.AppUser;
import harjoitustyo.reseptit.domain.AppUserRepository;
import harjoitustyo.reseptit.domain.Difficulty;
import harjoitustyo.reseptit.domain.DifficultyRepository;
import harjoitustyo.reseptit.domain.Recipe;
import harjoitustyo.reseptit.domain.RecipeRepository;
import harjoitustyo.reseptit.domain.Type;
import harjoitustyo.reseptit.domain.TypeRepository;

@SpringBootApplication
public class ReseptitApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ReseptitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReseptitApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner harjoitustyo(RecipeRepository recipeRepository, TypeRepository typeRepository, DifficultyRepository difficultyRepository , AppUserRepository urepository) {
		return (args) -> {
			
			log.info("create types");
			typeRepository.save(new Type("Aamiainen"));
			typeRepository.save(new Type("Lounas"));
			typeRepository.save(new Type("Päivällinen"));
			typeRepository.save(new Type("Iltapala"));
			typeRepository.save(new Type("Leivonnainen"));
			typeRepository.save(new Type("Jälkiruoka"));
			
			log.info("create difficulties");
			difficultyRepository.save(new Difficulty("Helppo"));
			difficultyRepository.save(new Difficulty("Keskivaikea"));
			difficultyRepository.save(new Difficulty("Vaikea"));
			
			log.info("create recipes"); // String name, String difficulty, String guide, int ranking, Type type
			recipeRepository.save(new Recipe("Munakas", difficultyRepository.findByName("Helppo").get(0), "Kaksi munaa ja mausteita.", 4, typeRepository.findByName("Aamiainen").get(0)));
			recipeRepository.save(new Recipe("Burrito", difficultyRepository.findByName("Vaikea").get(0), "Jauhelihaa, tortilla, majoneesi, kurkku, tomaatti ja salaatti.", 5, typeRepository.findByName("Päivällinen").get(0)));
			recipeRepository.save(new Recipe("Suklaahippukeksi", difficultyRepository.findByName("Keskivaikea").get(0), "Voita, sokeria, fariinisokeria, vanilijasokeria, kananmuna, vehnäjauhoja, ruokasooda, suola, suklaalevy, suklaahippuja", 4, typeRepository.findByName("Leivonnainen").get(0)));
			
			AppUser user1 = new AppUser("user", "$2a$10$JokuQqVqYScOZ28NvRcqwOcCMxwFuQJ8DRSald/3ODN9hkBGa0p.O", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$83q6U7uFRHKg3G43nl4TmOdrxKEUJ8l.lDLtKkdV.M8Q/FGNuxXOS", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all recipes");
			for (Recipe recipe : recipeRepository.findAll()) {
				log.info(recipe.toString());
			}
			
		};
	}

}
