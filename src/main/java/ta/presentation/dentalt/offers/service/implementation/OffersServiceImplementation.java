package ta.presentation.dentalt.offers.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.service.implementation.AppointmentServiceImplementation;
import ta.presentation.dentalt.category.repository.CategoryRepository;
import ta.presentation.dentalt.offers.model.dto.OffersDTO;
import ta.presentation.dentalt.offers.model.dto.OffersNewDateDTO;
import ta.presentation.dentalt.offers.repository.OffersRepository;
import ta.presentation.dentalt.offers.service.services.OffersService;
import ta.presentation.dentalt.operation.repository.OperationRepository;

import java.util.List;

@Service
public class OffersServiceImplementation implements OffersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImplementation.class);

    @Autowired
    private OffersRepository offersRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public OffersDTO getOffer(Integer id) {
        return null;
    }

    @Override
    public List<OffersDTO> getAllOffers() {
        return null;
    }

    @Override
    public OffersDTO createOffer(OffersDTO offerDTO) {
        return null;
    }

    @Override
    public OffersDTO changeOffer(OffersDTO offerDTO) {
        return null;
    }

    @Override
    public OffersDTO changeDate(Integer id, OffersNewDateDTO newDate) {
        return null;
    }

    @Override
    public OffersDTO changeOfferPrice(Integer id, Double price) {
        return null;
    }

    @Override
    public Integer deleteOffer(Integer id) {
        return null;
    }

    @Override
    public OffersDTO restoreOffer(Integer id) {
        return null;
    }
}
