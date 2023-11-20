package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Airplane;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.AirplaneRepository;
@Service
public class AirplaneMgr {

    private AirplaneRepository airplaneRepository;

    public void addAirplane(Airplane a) throws EntityAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el avion. Checkear que el role este correcto

        if (airplaneRepository.findByLicensePlate(a.getLicensePlate()) != null) {
            throw new EntityAlreadyExists();
        }
        airplaneRepository.save(a);
    }

    public void deleteAirplane(Airplane a) {
    	airplaneRepository.delete(a);
    }
}
