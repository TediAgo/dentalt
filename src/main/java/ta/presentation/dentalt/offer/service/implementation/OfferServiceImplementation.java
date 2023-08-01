package ta.presentation.dentalt.offer.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.service.implementation.AppointmentServiceImplementation;
import ta.presentation.dentalt.category.repository.CategoryRepository;
import ta.presentation.dentalt.offer.model.dto.OfferDTO;
import ta.presentation.dentalt.offer.model.dto.OfferNewDateDTO;
import ta.presentation.dentalt.offer.model.entity.OfferEntity;
import ta.presentation.dentalt.offer.repository.OfferRepository;
import ta.presentation.dentalt.offer.service.mapper.OfferConverter;
import ta.presentation.dentalt.offer.service.services.OfferService;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;
import ta.presentation.dentalt.offer.service.util.OfferUtil;

import ta.presentation.dentalt.operation.repository.OperationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
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
        LOGGER.info("Offer not found.");
        return new OfferDTO();
    }

    @Override
    public List<OfferDTO> getAllOffers() {
        return offerRepository.findAll()
                .stream()
                .filter(offer -> offer.getFinish().isAfter(LocalDate.now()) && offer.getValidity().equals(Boolean.TRUE))
                .map(OfferConverter::convertOfferEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OfferDTO createOffer(OfferDTO offerDTO) {
        if(offerDTO.getOperations() == null || offerDTO.getOperations().isEmpty()) {
            LOGGER.info("Operations not found.");
            return new OfferDTO();
        }
        if(offerDTO.getCategory() == null) {
            LOGGER.info("Category not found.");
            return new OfferDTO();
        }

        OfferEntity offer = new OfferEntity();
        offer.setName(offerDTO.getName());
        offer.setBegin(offerDTO.getBegin());
        offer.setFinish(offerDTO.getFinish());
        offer.setOperations(new ArrayList<>());
        for (OperationDTO operationDTO: offerDTO.getOperations()) {
            offer.getOperations().addAll(
                    operationRepository.findAll().stream()
                            .filter(operation -> operation.getId().equals(operationDTO.getId()) && operation.getValidity().equals(Boolean.TRUE))
                            .collect(Collectors.toList()));
        }
        offer.setCategory(categoryRepository.findById(offerDTO.getCategory().getId()).get());
        offer.setPrice(OfferUtil.calculateOfferPrice(offer.getOperations(), offer.getCategory()));

        offerRepository.save(offer);
        return OfferConverter.convertOfferEntityToDTO(offer);
    }

    @Override
    public OfferDTO changeName(Integer id, String name) {
        if(offerRepository.findById(id).isPresent() && offerRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            OfferEntity offer = offerRepository.findById(id).get();
            offer.setName(name);
            offerRepository.save(offer);
            return OfferConverter.convertOfferEntityToDTO(offer);
        }
        LOGGER.info("Offer not found.");
        return new OfferDTO();
    }

    @Override
    public OfferDTO changeDate(Integer id, OfferNewDateDTO newDate) {
        if(offerRepository.findById(id).isPresent() && offerRepository.findById(id).get().getValidity().equals(Boolean.TRUE)
                && newDate.getFinish().isAfter(newDate.getBegin())) {
            OfferEntity offer = offerRepository.findById(id).get();
            offer.setBegin(newDate.getBegin());
            offer.setFinish(newDate.getFinish());
            offerRepository.save(offer);
            return OfferConverter.convertOfferEntityToDTO(offer);
        }
        LOGGER.info("Offer not found or wrong dates.");
        return new OfferDTO();
    }

    @Override
    public OfferDTO changePrice(Integer id, Double price) {
        if(offerRepository.findById(id).isPresent() && offerRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            OfferEntity offer = offerRepository.findById(id).get();
            offer.setPrice(price);
            offerRepository.save(offer);
            return OfferConverter.convertOfferEntityToDTO(offer);
        }
        LOGGER.info("Offer not found.");
        return new OfferDTO();
    }

    @Override
    public OfferDTO addOperations(Integer id, List<OperationDTO> operations) {
        if(offerRepository.findById(id).isPresent() && offerRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            OfferEntity offer = offerRepository.findById(id).get();
            for (OperationDTO operationDTO: operations) {
                offer.getOperations().addAll(
                        operationRepository.findAll().stream()
                                .filter(operation -> operation.getId().equals(operationDTO.getId()) && operation.getValidity().equals(Boolean.TRUE))
                                .collect(Collectors.toList()));
            }
            offer.setPrice(OfferUtil.calculateOfferPrice(offer.getOperations(), offer.getCategory()));
            offerRepository.save(offer);
            return OfferConverter.convertOfferEntityToDTO(offer);
        }
        LOGGER.info("Offer not found.");
        return new OfferDTO();
    }

    @Override
    public OfferDTO removeOperation(Integer offerId, Integer operationId) {
        if(offerRepository.findById(offerId).isPresent() && offerRepository.findById(offerId).get().getValidity().equals(Boolean.TRUE)) {
            if(operationRepository.findById(operationId).isPresent() && operationRepository.findById(operationId).get().getValidity().equals(Boolean.TRUE)) {
                OfferEntity offer = offerRepository.findById(offerId).get();
                offer.getOperations().remove(operationRepository.findById(operationId).get());
                offer.setPrice(OfferUtil.calculateOfferPrice(offer.getOperations(), offer.getCategory()));
                offerRepository.save(offer);
            }
            LOGGER.info("Operation not found.");
        }
        LOGGER.info("Offer not found.");
        return new OfferDTO();
    }

    @Override
    public OfferDTO changeCategory(Integer offerId, Integer categoryId) {
        if((offerRepository.findById(offerId).isPresent() && offerRepository.findById(offerId).get().getValidity().equals(Boolean.TRUE))
            || (categoryRepository.findById(categoryId).isPresent() && categoryRepository.findById(categoryId).get().getValidity().equals(Boolean.TRUE)) ) {
            OfferEntity offer = offerRepository.findById(offerId).get();
            offer.setCategory(categoryRepository.findById(categoryId).get());
            offer.setPrice(OfferUtil.calculateOfferPrice(offer.getOperations(), offer.getCategory()));
            offerRepository.save(offer);
            return OfferConverter.convertOfferEntityToDTO(offer);
        }
        LOGGER.info("Offer or category not found.");
        return new OfferDTO();
    }

    @Override
    public Integer deleteOffer(Integer id) {
        if(offerRepository.findById(id).isPresent() && offerRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            OfferEntity offer = offerRepository.findById(id).get();
            offer.setValidity(Boolean.FALSE);
            offerRepository.save(offer);
            return id;
        }
        LOGGER.info("Offer not found.");
        return null;
    }

    @Override
    public OfferDTO restoreOffer(Integer id) {
        if(offerRepository.findById(id).isPresent() && offerRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            OfferEntity offer = offerRepository.findById(id).get();
            offer.setValidity(Boolean.TRUE);
            offerRepository.save(offer);
            return OfferConverter.convertOfferEntityToDTO(offer);
        }
        LOGGER.info("Offer not found.");
        return new OfferDTO();
    }
}
