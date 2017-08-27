package dao;

import models.Attorney;
import models.CaseHousing;
import models.Client;
import models.LegalCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oLegalCaseDaoTest {

    private Connection conn;
    private Sql2oLegalCaseDao legalCaseDao;
    private Sql2oCaseHousingDao caseHousingDao;
    private Sql2oAttorneyDao attorneyDao;
    private Sql2oClientDao clientDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        legalCaseDao = new Sql2oLegalCaseDao(sql2o);
        caseHousingDao = new Sql2oCaseHousingDao(sql2o);
        attorneyDao = new Sql2oAttorneyDao(sql2o);
        clientDao = new Sql2oClientDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

//    @Test
//    public void CaseInstantiatesCorrectly() throws Exception{
//        LegalCase newCase = setUpCase();
//        assertTrue(newCase instanceof LegalCase);
//    }

//    @Test
//    public void getAllCases() throws Exception{
//        LegalCase newCase = setUpCase();
//        LegalCase secondCase = setUpCase2();
//        CaseHousing thirdCase = setUpHCase();
//        legalCaseDao.add(newCase);
//        legalCaseDao.add(secondCase);
//        caseHousingDao.add(thirdCase);
//        assertEquals(3, legalCaseDao.getAll().size());
//    }
//
//    @Test
//    public void findById() throws Exception{
//        LegalCase newCase = setUpCase();
//        LegalCase secondCase = setUpCase2();
//        legalCaseDao.add(newCase);
//        legalCaseDao.add(secondCase);
//        LegalCase foundCase = legalCaseDao.findById(1);
//        assertEquals(newCase.getClientId(), foundCase.getClientId());
//    }
//
//    @Test
//    public void deleteById() throws Exception{
//        LegalCase newCase = setUpCase();
//        LegalCase secondCase = setUpCase2();
//        legalCaseDao.add(newCase);
//        legalCaseDao.add(secondCase);
//        legalCaseDao.deleteById(1);
//        assertEquals(1, legalCaseDao.getAll().size());
//        assertEquals(2, legalCaseDao.findById(2).getClientId());
//    }
//
//    @Test
//    public void createCaseUsingClient(){
//        Client newClient = setUpClient();
//        clientDao.add(newClient);
//        Client foundClient = clientDao.findById(1); //Ideally here I'd use a findByName or somesuch.
//        int clientId = foundClient.getId();
//        LegalCase newCase = new LegalCase(clientId);
//        legalCaseDao.add(newCase);
//        LegalCase foundCase = legalCaseDao.findById(1);
//        assertEquals(newClient.getId(), foundCase.getClientId());
//    }
//
//    //helper
//    public LegalCase setUpCase(){
//        return new LegalCase(1);
//    }
//    public LegalCase setUpCase2(){
//        return new LegalCase(2);
//    }
//
//    public Attorney attyHelper(){
//        return new Attorney("Jane Doe", "036013");
//    }
//    public Attorney secondHelper(){
//        return new Attorney("Jack Doe", "008712");
//    }
//
//    public CaseHousing setUpHCase(){
//        return new CaseHousing(2, "Mortgage", "Sunnyvale Bank", false, false);
//    }
//
//    public Client setUpClient(){
//        return new Client("Michael Eddington", "555-555-1234, meddington@maquis.com", false);
//    }

}