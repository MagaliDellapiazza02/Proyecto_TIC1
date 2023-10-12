package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.User;
import um.edu.uy.business.exceptions.UserAlreadyExists;
import um.edu.uy.business.exceptions.InvalidUserInformation;
import um.edu.uy.persistence.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMgr {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) throws UserAlreadyExists{
        //verificar en el front que los datos sean de tipo correcto antes de crear el usuario. Checkear que el role este correcto

        if(findByDocument(user.document) != null) {
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


        if (findByDocument(user.document) != null) {

        }

        userRepository.save(user);

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
