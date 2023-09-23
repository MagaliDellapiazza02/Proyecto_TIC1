package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Retorna un usuario por documento si encuentra mas de una lanza una excepcion
     * @param document
     * @return
     */
    User findOneByDocument(long document);

}
