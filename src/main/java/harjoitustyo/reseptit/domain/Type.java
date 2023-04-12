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
public class Type {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long typeid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
	@JsonIgnore
	private List<Recipe> recipe;

	public Type() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Type(String name) {
		super();
		this.name = name;
	}

	public Long getTypeid() {
		return typeid;
	}

	public void setTypeid(Long typeid) {
		this.typeid = typeid;
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
		return "Type [typeid=" + typeid + ", name=" + name + "]";
	}
	
	

}
