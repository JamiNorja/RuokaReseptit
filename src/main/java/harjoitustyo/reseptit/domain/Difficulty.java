package harjoitustyo.reseptit.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Difficulty {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long difficultyid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "difficulty")
	@JsonIgnore
	private List<Recipe> recipe;

	public Difficulty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Difficulty(String name) {
		super();
		this.name = name;
	}

	public Long getDifficultyid() {
		return difficultyid;
	}

	public void setDifficultyid(Long difficultyid) {
		this.difficultyid = difficultyid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Recipe> getRecipe() {
		return recipe;
	}

	public void setRecipe(List<Recipe> recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "Difficulty [difficultyid=" + difficultyid + ", name=" + name + "]";
	}
	
	

}
