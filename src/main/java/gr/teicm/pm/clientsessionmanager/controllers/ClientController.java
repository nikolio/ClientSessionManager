/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teicm.pm.clientsessionmanager.controllers;

import gr.teicm.pm.clientsessionmanager.model.Client;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ClientController {

    List<Client> clients;

    public ClientController() {
        clients = new ArrayList<>();
    }

    public void getClientDetailsFromForm(String clientName, String caseDetails, String chargingRate, long elapsedTime) {
        try {
            clients.add(new Client(clientName, caseDetails, Integer.valueOf(chargingRate), elapsedTime));
        } catch (NullPointerException e) {

        }
    }

    public List<Client> getClients() {
        return (clients);
    }

}
