package um.edu.uy.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Retorna un usuario por documento si encuentra mas de una lanza una excepcion
     * @param document
     * @return
     */
    User findByDocument(long document);

    Optional<User> findByMail(String mail);


    boolean existsByIdAndName(Long id, String name);

    @Query(value = "select count(*) from users where name=?", nativeQuery = true)
    int contarUsuariosPorNombre(String name);

}
