package harjoitustyo.reseptit.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import harjoitustyo.reseptit.domain.AppUser;
import harjoitustyo.reseptit.domain.AppUserRepository;
import harjoitustyo.reseptit.domain.Difficulty;
import harjoitustyo.reseptit.domain.DifficultyRepository;
import harjoitustyo.reseptit.domain.Recipe;
import harjoitustyo.reseptit.domain.RecipeRepository;
import harjoitustyo.reseptit.domain.TypeRepository;
import harjoitustyo.reseptit.domain.Type;

@Controller
public class RestController {
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Autowired
	DifficultyRepository difficultyRepository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	TypeRepository typeRepository;
	
	//Käyttäjät
	@GetMapping("/restUsers")
	public @ResponseBody List<AppUser> showRestUsers() {
		return (List <AppUser>) appUserRepository.findAll();
	}
	
	//Vaikeustasot
	@GetMapping("/difficulties")
	public @ResponseBody List<Difficulty> showRestDifficulties() {
		return (List<Difficulty>) difficultyRepository.findAll();
	}
	
	//Reseptit
	@GetMapping("/recipes")
	public @ResponseBody List<Recipe> showRestRecipes() {
		return (List<Recipe>) recipeRepository.findAll();
	}
	
	//Reseptit id:n perusteella
	@GetMapping("/recipe/{id}")
	public @ResponseBody Optional<Recipe> findRecipeRest(@PathVariable("id") Long id) {
		return recipeRepository.findById(id);
	}
	
	//Tyypit
	@GetMapping("/types")
	public @ResponseBody List<Type> showRestTypes() {
		return (List<Type>) typeRepository.findAll();
	}
}
