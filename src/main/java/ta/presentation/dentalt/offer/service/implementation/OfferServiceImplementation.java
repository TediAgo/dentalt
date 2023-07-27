package ta.presentation.dentalt.offer.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.service.implementation.AppointmentServiceImplementation;
import ta.presentation.dentalt.category.model.dto.CategoryDTO;
import ta.presentation.dentalt.category.repository.CategoryRepository;
import ta.presentation.dentalt.offer.model.dto.OfferDTO;
import ta.presentation.dentalt.offer.model.dto.OfferNewDateDTO;
import ta.presentation.dentalt.offer.model.entity.OfferEntity;
import ta.presentation.dentalt.offer.repository.OfferRepository;
import ta.presentation.dentalt.offer.service.mapper.OfferConverter;
import ta.presentation.dentalt.offer.service.services.OfferService;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;
import ta.presentation.dentalt.operation.repository.OperationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImplementation implements OfferService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImplementation.class);

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public OfferDTO getOffer(Integer id) {
        if(offerRepository.findById(id).isPresent() && offerRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            return OfferConverter.convertOfferEntityToDTO(offerRepository.findById(id).get());
        }
        LOGGER.info("Offer does not exist.");
        return new OfferDTO();
    }

    @Override
    public List<OfferDTO> getAllOffers() {
        return offerRepository.findAll()
                .stream()
                .filter(offer -> offer.getValidity().equals(Boolean.TRUE) && offer.getFinish().isAfter(LocalDate.now()))
                .map(OfferConverter::convertOfferEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OfferDTO createOffer(OfferDTO offerDTO) {
        if(offerDTO.getOperations() == null || offerDTO.getOperations().isEmpty()) {
            LOGGER.info("Operations not found.");
            return new OfferDTO();
        }
        if(offerDTO.getCategories() == null || offerDTO.getCategories().isEmpty()) {
            LOGGER.info("Operations not found.");
            return new OfferDTO();
        }

        OfferEntity offer = new OfferEntity();
        offer.setName(offer.getName());
        offer.setBegin(offerDTO.getBegin());
        offer.setFinish(offerDTO.getFinish());
        offer.setPrice(offerDTO.getPrice());

        for (OperationDTO operationDTO: offerDTO.getOperations()) {
            offer.getOperations().addAll(
                    operationRepository.findAll()
                    .stream()
                    .filter(operation -> operation.getId().equals(operationDTO.getId())
                                                && operation.getValidity().equals(Boolean.TRUE))
                            .collect(Collectors.toList()));
        }
        for (CategoryDTO categoryDTO: offerDTO.getCategories()) {
            offer.getCategories().addAll(
                    categoryRepository.findAll()
                            .stream()
                            .filter(category -> category.getId().equals(categoryDTO.getId())
                                    && category.getValidity().equals(Boolean.TRUE))
                            .collect(Collectors.toList()));
        }

        return OfferConverter.convertOfferEntityToDTO(offer);
    }

    @Override
    public OfferDTO changeOffer(Integer id, OfferDTO offerDTO) {
        if(offerRepository.findById(id).isPresent() && offerRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            OfferEntity offerToChange = offerRepository.findById(id).get();

            offerRepository.save(offerToChange);
            return OfferConverter.convertOfferEntityToDTO(offerToChange);
        }
        return new OfferDTO();
    }

    @Override
    public OfferDTO changeName(Integer id, String name) {
        return null;
    }


    @Override
    public OfferDTO changeDate(Integer id, OfferNewDateDTO newDate) {
        if(offerRepository.findById(id).isPresent() && offerRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            OfferEntity offerToChange = offerRepository.findById(id).get();
            offerToChange.setBegin(newDate.getBegin());
            offerToChange.setFinish(newDate.getFinish());
            offerRepository.save(offerToChange);
            return OfferConverter.convertOfferEntityToDTO(offerToChange);
        }
        return new OfferDTO();
    }

    @Override
    public OfferDTO changePrice(Integer id, Double price) {
        return null;
    }

    @Override
    public OfferDTO addOperation(Integer id, OperationDTO operationDTO) {
        return null;
    }

    @Override
    public OfferDTO removeOperation(Integer offerId, Integer operationId) {
        return null;
    }

    @Override
    public OfferDTO addCategory(Integer id, CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public OfferDTO removeCategory(Integer offerId, Integer categoryId) {
        return null;
    }

    @Override
    public Integer deleteOffer(Integer id) {
        if(offerRepository.findById(id).isPresent() && offerRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            OfferEntity offerToChange = offerRepository.findById(id).get();
            offerToChange.setValidity(Boolean.FALSE);
            offerRepository.save(offerToChange);
            return id;
        }
        return null;
    }

    @Override
    public OfferDTO restoreOffer(Integer id) {
        if(offerRepository.findById(id).isPresent() && offerRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            OfferEntity offerToChange = offerRepository.findById(id).get();
            offerToChange.setValidity(Boolean.TRUE);
            offerRepository.save(offerToChange);
            return OfferConverter.convertOfferEntityToDTO(offerToChange);
        }
        return new OfferDTO();
    }
}
