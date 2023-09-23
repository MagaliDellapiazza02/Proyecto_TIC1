package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.User;
import um.edu.uy.business.exceptions.UserAlreadyExists;
import um.edu.uy.business.exceptions.InvalidUserInformation;
import um.edu.uy.persistence.UserRepository;

@Service
public class UserMgr {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user)
            throws InvalidUserInformation, UserAlreadyExists {

        if (user.getName() == null || "".equals(user.getName()) || user.getAddress() == null || "".equals(user.getAddress())) {
            //hacer algo
            throw new InvalidUserInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (userRepository.findOneByDocument(user.getDocument()) != null) {

            throw new UserAlreadyExists();
        }

        userRepository.save(user);

    }

    public void loginUser(User user)
            throws InvalidUserInformation {

        if (user.getName() == null || "".equals(user.getName()) || user.getAddress() == null || "".equals(user.getAddress())) {
            //hacer algo
            throw new InvalidUserInformation("Alguno de los datos ingresados no es correcto");

        }


        if (userRepository.findOneByDocument(user.getDocument()) != null) {

        }

        userRepository.save(user);

    }

    public User findUserByDocument(Long document) {
        return userRepository.findOneByDocument(document);
    }

}
