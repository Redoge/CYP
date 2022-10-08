package testapp.redoge.cyp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testapp.redoge.cyp.repository.AddressRepository;

@Service
public class AdressService {
    @Autowired
    AddressRepository adressRepository;
}
