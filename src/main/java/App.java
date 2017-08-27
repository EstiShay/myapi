import static spark.Spark.*;
import com.google.gson.Gson;
import exceptions.ApiException;

import dao.Sql2oAttorneyDao;
import dao.Sql2oCaseHousingDao;
import dao.Sql2oClientDao;
import dao.Sql2oLegalCaseDao;

import models.Attorney;
import models.CaseHousing;
import models.Client;
import models.LegalCase;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;


public class App {

    public static void main(String[] args) {

        Sql2oLegalCaseDao legalCaseDao;
        Sql2oCaseHousingDao caseHousingDao;
        Sql2oAttorneyDao attorneyDao;
        Sql2oClientDao clientDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/legalaidclinic.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";

        Sql2o sql2o = new Sql2o(connectionString, "", "");
        legalCaseDao = new Sql2oLegalCaseDao(sql2o);
        caseHousingDao = new Sql2oCaseHousingDao(sql2o);
        attorneyDao = new Sql2oAttorneyDao(sql2o);
        clientDao = new Sql2oClientDao(sql2o);
        conn = sql2o.open();

        //create
            post("/clients/new", "application/json", (req, res) ->{
            Client client = gson.fromJson(req.body(), Client.class);
            clientDao.add(client);
            res.status(201);
            return gson.toJson(client);
        });

        post("/attorneys/new", "application/json", (req, res) ->{
            Attorney attorney = gson.fromJson(req.body(), Attorney.class);
            attorneyDao.add(attorney);
            res.status(201);
            return gson.toJson(attorney);
        });

        post("/legalcases/new", "application/json", (req, res) ->{
            LegalCase legalCase = gson.fromJson(req.body(), LegalCase.class);
            legalCaseDao.add(legalCase);
            res.status(201);
            return gson.toJson(legalCase);
        });

        post("/caseshousing/new", "application/json", (req, res) ->{
            CaseHousing caseHousing = gson.fromJson(req.body(), CaseHousing.class);
            caseHousingDao.add(caseHousing);
            res.status(201);
            return gson.toJson(caseHousing);
        });

        //read
        get("/clients", "application/json", (req, res) ->{
            return gson.toJson(clientDao.getAll());
        });
        get("/attorneys", "application/json", (req, res) ->{
            return gson.toJson(attorneyDao.getAll());
        });
        get("/legalcases", "application/json", (req, res) ->{
            return gson.toJson(legalCaseDao.getAll());
        });
        get("/caseshousing", "application/json", (req, res) ->{
            return gson.toJson(caseHousingDao.getAll());
        });

        get("/clients/:id", "application/json", (req, res) -> {
            int clientId = Integer.parseInt(req.params("id"));
            Client clientToFind = clientDao.findById(clientId);

            if(clientToFind == null) {
                throw new ApiException(404, String.format("There is no client in our records with the id \'%s\'", req.params("id")));
            }
            return null;
        });

        get("/attorneys/:id", "application/json", (req, res) -> {
            int attorneyId = Integer.parseInt(req.params("id"));
            Attorney attorneyToFind = attorneyDao.findById(attorneyId);

            if(attorneyToFind == null) {
                throw new ApiException(404, String.format("There is no attorney in our records with the id \'%s\'", req.params("id")));
            }
            return null;
        });

        get("/legalcases/:id", "application/json", (req, res) -> {
            int legalCaseId = Integer.parseInt(req.params("id"));
            LegalCase caseToFind = legalCaseDao.findById(legalCaseId);

            if(caseToFind == null) {
                throw new ApiException(404, String.format("There is no case in our records with the id \'%s\'", req.params("id")));
            }
            return null;
        });

        exception(ApiException.class, (exc, req, res) ->{
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });

        //filter
        after((req, res) -> {
            res.type("application/json");
        });

    }
}


