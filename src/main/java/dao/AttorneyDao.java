package dao;

import models.Attorney;
import models.LegalCase;
import models.Client;

import java.util.List;

public interface AttorneyDao {

    //create
    void add(Attorney attorney);

    //read
    Attorney findById(int id);
    List<Attorney> getAll();
    List<LegalCase> getAllCasesForAnAttorney(int attorneyId);
    List<Client> getAllClientsOfAnAttorney(int attorneyId);

    //update
    void update(String name, String barId, int id);

    //destroy
    void deleteById(int id);
}
