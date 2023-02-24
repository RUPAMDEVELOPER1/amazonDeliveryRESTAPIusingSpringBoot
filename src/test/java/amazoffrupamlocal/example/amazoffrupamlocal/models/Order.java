package amazoffrupamlocal.example.amazoffrupamlocal.models;

public class Order {

    private String id;

    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        this.id = id;
        String str = new String(deliveryTime);
        int hour= Integer.parseInt(str.substring(0,2));
        int min = Integer.parseInt(str.substring(2,4));
        int ans = hour*60+min;
        this.deliveryTime = ans;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
