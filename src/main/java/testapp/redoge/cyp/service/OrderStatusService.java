package testapp.redoge.cyp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testapp.redoge.cyp.repository.OrderStatusRepository;

@Service
public class OrderStatusService {
    @Autowired
    OrderStatusRepository orderStatusRepository;
}
