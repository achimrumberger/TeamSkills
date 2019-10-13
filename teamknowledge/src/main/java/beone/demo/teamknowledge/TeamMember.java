package beone.demo.teamknowledge;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TeamMember {
	
	@Id @GeneratedValue
    private Long id;
    private String name;
    private String skill;
    private int skillLevel;
    
    public TeamMember() {
    	
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

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public int getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}
    
	public String toString() {
		return String.format(id + "; " + name  + "; " + skill+ "; " + skillLevel); 
	}
	
    

}
