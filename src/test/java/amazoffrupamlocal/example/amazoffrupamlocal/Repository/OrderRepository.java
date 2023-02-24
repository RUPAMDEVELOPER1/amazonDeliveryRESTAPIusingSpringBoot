package amazoffrupamlocal.example.amazoffrupamlocal.Repository;

import amazoffrupamlocal.example.amazoffrupamlocal.models.DeliveryPartner;
import amazoffrupamlocal.example.amazoffrupamlocal.models.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    HashMap<String , DeliveryPartner> deliverpartnerdb = new HashMap<>();
    HashMap<String, Order> orderdb = new HashMap<>();
    HashMap<String ,String > deliverparterorderdb = new HashMap<>();

    HashMap<String,List<Order>> orderswithpartnerdb = new HashMap<>();

    public String createOrder(Order order){
        if(orderdb.containsKey(order.getId())){
            return "order id is already exist";

        }
        else{
            orderdb.put(order.getId(),order);
            return "New order added successfully";
        }
    }
    public String createDeliveryParrtner(String Id){
        if(deliverparterorderdb.containsKey(Id)){
            return "delivery partner is already exist";
        }
        else {
            DeliveryPartner deliveryPartner = new DeliveryPartner(Id);
            deliverpartnerdb.put(Id,deliveryPartner);
            return "deliver partner is added";
        }
    }
    public String createOrderpartnerPair(String orderid, String partnerid){
        if(orderswithpartnerdb.containsKey(partnerid)){
            orderswithpartnerdb.put(partnerid,Arrays.asList(orderdb.get(orderid)));
        }
        if(deliverparterorderdb.containsKey(orderid) && deliverparterorderdb.get(orderid)!= null){
            return "for this order id deliver partner is already assigned";
        }
        else{
            deliverparterorderdb.put(orderid,partnerid);
            return "Partner is assigned sucessfully for the order id"+orderid;
        }
    }
    public Order getOrder(String orderid){
        if(orderdb.containsKey(orderid)){
            return orderdb.get(orderid);
        }
        else {
            return null;
        }
    }
    public DeliveryPartner getPartner(String partnerId){
         if(deliverpartnerdb.containsKey(partnerId)){
             return deliverpartnerdb.get(partnerId);
         }
         else {
             return null;
         }
    }
    public int numberOfOrder(String partnerId){
        if(deliverpartnerdb.containsKey(partnerId)){
            return deliverpartnerdb.get(partnerId).getNumberOfOrder();
        }
        else{
            return 0;
        }
    }
    public List<Order> listoforderforPatrnetId(String partnerId){
        if(orderswithpartnerdb.containsKey(partnerId)){
            return orderswithpartnerdb.get(partnerId);
        }
        else {
            return null;
        }

    }
    public List<Order> listOfAllOrder(){
        List<Order> list = new ArrayList<>();
        for(String key:orderdb.keySet()){
            list.add(orderdb.get(key));
        }
        return list;
    }
    public int countOfUnassignedOrders(){
        int count = 0;
        for( String x :orderswithpartnerdb.keySet()){
            if(orderswithpartnerdb.get(x)==null){
                count++;
            }
        }
        return count;
    }
    public int ordersLeftAfterGivenTimeByPartnerId(String time,String partnerId) {
        int count = 0;
        if(deliverparterorderdb.containsKey(partnerId)) {
            for (String x : deliverparterorderdb.keySet()) {
                if (orderdb.get(x).getDeliveryTime() > Integer.parseInt(time)) {
                    count++;
                }
            }

            return count;
        }
        else{
            return 0;
        }
    }
    public String deletePartnerById(String partnerId){
        if(deliverpartnerdb.containsKey(partnerId)){
            orderswithpartnerdb.remove(partnerId);
            for (String x : deliverparterorderdb.keySet()) {
                if (deliverparterorderdb.get(x)==partnerId){
                    deliverparterorderdb.put(x,null);
                }
                }

            deliverpartnerdb.remove(partnerId);
            return "sucessfully delete partner id and made all order null for corresponding partnerid";

        }
        else{
            return "partner id is not there in data base";
        }
    }
    public String deleteOrder(String orderId){
        if(orderdb.containsKey(orderId)){

               deliverparterorderdb.put(orderId,null);
               orderdb.remove(orderId);
               return "Sucessfully deleted";

        }
        else{
            return "Order is not exist in database";
        }
    }



}
