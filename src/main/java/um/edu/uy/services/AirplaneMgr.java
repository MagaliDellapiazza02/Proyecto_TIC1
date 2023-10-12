package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Airplane;
import um.edu.uy.business.exceptions.UserAlreadyExists;
import um.edu.uy.persistence.AirplaneRepository;

@Service
public class AirplaneMgr {

    @Autowired
    private AirplaneRepository AirplaneRepository;

    public void addAirplane(Airplane A) throws UserAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el avion. Checkear que el role este correcto

        if(AirplaneRepository.findByLicensePlate(A.getLicensePlate()) != null) {
            throw new UserAlreadyExists();
        }

        AirplaneRepository.save(A);
    }
}
