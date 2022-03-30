package com.xeniabuscarons.restapirockets;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RocketService {
    private RocketRepository rocketRepository;
    private PropellantRepository propellantRepository;

    public RocketService(RocketRepository rocketRepository, PropellantRepository propellantRepository) {
        this.rocketRepository = rocketRepository;
        this.propellantRepository = propellantRepository;
    }

    public Rocket createRocket(Rocket rocketToCreate) {
        this.rocketRepository.save(rocketToCreate);
        return rocketToCreate;
    }

    public Rocket findRocket(String rocketId) throws Exception {
        return rocketRepository.findById(rocketId).get();
    }

    public Propellant createPropellantsOnRocket(String rocketId, Propellant propellant) throws Exception {
        Rocket rocket = findRocket(rocketId);
        propellant = rocket.addPropellant(propellant);
        propellantRepository.save(propellant);
        return propellant;
    }


    public List<Rocket> getRockets() {
        List<Rocket> rockets = new ArrayList<>();
        for (Rocket r : this.rocketRepository.findAll()) {
            rockets.add(r);
        }
        return rockets;
    }

    public Rocket getRocket(String rocketId) throws Exception {
        return rocketRepository.findById(rocketId).get();
    }

    public List<Propellant> getPropellants(String rocketId) throws Exception {
        Rocket rocket = findRocket(rocketId);
        return rocket.getPropellants();

    }

    public Propellant getPropellant(String rocketId, String propellantId) throws Exception {
        return propellantRepository.findById(propellantId).get();
    }

    public void removeAllRockets() {
        rocketRepository.deleteAll();

    }

    public void removeRocket(String rocketId) {
        rocketRepository.deleteById(rocketId);
    }

    public void removeAllPropellants(String rocketId) throws Exception {
        Rocket rocket = findRocket(rocketId);
        propellantRepository.deleteAllByRocket(rocket);
    }

    public void removePropellant(String rocketId, String propellantId) throws Exception {
        Rocket rocket = findRocket(rocketId);
        propellantRepository.deleteById(propellantId);

    }

    public Rocket updateRocket(String rocketId, Rocket rocketToUpdate) throws Exception {
        Rocket rocket = findRocket(rocketId);
        rocket.setCode(rocketToUpdate.getCode());
        return this.rocketRepository.save(rocket);
    }

    public Propellant updatePropellant(String propellantId, Propellant propellantToUpdate) throws Exception {
        Propellant propellant = propellantRepository.findById(propellantId).get();
        propellant.setMaximumPower(propellantToUpdate.getMaximumPower());
        return this.propellantRepository.save(propellant);
    }

    public Rocket moveRocket(String rocketId,String movement, int speed ) throws Exception {
        Rocket rocket = findRocket(rocketId);

        if(movement.equalsIgnoreCase("speedUpRocket")){
            speedUpRocket(rocket,speed);
        }
       else if(movement.equalsIgnoreCase("slowDownRocket")){
            slowDownRocket(rocket,speed);
        }

        propellantRepository.saveAll(rocket.getPropellants());
        return rocket;
    }

    public void slowDownRocket(Rocket rocket, int speed) {
        for (int i = 0; i < speed; i++) {
            rocket.decreasePower();
        }
    }

    public void speedUpRocket(Rocket rocket, int speed) {
        for (int i = 0; i < speed; i++) {
            rocket.increasePower();
        }
    }


}
