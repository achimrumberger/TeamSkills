package beone.demo.teamknowledge;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javassist.NotFoundException;

@RestController
public class TeamMemberController {
	
	private TeamMemberRepository repository;
	
	private static List<String> skills = Arrays.asList("JAVA", "C", "JAVASCRPT", "RAILS", "SQL");
	private static List<Integer> skillLevels = Arrays.asList(0,1,2,3,4);
	
	public TeamMemberController(TeamMemberRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/teammembers")
	@CrossOrigin(origins = "http://localhost:4200")
	public Collection<TeamMember> getAllTeamMembers() {
		return repository.findAll();
	}
	
	@GetMapping("/all-skills")
	@CrossOrigin(origins = "http://localhost:4200")
	public Collection<String> getAvailableSkills() {
		return skills;
	}
	
	@GetMapping("/all-skilllevels")
	@CrossOrigin(origins = "http://localhost:4200")
	public Collection<Integer> getAvailableSkillLevels() {
		return skillLevels;
	}
	
	
	@GetMapping("/teammembers/{skillLevel}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Collection<TeamMember> getTeamMemberWithSkillLevel(@PathVariable int skillLevel) throws NotFoundException {
		return  repository.findByAtleastSkillLevel(skillLevel);
		
	}

	@GetMapping("/teammember/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public TeamMember getTeamMemberById(@PathVariable long id) throws NotFoundException {
		Optional<TeamMember> teamMember =  repository.findById(id);
		if(!teamMember.isPresent()) {
			throw new NotFoundException("no teamMember with this id: " + id);
		}

		return teamMember.get();
	}
	
	@PostMapping("/teammember")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> saveTeamMember(@RequestBody TeamMember teamMember) {
		
		TeamMember  savedTeamMember = repository.save(teamMember);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(savedTeamMember.getId()).toUri();

    	return ResponseEntity.created(location).build();
    }
    
    @PutMapping("/teammember/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> updateTeamMember(@RequestBody TeamMember teamMember, @PathVariable long id) throws NotFoundException {
           	
    	Optional<TeamMember> teamMemberFromRepository =  repository.findById(id);
        if(!teamMemberFromRepository.isPresent()) {
        	return ResponseEntity.notFound().build();
        }
        
        teamMember.setId(id);
        repository.save(teamMember);
        
        return ResponseEntity.noContent().build();
    }

}
