package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Airplane;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.AirplaneRepository;

import java.util.List;

@Service
public class AirplaneMgr {

    @Autowired
    private AirlineMgr airlineMgr;

    @Autowired
    private AirplaneRepository airplaneRepository;

    public void addAirplane(Airplane a) throws EntityAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el avion. Checkear que el role este correcto

        if (airplaneRepository.findByLicensePlate(a.getLicensePlate()) != null) {
            throw new EntityAlreadyExists();
        }
        airplaneRepository.save(a);
    }

    public List<Airplane> getAirplanesByAirlineIATA(String IATA) {
        Airline a = airlineMgr.findAirlineByIATA(IATA);
        return airplaneRepository.findAllByAirline(a);
    }
}
