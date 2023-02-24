package amazoffrupamlocal.example.amazoffrupamlocal.Controller;


import amazoffrupamlocal.example.amazoffrupamlocal.Services.OrderService;
import amazoffrupamlocal.example.amazoffrupamlocal.models.DeliveryPartner;
import amazoffrupamlocal.example.amazoffrupamlocal.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    //1
    @PostMapping("/add-order")
    public ResponseEntity<String > addOrder(@RequestBody Order order){
        String response = orderService.addNewOrder(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
  //2
    @PostMapping("/add-Partner/{Id}")
    public ResponseEntity<String> addPartner(@PathVariable String Id){
        String response = orderService.addNewDeliveryPartner(Id);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    //3
    @PutMapping("/add-order-partner-pair")
    public ResponseEntity<String> addOrderPartnerPair(@RequestParam String orderId, @RequestParam String partnerId){
        String response = orderService.addNewOrderPartnerPair(orderId,partnerId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
   //5
    @GetMapping("/get-partner-by-id/{partnerId}")
    public ResponseEntity getPartnerById(@PathVariable String partnerId){
        DeliveryPartner response = orderService.partnerById(partnerId);
        if(response==null){
            return new ResponseEntity<>("given order id not found",HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }

    }
    //4
    @GetMapping("/orders/get-order-by-id/{orderId} ")
    public ResponseEntity getOrderById(@PathVariable String orderId){
        Order response = orderService.OrderById(orderId);
        if(response==null){
            return new ResponseEntity<>("given order id not found",HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }
    //6
    }
    @GetMapping("/orders/get-order-count-by-partner-id/{partnerId}")
    public ResponseEntity getCountOrder(@PathVariable String partnerId){
        int response = orderService.countOrders(partnerId);
        if(response==0){
            return new ResponseEntity<>("given partner id not found",HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.FOUND);
        }
    }

    @GetMapping("/orders/get-orders-by-partner-id/{partnerId}")
        public ResponseEntity getOrdersByPartnerId(@PathVariable String partnerId){
        List<Order> response = orderService.getOrderObjectListBypartnerId(partnerId);
            if(response==null){
                return new ResponseEntity("given partner id :-"+partnerId+" is not present in database",HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity(response, HttpStatus.FOUND);
            }

    }
    @GetMapping("orders/get-all-orders")
    public ResponseEntity getAllOrders(){
        List<Order> response = orderService.getAllOrderList();
        return new ResponseEntity(response,HttpStatus.FOUND);
    }
    @GetMapping("/orders/get-count-of-unassigned-orders")
    public ResponseEntity getCountOfUnassignedOrders(){
        int response = orderService.getUnassignedCountOfOrder();
        return new ResponseEntity(response,HttpStatus.FOUND);
    }
    @GetMapping("/orders/get-count-of-orders-left-after-given-time/{time}/{partnerId}")
    public ResponseEntity getOrdersLeftAfterGivenTimeByPartnerId(@PathVariable String time, @PathVariable String partnerId ){
        int response = orderService.unDelivered(time, partnerId);
        if(response==0){
            return new ResponseEntity("partnerIdnotfound",HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity(response,HttpStatus.FOUND);
        }

    }
    @DeleteMapping("/orders/delete-partner-by-id/{partnerId}")
    public ResponseEntity deletePartnerById(@PathVariable String partnerId){
        String response = orderService.deletePartner(partnerId);
        return new ResponseEntity<>(response,HttpStatus.FOUND);

    }
    @DeleteMapping("/orders/delete-order-by-id/{orderId}")
    public ResponseEntity deleteOrderById(@PathVariable String orderId){
        String response = orderService.deleteOrder(orderId);
        return new ResponseEntity(response,HttpStatus.FOUND);

    }


    //7


}
