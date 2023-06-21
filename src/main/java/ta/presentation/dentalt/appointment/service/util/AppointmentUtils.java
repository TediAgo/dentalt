package ta.presentation.dentalt.appointment.service.util;

import ta.presentation.dentalt.appointment.model.entity.AppointmentEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentUtils {

    public static boolean isDateTimeFree(LocalDateTime startDateTime, LocalDateTime endDateTime, List<AppointmentEntity> appointmentsToCheck) {

        return appointmentsToCheck.stream()
                .filter(appointment -> (appointment.getStartDateTime().isBefore(startDateTime) && appointment.getEndDateTime().isAfter(startDateTime))
                                    && appointment.getStartDateTime().isBefore(endDateTime) && appointment.getEndDateTime().isAfter(endDateTime)
                                    && appointment.getStartDateTime().isAfter(startDateTime) && appointment.getEndDateTime().isBefore(endDateTime))
                .collect(Collectors.toList())
                .size() == 0;
    }
}
