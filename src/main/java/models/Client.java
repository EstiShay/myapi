package models;

public class Client {

    private int id;
    private String name;
    private String contact;
    private boolean financialNeedVerified;

    public Client(String name, String contact, boolean financialNeedVerified){
        this.name = name;
        this.contact = contact;
        this.financialNeedVerified = financialNeedVerified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isFinancialNeedVerified() {
        return financialNeedVerified;
    }

    public void setFinancialNeedVerified(boolean financialNeedVerified) {
        this.financialNeedVerified = financialNeedVerified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (id != client.id) return false;
        if (financialNeedVerified != client.financialNeedVerified) return false;
        if (!name.equals(client.name)) return false;
        return contact.equals(client.contact);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + contact.hashCode();
        result = 31 * result + (financialNeedVerified ? 1 : 0);
        return result;
    }
}
