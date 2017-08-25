package dao;

import models.CaseHousing;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oCaseHousingDao implements CaseHousingDao {

    private final Sql2o sql2o;
    public Sql2oCaseHousingDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(CaseHousing caseHousing) {
        String sql = "INSERT INTO caseshousing (clientId, typeOfDispute, opposingParty, clientTenant, caseIsEviction) VALUES (:clientId, :typeOfDispute, :opposingParty, :clientTenant, :caseIsEviction)";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(caseHousing)
                    .executeUpdate()
                    .getKey();
            caseHousing.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public CaseHousing findById(int id) {
        String sql = "SELECT * FROM caseshousing WHERE id = :id";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(CaseHousing.class);
        }
    }

    @Override
    public List<CaseHousing> getAll() {
        String sql = "SELECT * FROM caseshousing";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(CaseHousing.class);
        }
    }

    @Override
    public void update(int clientId, String typeOfDispute, String opposingParty, boolean clientTenant, boolean caseIsEviction, int id) {
        String sql = "UPDATE caseshousing SET (clientId, typeOfDispute, opposingParty, clientTenant, caseIsEviction) = (:clientId, :typeOfDispute, :opposingParty, :clientTenant, :caseIsEviction) WHERE id = :id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("clientId", clientId)
                    .addParameter("typeOfDispute", typeOfDispute)
                    .addParameter("opposingParty", opposingParty)
                    .addParameter("clientTenant", clientTenant)
                    .addParameter("caseIsEviction", caseIsEviction)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM caseshousing WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
