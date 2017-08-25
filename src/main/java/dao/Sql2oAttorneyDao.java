package dao;

import models.Attorney;
import models.LegalCase;
import models.Client;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oAttorneyDao implements AttorneyDao{

    private final Sql2o sql2o;
    public Sql2oAttorneyDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Attorney attorney) {
        String sql = "INSERT INTO attorneys (name, barId) VALUES (:name, :barId)";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(attorney)
                    .executeUpdate()
                    .getKey();
            attorney.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Attorney findById(int id) {
        String sql = "SELECT * FROM attorneys WHERE id = :id";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Attorney.class);
        }
    }

    @Override
    public List<Attorney> getAll() {
        String sql = "SELECT * FROM attorneys";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Attorney.class);
        }
    }

    @Override
    public List<LegalCase> getAllCasesForAnAttorney(int attorneyId) {
        return null;
    }

    @Override
    public List<Client> getAllClientsOfAnAttorney(int attorneyId) {
        return null;
    }

    @Override
    public void update(String name, String barId, int id) {
        String sql = "UPDATE attorneys SET (name, barId) = (:name, :barId) WHERE id = :id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("barId", barId)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM attorneys WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
