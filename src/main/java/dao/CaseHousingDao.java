package dao;

import models.CaseHousing;

import java.util.List;

public interface CaseHousingDao {

    //create
    void add(CaseHousing caseHousing);

    //read
    CaseHousing findById(int id);
    List<CaseHousing> getAll();

    //update
    void update(int clientId, String typeOfDispute, String opposingParty, boolean clientTenant, boolean caseIsEviction, int id);

    //destroy
    void deleteById(int id);
}
