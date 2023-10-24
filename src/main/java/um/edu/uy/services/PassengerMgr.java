package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.User;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.business.exceptions.EntityNotExists;
import um.edu.uy.persistence.PassengerRepository;
import um.edu.uy.persistence.UserRepository;

import java.util.Optional;

@Service
public class PassengerMgr {

    @Autowired
    private PassengerRepository passengerRepository;

    public void addPassenger(Passenger p) throws EntityAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el usuario. Checkear que el role este correcto

        if(passengerRepository.findByDocument(p.document) != null ||  checkIfUserNameExists(p.mail)) {
            throw new EntityAlreadyExists();
        }

        passengerRepository.save(p);
    }

    public boolean checkIfUserNameExists(String mail) {
        Optional<Passenger> passengerOptional = passengerRepository.findByMail(mail);
        return passengerOptional.isPresent();
    }

    public boolean checkLogIn(String mail, String password) {
        if (!checkIfUserNameExists(mail)) {
            return false;
        }
        Optional<Passenger> pOptional = passengerRepository.findByMail(mail);
        if(pOptional.isPresent()) {
            if(pOptional.get().getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
