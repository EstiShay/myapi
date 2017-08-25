package dao;

import models.Attorney;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oAttorneyDaoTest {

    private Connection conn;
    private Sql2oAttorneyDao attorneyDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        attorneyDao = new Sql2oAttorneyDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void AttorneyInstantiatesCorrectly() throws Exception{
        Attorney newAtty = attyHelper();
        assertTrue(newAtty instanceof Attorney);
    }

    @Test
    public void addingAttorneySetsId() throws Exception{
        Attorney newAtty = attyHelper();
        int oldId = newAtty.getId();
        attorneyDao.add(newAtty);
        Attorney foundAtty = attorneyDao.findById(1);
        assertNotEquals(oldId, foundAtty.getId());
    }

    @Test
    public void getAllAttorneys() throws Exception{
        Attorney newAtty = attyHelper();
        Attorney secondAtty = secondHelper();
        attorneyDao.add(newAtty);
        attorneyDao.add(secondAtty);
        assertEquals(2, attorneyDao.getAll().size());
    }

    @Test
    public void updateAttorneyRecord() throws Exception{
        Attorney newAtty = attyHelper();
        String oldName = newAtty.getName();
        attorneyDao.add(newAtty);
        attorneyDao.update("Janice Doe", "110845", 1);
        Attorney foundAtty = attorneyDao.findById(1);
        assertNotEquals(oldName, foundAtty.getName());
    }

    @Test
    public void deleteById() {
        Attorney newAtty = attyHelper();
        Attorney secondAtty = secondHelper();
        attorneyDao.add(newAtty);
        attorneyDao.add(secondAtty);
        attorneyDao.deleteById(1);
        assertEquals(1, attorneyDao.getAll().size());
        assertEquals("Jack Doe", attorneyDao.findById(2).getName());
    }



    //helper
    public Attorney attyHelper(){
        return new Attorney("Jane Doe", "036013");
    }
    public Attorney secondHelper(){
        return new Attorney("Jack Doe", "008712");
    }

}