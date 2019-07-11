package vacation.work.multimedia.Repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vacation.work.multimedia.Domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String>, JpaSpecificationExecutor {
    public List<User> findByUsername(String username);
    public List<User> findByUsernameAndAndPassword(String username,String password);
    public List<User> findByCode(String code);
}
