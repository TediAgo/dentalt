package ta.presentation.dentalt.offer.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.service.implementation.AppointmentServiceImplementation;
import ta.presentation.dentalt.category.repository.CategoryRepository;
import ta.presentation.dentalt.category.service.mapper.CategoryConverter;
import ta.presentation.dentalt.offer.model.dto.OfferDTO;
import ta.presentation.dentalt.offer.model.dto.OfferNewDateDTO;
import ta.presentation.dentalt.offer.model.entity.OfferEntity;
import ta.presentation.dentalt.offer.repository.OfferRepository;
import ta.presentation.dentalt.offer.service.mapper.OfferConverter;
import ta.presentation.dentalt.offer.service.services.OfferService;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;
import ta.presentation.dentalt.operation.repository.OperationRepository;
import ta.presentation.dentalt.operation.service.mapper.OperationConverter;

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
                .filter(offer -> offer.getValidity().equals(Boolean.TRUE))
                .map(OfferConverter::convertOfferEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OfferDTO createOffer(OfferDTO offerDTO) {
        OfferEntity offer = new OfferEntity();

        offer.setName(offer.getName());
        offer.setBegin(offerDTO.getBegin());
        offer.setFinish(offerDTO.getFinish());
        offer.setPrice(offerDTO.getPrice());
        if(offerDTO.getOperations() == null || offerDTO.getOperations().isEmpty()) {
            LOGGER.info("No operations selected.");
            return new OfferDTO();
        }
        if(!categoryRepository.findById(offerDTO.getCategoryDTO().getId()).isPresent()
                || !categoryRepository.findById(offerDTO.getCategoryDTO().getId()).get().getValidity().equals(Boolean.TRUE) ) {
            LOGGER.info("Category does not exist.");
            return new OfferDTO();
        }
        for (OperationDTO operationDTO: offerDTO.getOperations()) {
            offer.getOperations().addAll(
                    operationRepository.findAll()
                    .stream()
                    .filter(operationEntity -> operationEntity.getId().equals(operationDTO.getId())
                                                && operationEntity.getValidity().equals(Boolean.TRUE))
                            .collect(Collectors.toList()));
        }
        offer.setCategory(categoryRepository.findById(offerDTO.getCategoryDTO().getId()).get());

        return OfferConverter.convertOfferEntityToDTO(offer);
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
    public OfferDTO changeOfferPrice(Integer id, Double price) {
        if(offerRepository.findById(id).isPresent() && offerRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            OfferEntity offerToChange = offerRepository.findById(id).get();
            offerToChange.setPrice(price);
            offerRepository.save(offerToChange);
            return OfferConverter.convertOfferEntityToDTO(offerToChange);
        }
        return new OfferDTO();
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
