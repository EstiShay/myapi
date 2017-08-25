package models;


import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LegalCase {

    private int id;
    private int clientId;
    private Timestamp createdAt;
    private boolean caseOpen;

    public LegalCase(int clientId){
        this.clientId = clientId;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.caseOpen = true;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isCaseOpen() {
        return caseOpen;
    }

    public void setCaseOpen(boolean caseOpen) {
        this.caseOpen = caseOpen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegalCase legalCase = (LegalCase) o;

        if (id != legalCase.id) return false;
        if (clientId != legalCase.clientId) return false;
        if (caseOpen != legalCase.caseOpen) return false;
        return createdAt.equals(legalCase.createdAt);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + clientId;
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + (caseOpen ? 1 : 0);
        return result;
    }
}
