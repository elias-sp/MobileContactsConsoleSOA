package gr.aueb.cf.mobilecontacts.model;

public abstract class AbstractEntity {
private long id;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
