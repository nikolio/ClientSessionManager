/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teicm.pm.clientsessionmanager.model;

import gr.teicm.pm.clientsessionmanager.TimeWatch;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author user
 */
public class Client {
    private Integer id;
    private String name;
    private String caseDetails;
    private Integer chargingRate;
    private long elapsedTime;
    private long price;
    private TimeWatch watch;
    Boolean isSessionStarted;
    public long getPrice() {
        return price;
    }
    
    public void startSession() {
        if (!isSessionStarted) {
            this.watch = TimeWatch.start();
        }
        isSessionStarted = true;
    }

    public void endSession() {
        if (isSessionStarted) {
            this.elapsedTime = this.watch.getTime(TimeUnit.SECONDS);
            isSessionStarted = false;
            calculateClientCharge();
        }
    }
    private void calculateClientCharge() {
        price=elapsedTime*chargingRate;
    }
    public long getElapsedTime() {
        return elapsedTime;
    }

    private Client() {
        initializeClientData();
    }

    private void initializeClientData() {
        name = "";
        caseDetails = "";
        chargingRate = 0;
        elapsedTime = 0;
        isSessionStarted = false;
    }

    public Client(String name, String caseDetails, Integer chargingRate, long elapsedTime) {
        this.name = name;
        this.caseDetails = caseDetails;
        this.chargingRate = chargingRate;
        this.elapsedTime = elapsedTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCaseDetails(String caseDetails) {
        this.caseDetails = caseDetails;
    }

    public void setChargingRate(Integer chargingRate) {
        this.chargingRate = chargingRate;
    }

    public String getName() {
        return name;
    }

    public String getCaseDetails() {
        return caseDetails;
    }

    public Integer getChargingRate() {
        return chargingRate;
    }

}
