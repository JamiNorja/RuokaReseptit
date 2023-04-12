package harjoitustyo.reseptit.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name, guide;
	private int ranking;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "typeid")
	private Type type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "difficultyid")
	private Difficulty difficulty;

	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recipe(String name, Difficulty difficulty, String guide, int ranking, Type type) {
		super();
		this.name = name;
		this.difficulty = difficulty;
		this.guide = guide;
		this.ranking = ranking;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", difficulty=" + this.getDifficulty() + ", guide=" + guide + ", ranking="
				+ ranking + ", type=" + this.getType() + "]";
	}
	
	

}
