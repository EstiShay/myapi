package dao;

import models.CaseHousing;
import models.Client;
import models.LegalCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oClientDaoTest {

    private Connection conn;
    private Sql2oClientDao clientDao;
    private Sql2oLegalCaseDao legalCaseDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        clientDao = new Sql2oClientDao(sql2o);
        legalCaseDao = new Sql2oLegalCaseDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void ClientInstatiatesCorrectly(){
        Client newClient = setUpClient();
        assertTrue(newClient instanceof Client);
    }

    @Test
    public void addingClientSetsId() throws Exception{
        Client newClient = setUpClient();
        int oldId = newClient.getId();
        clientDao.add(newClient);
        Client foundClient = clientDao.findById(1);
        assertNotEquals(oldId, foundClient.getId());
    }

    @Test
    public void getAllClients(){
        Client newClient = setUpClient();
        Client secondClient = setUpClient2();
        clientDao.add(newClient);
        clientDao.add(secondClient);
        assertEquals(2, clientDao.getAll().size());
    }

    @Test
    public void updateClientRecord() throws Exception{
        Client newClient = setUpClient();
        Boolean prevFinancial = newClient.isFinancialNeedVerified();
        clientDao.add(newClient);
        clientDao.update("Michael Eddington", "555-555-1234, meddington@maquis.com", true, 1);
        Client foundClient = clientDao.findById(1);
        assertNotEquals(prevFinancial, foundClient.isFinancialNeedVerified());
    }

    @Test
    public void deleteById() {
        Client newClient = setUpClient();
        Client secondClient = setUpClient2();
        clientDao.add(newClient);
        clientDao.add(secondClient);
        clientDao.deleteById(1);
        assertEquals(1, clientDao.getAll().size());
        assertEquals("Cal Hudson", clientDao.findById(2).getName());
    }

    @Test
    public void addCaseToClient(){
        Client newClient = setUpClient();

    }


    //helper
    public Client setUpClient(){
        return new Client("Michael Eddington", "555-555-1234, meddington@maquis.com", false);
    }
    public Client setUpClient2(){
        return new Client("Cal Hudson", "555-555-9876, chudson@maquis.com", true);
    }

    public CaseHousing setUpCase(){
        return new CaseHousing(1, "Landlord-tenant", "Acme Property Management", true, true);
    }
}