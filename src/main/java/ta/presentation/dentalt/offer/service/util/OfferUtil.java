package ta.presentation.dentalt.offer.service.util;

import ta.presentation.dentalt.category.model.entity.CategoryEntity;
import ta.presentation.dentalt.operation.model.entity.OperationEntity;

import java.util.List;

public class OfferUtil {

    public static Double calculateOfferPrice(List<OperationEntity> operations, CategoryEntity category) {
        Double operationsPrice = findOperationsPrice(operations);
        return (operationsPrice * category.getDiscountPercentage()) / 100;
    }

    private static Double findOperationsPrice(List<OperationEntity> operations) {
        Double operationsPrice = 0.0;
        for (OperationEntity operation: operations) {
            operationsPrice += operation.getPrice();
        }
        return operationsPrice;
    }
}
