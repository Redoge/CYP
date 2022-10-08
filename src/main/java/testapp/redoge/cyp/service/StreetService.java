package testapp.redoge.cyp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testapp.redoge.cyp.repository.StreetRepository;

@Service
public class StreetService {
    @Autowired
    StreetRepository streetRepository;
}
