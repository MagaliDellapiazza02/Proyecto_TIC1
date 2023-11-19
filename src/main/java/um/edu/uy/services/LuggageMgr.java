package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.Luggage;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.persistence.FlightRepository;
import um.edu.uy.persistence.LuggageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LuggageMgr {

    @Autowired
    private LuggageRepository luggageRepository;

    public boolean addLuggage(Passenger p, Flight flight, int weight, String trackingCode) {

        if (luggageRepository.findByTrackingCode(trackingCode) != null) {
            return false;
        }
        int weightLeft = flight.getLuggagesLeft() - weight;
        if(weightLeft < 0) {
            return false;
        }
        Luggage luggage = new Luggage(p,flight,weightLeft,trackingCode);

        luggageRepository.save(luggage);
        return true;
    }

    public List<Luggage> getLuggagesFromPassenger(Passenger p) {
        return luggageRepository.findAllByPassenger(p);
    }

    public List<Luggage> getLuggagesFromFlight(Flight f) {
        List<Luggage> valijas = luggageRepository.findAllByFlight(f);
        List<Luggage> noEntregadas = new ArrayList<>();
        for (int i = 0; i < valijas.size(); i++){
            if (!valijas.get(i).getState().equals("Entregada")) {
                noEntregadas.add(valijas.get(i));
            }
        }
        return noEntregadas;
    }

    public void luggageDelivered(Luggage l){
        l.setState("Entregada");
        luggageRepository.save(l);
    }
}
