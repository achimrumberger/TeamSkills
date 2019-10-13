package beone.demo.teamknowledge;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
	
	Optional<TeamMember> findByName(String name);
	
	List<TeamMember> findBySkillLevel(int sillelevel);
	
	@Query("select t from TeamMember t where t.skillLevel >= ?1")
	List<TeamMember> findByAtleastSkillLevel(int skillLevel);

}
