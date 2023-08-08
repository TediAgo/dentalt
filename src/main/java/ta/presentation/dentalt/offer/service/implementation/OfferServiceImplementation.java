package ta.presentation.dentalt.offer.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.service.implementation.AppointmentServiceImplementation;
import ta.presentation.dentalt.offer.repository.CategoryRepository;
import ta.presentation.dentalt.offer.model.dto.OfferDTO;
import ta.presentation.dentalt.offer.model.dto.OfferNewDateDTO;
import ta.presentation.dentalt.offer.model.entity.OfferEntity;
import ta.presentation.dentalt.offer.repository.OfferRepository;
import ta.presentation.dentalt.offer.model.mapper.OfferConverter;
import ta.presentation.dentalt.offer.service.OfferService;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;
import ta.presentation.dentalt.offer.service.util.OfferUtil;

import ta.presentation.dentalt.operation.model.entity.OperationEntity;
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
        if(offerRepository.findById(id).isEmpty() || offerRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Offer not found.");
            throw new RuntimeException("Offer not found.");
        }
        return OfferConverter.convertOfferEntityToDTO(offerRepository.findById(id).get());
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
            throw new RuntimeException("Operations not found.");
        }
        if(offerDTO.getCategory() == null
                || categoryRepository.findById(offerDTO.getCategory().getId()).isEmpty()
                || offerRepository.findById(offerDTO.getCategory().getId()).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Category not found.");
            throw new RuntimeException("Category not found.");
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
        offer.setValidity(Boolean.TRUE);

        offerRepository.save(offer);
        return OfferConverter.convertOfferEntityToDTO(offer);
    }

    @Override
    public OfferDTO changeName(Integer id, String name) {
        if(offerRepository.findById(id).isEmpty() || offerRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Offer not found.");
            throw new RuntimeException("Offer not found.");
        }
        OfferEntity offer = offerRepository.findById(id).get();
        offer.setName(name);
        offerRepository.save(offer);
        return OfferConverter.convertOfferEntityToDTO(offer);
    }

    @Override
    public OfferDTO changeDate(Integer id, OfferNewDateDTO newDate) {
        if(offerRepository.findById(id).isEmpty() || offerRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Offer not found.");
            throw new RuntimeException("Offer not found.");
        }
        if(newDate.getFinish().isBefore(newDate.getBegin())) {
            LOGGER.info("Wrong dates.");
            throw new RuntimeException("Wrong dates.");
        }
        OfferEntity offer = offerRepository.findById(id).get();
        offer.setBegin(newDate.getBegin());
        offer.setFinish(newDate.getFinish());
        offerRepository.save(offer);
        return OfferConverter.convertOfferEntityToDTO(offer);
    }

    @Override
    public OfferDTO addOperations(Integer id, List<OperationDTO> operations) {
        if(offerRepository.findById(id).isEmpty() || offerRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Offer not found.");
            throw new RuntimeException("Offer not found.");
        }
        OfferEntity offer = offerRepository.findById(id).get();
        List<OperationEntity> existingOperations = offer.getOperations();
        for (OperationDTO operationDTO: operations) {
            offer.getOperations().addAll(
                    operationRepository.findAll().stream()
                            .filter(operation -> operation.getId().equals(operationDTO.getId()) && operation.getValidity().equals(Boolean.TRUE)
                                    && !existingOperations.contains(operation))
                            .collect(Collectors.toList()));
        }
        offer.setPrice(OfferUtil.calculateOfferPrice(offer.getOperations(), offer.getCategory()));
        offerRepository.save(offer);
        return OfferConverter.convertOfferEntityToDTO(offer);
    }

    @Override
    public OfferDTO removeOperation(Integer offerId, Integer operationId) {
        if(offerRepository.findById(offerId).isEmpty() || offerRepository.findById(offerId).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Offer not found.");
            throw new RuntimeException("Offer not found.");
        }
        if(operationRepository.findById(operationId).isEmpty() || operationRepository.findById(operationId).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Operation not found.");
            throw new RuntimeException("Operation not found.");
        }
        OfferEntity offer = offerRepository.findById(offerId).get();
        offer.getOperations().remove(operationRepository.findById(operationId).get());
        offer.setPrice(OfferUtil.calculateOfferPrice(offer.getOperations(), offer.getCategory()));
        offerRepository.save(offer);
        return OfferConverter.convertOfferEntityToDTO(offer);
    }

    @Override
    public OfferDTO changeCategory(Integer offerId, Integer categoryId) {
        if(offerRepository.findById(offerId).isEmpty() || offerRepository.findById(offerId).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Offer not found.");
            throw new RuntimeException("Offer not found.");
        }
        if(categoryRepository.findById(categoryId).isEmpty() || categoryRepository.findById(categoryId).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Category not found.");
            throw new RuntimeException("Category not found.");
        }
        OfferEntity offer = offerRepository.findById(offerId).get();
        offer.setCategory(categoryRepository.findById(categoryId).get());
        offer.setPrice(OfferUtil.calculateOfferPrice(offer.getOperations(), offer.getCategory()));
        offerRepository.save(offer);
        return OfferConverter.convertOfferEntityToDTO(offer);
    }

    @Override
    public Integer deleteOffer(Integer id) {
        if(offerRepository.findById(id).isEmpty() || offerRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            LOGGER.info("Offer not found.");
            throw new RuntimeException("Offer not found.");
        }
        OfferEntity offer = offerRepository.findById(id).get();
        offer.setValidity(Boolean.FALSE);
        offerRepository.save(offer);
        return id;
    }

    @Override
    public OfferDTO restoreOffer(Integer id) {
        if(offerRepository.findById(id).isEmpty() || offerRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            LOGGER.info("Offer not found.");
            throw new RuntimeException("Offer not found");
        }
        OfferEntity offer = offerRepository.findById(id).get();
        offer.setValidity(Boolean.TRUE);
        offerRepository.save(offer);
        return OfferConverter.convertOfferEntityToDTO(offer);
    }
}
