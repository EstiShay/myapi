package dao;

import models.LegalCase;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oLegalCaseDao implements LegalCaseDao{

    private final Sql2o sql2o;
    public Sql2oLegalCaseDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(LegalCase legalCase){
        String sql = "INSERT INTO legalcases (clientId, createdAt, caseOpen) VALUES (:clientId, :createdAt, :caseOpen)";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(legalCase)
                    .executeUpdate()
                    .getKey();
            legalCase.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addClientAndAttorneyToACase(int caseId, int attorneyId, int clientId) {

    }

    @Override
    public List<LegalCase> getAll() {
        String sql = "SELECT * FROM legalcases";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(LegalCase.class);
        }
    }

    @Override
    public LegalCase findById(int id){
        String sql = "SELECT * FROM legalcases WHERE id = :id";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(LegalCase.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM legalcases WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
