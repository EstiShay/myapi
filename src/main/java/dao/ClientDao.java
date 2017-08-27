package dao;

import models.Attorney;
import models.Client;
import models.LegalCase;

import java.util.List;

public interface ClientDao {

    //create
    void add(Client client);
    void addCaseToClient(LegalCase legalCase, Client client);

    //read
    Client findById(int id);
    List<Client> getAll();
    List<LegalCase> findAllCasesForAClient();
    List<Attorney> findAllAttorneysOfAClient();

    //update
    void update(String name, String contact, boolean financialNeedVerified, int id);

    //destroy
    void deleteById(int id);
}
