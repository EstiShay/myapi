package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CaseHousing extends LegalCase {

    private String typeOfDispute;
    private String opposingParty;
    private boolean clientTenant;
    private boolean caseIsEviction;

    public CaseHousing(int clientId, String typeOfDispute, String opposingParty, boolean clientTenant, boolean caseIsEviction) {
        //super(clientId);
        this.clientId = clientId;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.caseOpen = true;
        this.typeOfDispute = typeOfDispute;
        this.opposingParty = opposingParty;
        this.clientTenant = clientTenant;
        this.caseIsEviction = caseIsEviction;
    }

    public String getTypeOfDispute() {
        return typeOfDispute;
    }

    public void setTypeOfDispute(String typeOfDispute) {
        this.typeOfDispute = typeOfDispute;
    }

    public String getOpposingParty() {
        return opposingParty;
    }

    public void setOpposingParty(String opposingParty) {
        this.opposingParty = opposingParty;
    }

    public boolean isClientTenant() {
        return clientTenant;
    }

    public void setClientTenant(boolean clientTenant) {
        this.clientTenant = clientTenant;
    }

    public boolean isCaseIsEviction() {
        return caseIsEviction;
    }

    public void setCaseIsEviction(boolean caseIsEviction) {
        this.caseIsEviction = caseIsEviction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CaseHousing that = (CaseHousing) o;

        if (clientTenant != that.clientTenant) return false;
        if (caseIsEviction != that.caseIsEviction) return false;
        if (!typeOfDispute.equals(that.typeOfDispute)) return false;
        return opposingParty.equals(that.opposingParty);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + typeOfDispute.hashCode();
        result = 31 * result + opposingParty.hashCode();
        result = 31 * result + (clientTenant ? 1 : 0);
        result = 31 * result + (caseIsEviction ? 1 : 0);
        return result;
    }
}
