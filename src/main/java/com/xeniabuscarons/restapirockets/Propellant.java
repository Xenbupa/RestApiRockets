package com.xeniabuscarons.restapirockets;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "propellants")
public class Propellant {
    @Id
    private String propellantId = UUID.randomUUID().toString();
    private static final int MAX_INCREMENT_POWER = 10;
    private int maximumPower;
    private int currentPower;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rocket_id")
    private Rocket rocket;

    public Propellant(){

    }

    public Propellant(int maximumPower) throws Exception {
        checkMaximumPower(maximumPower);
        this.maximumPower = maximumPower;
        this.currentPower = 0;

    }

    public void increasePower()  {
        currentPower += MAX_INCREMENT_POWER;
        if (this.currentPower > maximumPower) {
            this.currentPower = maximumPower;
        }
    }

    public void decreasePower()  {
        currentPower -= MAX_INCREMENT_POWER;
        if (this.currentPower < 0) {
            this.currentPower = 0;
        }
    }

    private void checkMaximumPower(int maximumPower) throws Exception {
        if (maximumPower <= 0) throw new Exception("Potencia erronea!");
    }

    public int getMaximumPower() {
        return maximumPower;
    }

    public int getCurrentPower() {
        return currentPower;
    }

    public void setMaximumPower(int maximumPower) throws Exception {
        checkMaximumPower(maximumPower);
        this.maximumPower = maximumPower;
    }

    public void setCurrentPower(int currentPower) {
        this.currentPower = currentPower;
    }

    public String getPropellantId() {
        return propellantId;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }


}
