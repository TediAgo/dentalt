package ta.presentation.dentalt.category.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.service.implementation.AppointmentServiceImplementation;
import ta.presentation.dentalt.category.repository.CategoryRepository;
import ta.presentation.dentalt.category.service.services.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImplementation.class);

    @Autowired
    private CategoryRepository categoryRepository;



}
