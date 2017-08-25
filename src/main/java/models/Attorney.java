package models;

public class Attorney {

    private int id;
    private String name;
    private String barId;

    public Attorney(String name, String barId){
        this.name = name;
        this.barId = barId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarId() {
        return barId;
    }

    public void setBarId(String barId) {
        this.barId = barId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attorney attorney = (Attorney) o;

        if (id != attorney.id) return false;
        if (!name.equals(attorney.name)) return false;
        return barId.equals(attorney.barId);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + barId.hashCode();
        return result;
    }
}
