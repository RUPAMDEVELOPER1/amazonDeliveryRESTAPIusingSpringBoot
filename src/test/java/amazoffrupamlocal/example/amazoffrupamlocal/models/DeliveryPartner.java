package amazoffrupamlocal.example.amazoffrupamlocal.models;

public class DeliveryPartner {
    private String id;
    private int numberOfOrder;

    public DeliveryPartner(String id) {
        this.id = id;
        numberOfOrder = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumberOfOrder() {
        return numberOfOrder;
    }

    public void setNumberOfOrder(int numberOfOrder) {
        this.numberOfOrder = numberOfOrder;
    }
}
