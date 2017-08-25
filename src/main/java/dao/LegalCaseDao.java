package dao;

import models.LegalCase;

import java.util.List;

public interface LegalCaseDao {
    //only those methods which users may need to execute on an object without knowing to which child class it belongs

    //create
    void add(LegalCase legalCase);
    void addClientAndAttorneyToACase(int caseId, int attorneyId, int clientId);

    //read
    List<LegalCase> getAll();
    LegalCase findById(int id);

    //update
    //void update(int clientId, boolean caseOpen, int id);

    //destroy
    void deleteById(int id);
}
