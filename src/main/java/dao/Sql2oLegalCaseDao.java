package dao;

import models.Attorney;
import models.Client;
import models.LegalCase;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
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

//    @Override
//    public void addClientToACase(LegalCase legalCase, Client client) {
//        String sql = "INSERT INTO cases_clients (caseId, clientId) VALUES (:caseId, :clientId)";
//        try (Connection con = sql2o.open()){
//            con.createQuery(sql)
//                .addParameter("caseId",legalCase.getId())
//                .addParameter("clientId", client.getId())
////                .addParameter("attorneyId", attorneyId)
//                .executeUpdate();
//        } catch (Sql2oException ex) {
//            System.out.println(ex);
//        }
//    }

    @Override
    public List<LegalCase> getAll() {
        //List<LegalCase> allCases = new ArrayList<LegalCase>();
        String sql = "SELECT * FROM legalcases; SELECT * FROM caseshousing";
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
    public List<LegalCase> getAllCasesForAClient(int clientId) {
        String sql = "SELECT * FROM legalcases WHERE clientId = :clientId";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("clientId", clientId)
                    .executeAndFetch(LegalCase.class);
        }
    }

    @Override
    public List<Attorney> getAllAttorneysOnACase(int id) {
        String sql = "SELECT * FROM cases_attorneys WHERE caseId = :caseId";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("caseId", id)
                    .executeAndFetch(Attorney.class);
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
