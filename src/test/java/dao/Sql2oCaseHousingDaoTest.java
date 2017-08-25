package dao;

import models.CaseHousing;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oCaseHousingDaoTest {

    private Connection conn;
    private Sql2oCaseHousingDao caseHousingDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        caseHousingDao = new Sql2oCaseHousingDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void HousingInstantiatesCorrectly() throws Exception{
        CaseHousing newHousing = setUpCase();
        assertTrue(newHousing instanceof CaseHousing);
    }

    @Test
    public void findById() throws Exception{
        CaseHousing newCase = setUpCase();
        CaseHousing secondCase = setUpCase2();
        caseHousingDao.add(newCase);
        caseHousingDao.add(secondCase);
        CaseHousing foundCase = caseHousingDao.findById(1);
        assertEquals("Landlord-tenant", foundCase.getTypeOfDispute());
    }

    @Test
    public void addingCaseSetsId() throws Exception {
        CaseHousing newCase = setUpCase();
        int oldId = newCase.getId();
        caseHousingDao.add(newCase);
        CaseHousing foundCase = caseHousingDao.findById(1);
        assertNotEquals(oldId, foundCase.getId());
    }

    @Test
    public void updateCaseRecord() throws Exception {
        CaseHousing newCase = setUpCase();
        caseHousingDao.add(newCase);
        caseHousingDao.update(1, "Landlord-tenant", "Acme Property Management", true, false, 1);
        CaseHousing foundCase = caseHousingDao.findById(1);
        assertEquals(false, foundCase.isCaseIsEviction());
    }

    @Test
    public void deleteById(){
        CaseHousing newCase = setUpCase();
        CaseHousing secondCase = setUpCase2();
        caseHousingDao.add(newCase);
        caseHousingDao.add(secondCase);
        caseHousingDao.deleteById(1);
        CaseHousing foundCase = caseHousingDao.findById(2);
        assertEquals(1, caseHousingDao.getAll().size());
        assertEquals(false, foundCase.isClientTenant());
    }


        //helper
    public CaseHousing setUpCase(){
        return new CaseHousing(1, "Landlord-tenant", "Acme Property Management", true, true);
    }
    public CaseHousing setUpCase2(){
        return new CaseHousing(2, "Mortgage", "Sunnyvale Bank", false, false);
    }
}