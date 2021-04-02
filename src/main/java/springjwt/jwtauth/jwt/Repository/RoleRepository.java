package springjwt.jwtauth.jwt.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springjwt.jwtauth.jwt.Entity.Role;
import springjwt.jwtauth.jwt.Model.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Optional<Role> findByName(ERole name);
    
}
