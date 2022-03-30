package com.xeniabuscarons.restapirockets;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "rockets")
public class Rocket {
    @Id
    private String rocketId = UUID.randomUUID().toString();
    private String code;

    @OneToMany(mappedBy = "rocket")
    private List<Propellant> propellants = new ArrayList<>();


    public Rocket(String code) throws Exception {
        checkCode(code);
        this.code = code;

    }
    public Rocket(){

    }

    private void checkCode(String code) throws Exception {
        if (!code.matches("[A-Z0-9]{8}")) {
            throw new Exception("codi erroni!");
        }
    }

    public String getCode() {
        return code;
    }

    public Propellant addPropellant(Propellant propellant) throws Exception {
        propellant.setRocket(this);
        this.propellants.add(propellant);
        return propellant;
    }

    public List<Propellant> getPropellants() {
        return propellants;
    }

    public void increasePower()  {
        for (Propellant propellant : propellants) {
            propellant.increasePower();
        }
    }
    public void decreasePower()  {
        for (Propellant propellant : propellants) {
            propellant.decreasePower();
        }
    }


    public int getCurrentRocketPower() {
        int result = 0;
        for (Propellant propellant : propellants) {
            result += propellant.getCurrentPower();
        }
        return result;
    }

    public String printPropellantsPower() {
        String result = "";
        int i = 1;
        for (Propellant propellant : propellants) {
            result += "Propulsor " + i + " : màxima potencia " + propellant.getMaximumPower() + " \n";
            i++;
        }
        return result;
    }

    public String getRocketId() {
        return rocketId;
    }

    public void setCode(String code) throws Exception {
        checkCode(code);
        this.code = code;
    }
}
