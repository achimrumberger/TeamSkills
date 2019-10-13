package beone.demo.teamknowledge;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TeamknowledgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamknowledgeApplication.class, args);
	}

	
	@Bean
    ApplicationRunner init(TeamMemberRepository repository) {
        return args -> {
           
                TeamMember teamMember = new TeamMember();
                teamMember.setName("Achim");
                teamMember.setSkill("JAVA");
                teamMember.setSkillLevel(1);
                repository.save(teamMember);
                TeamMember teamMember1 = new TeamMember();
                teamMember1.setName("Eva");
                teamMember1.setSkill("JAVA");
                teamMember1.setSkillLevel(2);
                repository.save(teamMember1);
            
            repository.findAll().forEach(System.out::println);
        };
	}
}
