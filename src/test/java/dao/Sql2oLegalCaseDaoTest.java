package dao;

import models.LegalCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oLegalCaseDaoTest {

    private Connection conn;
    private Sql2oLegalCaseDao legalCaseDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        legalCaseDao = new Sql2oLegalCaseDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void CaseInstantiatesCorrectly(){
        LegalCase newCase = setUpCase();
        assertTrue(newCase instanceof LegalCase);

    }

    @Test
    public void getAllCases(){
        LegalCase newCase = setUpCase();
        LegalCase secondCase = setUpCase2();
        legalCaseDao.add(newCase);
        legalCaseDao.add(secondCase);
        assertEquals(2, legalCaseDao.getAll().size());
    }

    @Test
    public void findById(){
        LegalCase newCase = setUpCase();
        LegalCase secondCase = setUpCase2();
        legalCaseDao.add(newCase);
        legalCaseDao.add(secondCase);
        LegalCase foundCase = legalCaseDao.findById(1);
        assertEquals(newCase.getClientId(), foundCase.getClientId());
    }

    @Test
    public void deleteById() {
        LegalCase newCase = setUpCase();
        LegalCase secondCase = setUpCase2();
        legalCaseDao.add(newCase);
        legalCaseDao.add(secondCase);
        legalCaseDao.deleteById(1);
        assertEquals(1, legalCaseDao.getAll().size());
        assertEquals(2, legalCaseDao.findById(2).getClientId());
    }

    //helper
    public LegalCase setUpCase(){
        return new LegalCase(1);
    }
    public LegalCase setUpCase2(){
        return new LegalCase(2);
    }

}