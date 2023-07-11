package ta.presentation.dentalt.appointment.service.util;

import ta.presentation.dentalt.appointment.model.entity.AppointmentEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentUtils {

    public static boolean isDateTimeFree(LocalDateTime start, LocalDateTime end, List<AppointmentEntity> appointmentsToCheck) {

        return appointmentsToCheck.stream()
                .filter(appointment -> (appointment.getStartDateTime().isBefore(start) && appointment.getEndDateTime().isAfter(start))
                                    || (appointment.getStartDateTime().isBefore(end) && appointment.getEndDateTime().isAfter(end))
                                    || (appointment.getStartDateTime().isAfter(start) && appointment.getEndDateTime().isBefore(end))
                                    || (appointment.getStartDateTime().equals(start) || appointment.getEndDateTime().equals(end)) )
                .collect(Collectors.toList())
                .size() == 0;
    }
}
