package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.User;
import um.edu.uy.business.exceptions.UserAlreadyExists;
import um.edu.uy.persistence.PassengerRepository;
import um.edu.uy.persistence.UserRepository;

@Service
public class PassengerMgr {

    @Autowired
    private PassengerRepository passengerRepository;

    public void addPassenger(Passenger p) throws UserAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el usuario. Checkear que el role este correcto

        if(passengerRepository.findByDocument(p.document) != null) {
            throw new UserAlreadyExists();
        }

        passengerRepository.save(p);
    }
}
