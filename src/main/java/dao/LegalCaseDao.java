package dao;

import models.Attorney;
import models.LegalCase;

import java.util.List;

public interface LegalCaseDao {
    //only those methods which users may need to execute on an object without knowing to which child class it belongs

    //create
    void add(LegalCase legalCase);

    //read
    List<LegalCase> getAll();
    LegalCase findById(int id);
    List<LegalCase> getAllCasesForAClient(int clientId);
    List<Attorney> getAllAttorneysOnACase(int id);

    //update
    //void update(int clientId, boolean caseOpen, int id);

    //destroy
    void deleteById(int id);
}
