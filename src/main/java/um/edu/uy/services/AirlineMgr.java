package um.edu.uy.services;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.User;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.business.exceptions.InvalidInformation;
import um.edu.uy.persistence.AirlineRepository;
import um.edu.uy.persistence.UserRepository;

@Service
public class AirlineMgr {

    @Autowired
    private UserMgr userMgr;

    @Autowired
    private AirlineRepository airlineRepository;

    public void addAirline(Airline airline) throws EntityAlreadyExists {
        if (airlineRepository.findOneByAlnIATA(airline.getAlnIATA()) != null) {
            throw new EntityAlreadyExists();
        }
        airlineRepository.save(airline);
    }

    public String findAirlineWithUser (String mail) {
        String IATA = userMgr.getCompanyByMail(mail).split("%")[1];
        return IATA;
    }

    public Airline findAirlineByIATA(String IATA) {
        return airlineRepository.findOneByAlnIATA(IATA);
    }
}