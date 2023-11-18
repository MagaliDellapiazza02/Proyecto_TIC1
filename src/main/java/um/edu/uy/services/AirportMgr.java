package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.User;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.AirportRepository;
import um.edu.uy.persistence.UserRepository;

@Service
public class AirportMgr {

    @Autowired
    private AirportRepository AirportRepository;

    public void addAirport(Airport a) throws EntityAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el aerpouerto. Checkear que el role este correcto

        if(AirportRepository.findByName(a.getName()) != null) {
            throw new EntityAlreadyExists();
        }

        AirportRepository.save(a);
    }

    public Airport findByName(String name) {
        return AirportRepository.findByName(name);
    }
}
