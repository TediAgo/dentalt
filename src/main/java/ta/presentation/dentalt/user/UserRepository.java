package ta.presentation.dentalt.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ta.presentation.dentalt.user.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


}
