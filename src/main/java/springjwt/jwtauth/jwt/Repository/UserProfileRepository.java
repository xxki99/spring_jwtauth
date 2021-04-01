package springjwt.jwtauth.jwt.Repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import springjwt.jwtauth.jwt.Entity.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

    Set<UserProfile> findAll();

    Optional<UserProfile> findByUsername(String username);
}
