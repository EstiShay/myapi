package dao;

import models.Attorney;
import models.Client;
import models.LegalCase;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oClientDao implements ClientDao{

    private final Sql2o sql2o;
    public Sql2oClientDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void add(Client client) {
        String sql = "INSERT INTO clients (name, contact, financialNeedVerified) VALUES (:name, :contact, :financialNeedVerified)";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(client)
                    .executeUpdate()
                    .getKey();
            client.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }


    @Override
    public void addCaseToClient(LegalCase legalCase, Client client){
        String sql = "INSERT INTO cases_clients (caseid, clientid) VALUES (:caseid, :clientid)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("caseid", legalCase.getId())
                    .addParameter("clientid", client.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public Client findById(int id) {
        String sql = "SELECT * FROM clients WHERE id = :id";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Client.class);
        }
    }

    @Override
    public List<Client> getAll() {
        String sql = "SELECT * FROM clients";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Client.class);
        }
    }


    @Override
    public List<Attorney> findAllAttorneysOfAClient() {
        return null;
    }

    @Override
    public List<LegalCase> findAllCasesForAClient() {
        return null;
    }

    @Override
    public void update(String name, String contact, boolean financialNeedVerified, int id) {
        String sql = "UPDATE clients SET (name, contact, financialNeedVerified) = (:name, :contact, :financialNeedVerified) WHERE id = :id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("contact", contact)
                    .addParameter("financialNeedVerified", financialNeedVerified)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM clients WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
