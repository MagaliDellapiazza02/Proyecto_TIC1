package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.User;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.business.exceptions.InvalidInformation;
import um.edu.uy.persistence.PassengerRepository;
import um.edu.uy.persistence.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserMgr {

    @Autowired
    private UserRepository userRepository;


    private PassengerRepository passengerRepository;

    public void addUser(User user) throws EntityAlreadyExists {
        if (checkIfUserMailExists(user.getMail()) || checkIfUserNameExists(user.getMail())) {
            throw new EntityAlreadyExists();
        } else {
            userRepository.save(user);
        }
    }

    public boolean checkIfUserMailExists(String mail) {
        Optional<User> userOptional = userRepository.findByMail(mail);
        return userOptional.isPresent();
    }


    public boolean deleteUser(long doc) {
        User u = userRepository.findByDocument(doc);
        if (u != null){
            userRepository.delete(u);
            return true;
        }
        return false;
    }

    public String getNameByMail(String mail) {
        User u = userRepository.findUserByMail(mail);
        if(u != null) {
            return u.getName();
        }
        return "None";
    }

    public String getCompanyByMail(String mail) {
        User u = userRepository.findUserByMail(mail);
        if(u != null) {
            return u.getCompany();
        }
        return "None";
    }

    public String getRoleByMail(String mail) {
        User u = userRepository.findUserByMail(mail);
        if(u != null) {
            return u.getRole();
        }
        return "None";
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

    public String checkWorkerLogIn(String mail, String password) {
        if (!checkIfUserNameExists(mail)) {
            return "No User";
        }
        Optional<User> uOptional = userRepository.findByMail(mail);
        if(uOptional.isPresent()) {
            if(uOptional.get().getPassword().equals(password)) {
                if (uOptional.get().getCompany().equals("Administrador Sistema")) {
                    return "Admin System";
                }else if (uOptional.get().getCompany().split("$")[0].equals("Aeropuerto")) {
                    if (uOptional.get().getRole().equals("administrador")) {
                        return "Admin Airport";
                    } else {
                        return "Worker Airport";
                    }
                } else {
                    String airline = uOptional.get().getCompany();
                    if (uOptional.get().getRole().equals("administrador")) {
                        return "Admin " + airline;
                    } else {
                        return "Worker " + airline;
                    }
                }
            }
        }
        return "No User";
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
