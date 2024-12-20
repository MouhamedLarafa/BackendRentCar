package de.tekup.locationappb.repositories;

import de.tekup.locationappb.entites.Role;
import de.tekup.locationappb.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    List<User> findUserByRole(Role role);


}
