package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.User;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.business.exceptions.InvalidInformation;
import um.edu.uy.persistence.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserMgr {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user)
            throws InvalidInformation, EntityAlreadyExists {

        if (user.getName() == null || "".equals(user.getName()) || user.getAddress() == null || "".equals(user.getAddress())) {
            //hacer algo
            throw new InvalidInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (userRepository.findByDocument(user.getDocument()) != null) {

            throw new EntityAlreadyExists();
        }

        userRepository.save(user);
    }

    public void loginUser(User user)
            throws InvalidInformation {

        if (user.getName() == null || "".equals(user.getName()) || user.getAddress() == null || "".equals(user.getAddress())) {
            //hacer algo
            throw new InvalidInformation("Alguno de los datos ingresados no es correcto");

        }


        if (findByDocument(user.getDocument()) != null) {

        }

        userRepository.save(user);

    }

    public boolean checkIfUserNameExists(String mail) {
        Optional<User> userOptional = userRepository.findByMail(mail);
        return userOptional.isPresent();
    }

    public boolean checkAdminLogIn(String mail, String password) {
        if (!checkIfUserNameExists(mail)) {
            return false;
        }
        Optional<User> uOptional = userRepository.findByMail(mail);
        if(uOptional.isPresent()) {
            if(uOptional.get().getPassword().equals(password) && uOptional.get().getRole().equals("administrator")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public List<User> getAllUsers()
    {
        List<User> lista = new ArrayList<>();
        userRepository.findAll().forEach(user -> lista.add(user));
        return lista;
    }
    public User findByDocument(Long document) {
        return userRepository.findByDocument(document);
    }

}
