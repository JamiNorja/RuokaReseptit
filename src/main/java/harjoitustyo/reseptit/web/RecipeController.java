package harjoitustyo.reseptit.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import harjoitustyo.reseptit.domain.DifficultyRepository;
import harjoitustyo.reseptit.domain.Recipe;
import harjoitustyo.reseptit.domain.RecipeRepository;
import harjoitustyo.reseptit.domain.TypeRepository;

@Controller
public class RecipeController {
	private static final Logger log = LoggerFactory.getLogger(RecipeController.class);
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private DifficultyRepository difficultyRepository;
	
	@RequestMapping(value = { "/main" } )
	public String showMainPage() {
		return "index";
	}
	
	//CRUD alhaalla
	
	@RequestMapping(value = { "/recipelist", "/" })
	public String showRecipelist(Model model) {
		log.info("get recipes from db");
		model.addAttribute("recipes", recipeRepository.findAll());
		return "recipelist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/addRecipe")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());
		model.addAttribute("difficulties", difficultyRepository.findAll());
		model.addAttribute("types", typeRepository.findAll());
		return "newRecipe";
	}
	
	@PostMapping("saveRecipe")
	public String saveRecipe(@Valid @ModelAttribute ("recipe") Recipe recipe, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("addRecipe", recipe);
			model.addAttribute("difficulties", difficultyRepository.findAll());
			model.addAttribute("types", typeRepository.findAll());
			return "newRecipe";
		}
		recipeRepository.save(recipe);
		return "redirect:/recipelist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("delete/{id}")
	public String deleteRecipe(@PathVariable("id") Long id) {
		recipeRepository.deleteById(id);
		return "redirect:/recipelist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("edit/{id}")
	public String editRecipe(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recipe", recipeRepository.findById(id));
		model.addAttribute("difficulties", difficultyRepository.findAll());
		model.addAttribute("types", typeRepository.findAll());
		return "editRecipe";
	}
	
	//Resepti ohje
	@GetMapping("guide/{id}")
	public String showGuide(@PathVariable("id") Long id, Model model) {
		log.info("showGuide");
		model.addAttribute("recipe", recipeRepository.findById(id));
		return "guide";
	}	
	
	//Rest alhaalla
	
	@GetMapping("/recipes")
	public @ResponseBody List<Recipe> showRestRecipes() {
		log.info("showRestRecipes");
		return (List<Recipe>) recipeRepository.findAll();
	}
	
	@GetMapping("/recipe/{id}")
	public @ResponseBody Optional<Recipe> findRecipeRest(@PathVariable("id") Long id) {
		return recipeRepository.findById(id);
	}

}
