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
    private AirlineRepository airlineRepository;

    @Autowired
    private UserRepository userRepository;

    public void addAirline(Airline airline)
            throws InvalidInformation, EntityAlreadyExists {

        if (airline.getAlnName()== null || "".equals(airline.getAlnCountry()) || airline.getAlnIATA() == null || "".equals(airline.getAlnICAO())) {
            //hacer algo
            throw new InvalidInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si no existe

        if (airlineRepository.findOneByAlnIATA(airline.alnIATA) != null) {

            throw new EntityAlreadyExists();
        }

        airlineRepository.save(airline);

        //Asignarle un trabajador a la aerolinea, admin de aerolinea

        // password: iata de aerolinea, mail: nombre de la aerolinea
        User adminAirline = new User(airline.alnIATA,airline.alnName); //
        adminAirline.setRole("adminAirline");
        adminAirline.setCompany(airline.alnName);
        userRepository.save(adminAirline);
        // document = ? , restriccion not null en la tabla

    }
}