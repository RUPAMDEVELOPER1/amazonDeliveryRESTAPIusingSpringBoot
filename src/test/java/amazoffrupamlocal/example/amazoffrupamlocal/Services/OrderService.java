package amazoffrupamlocal.example.amazoffrupamlocal.Services;

import amazoffrupamlocal.example.amazoffrupamlocal.Repository.OrderRepository;
import amazoffrupamlocal.example.amazoffrupamlocal.models.DeliveryPartner;
import amazoffrupamlocal.example.amazoffrupamlocal.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public String addNewOrder(Order order){
        return orderRepository.createOrder(order);
    }
    public String addNewDeliveryPartner(String Id){
        return orderRepository.createDeliveryParrtner(Id);
    }
    public String addNewOrderPartnerPair(String orderid, String partnerid){
        return  orderRepository.createOrderpartnerPair(orderid,partnerid);
    }
    public Order OrderById(String orderId){
        return orderRepository.getOrder(orderId);
    }
    public DeliveryPartner partnerById(String partnerId){
        return orderRepository.getPartner(partnerId);
    }
    public int countOrders(String partnerId){
        return  orderRepository.numberOfOrder(partnerId);
    }
    public List<Order> getOrderObjectListBypartnerId(String partnerId){
        return orderRepository.listoforderforPatrnetId(partnerId);
    }
    public List<Order> getAllOrderList(){
        return orderRepository.listOfAllOrder();
    }
    public int getUnassignedCountOfOrder(){
        return orderRepository.countOfUnassignedOrders();
    }
    public int unDelivered(String time, String partnerId){
        return orderRepository.ordersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }
    public String deletePartner(String partnerId){
        return orderRepository.deletePartnerById(partnerId);
    }
    public String deleteOrder(String orderId){
        return orderRepository.deleteOrder(orderId);
    }
}
