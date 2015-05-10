/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.teicm.pm.clientsessionmanager.logic.controllers;

import gr.teicm.pm.clientsessionmanager.logic.dao.HibernateUtil;
import gr.teicm.pm.clientsessionmanager.logic.model.Client;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author user
 */
public class ClientController {

    private DefaultTableModel clientModel;
    private List<Client> clients;
    private final SessionFactory sessionFactory;
    private Session session;

    public ClientController() {
        clients = new ArrayList<>();
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        updateClients();
        // sessionFactory.close();
    }

    public void getClientDetailsFromForm(String clientName, String caseDetails, String chargingRate, long elapsedTime) {
        try {
            saveClient(new Client(clientName, caseDetails, Integer.valueOf(chargingRate), elapsedTime));
        } catch (NullPointerException e) {

        }
    }

    private void initializeClientModel() {
        this.clientModel = (DefaultTableModel) new javax.swing.table.DefaultTableModel(new Object[][]{},
                new String[]{
                    "Client Name", "Charging rate", "Elapsed time", "Price"
                }
        );
    }

    private void updateClients() {
        clients = session.createCriteria(Client.class).list();
        initializeClientModel();
        clients.forEach(c -> {
            clientModel.addRow(new Object[]{c.getName(), Long.toString(c.getChargingRate()), Long.toString(c.getElapsedTime()), Long.toString(c.getElapsedTime() * c.getChargingRate())});
        });
    }

    public void saveClient(Client client) {
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        updateClients();
    }

    public DefaultTableModel getClientsModel() {
        return (clientModel);
    }

    public List<Client> getClients() {
        return (clients);
    }

}
