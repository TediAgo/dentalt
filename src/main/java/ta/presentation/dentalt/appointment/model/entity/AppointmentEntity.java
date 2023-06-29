package ta.presentation.dentalt.appointment.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ta.presentation.dentalt.appointment.model.enums.CompletionStatus;
import ta.presentation.dentalt.appointment.model.enums.PaymentStatus;
import ta.presentation.dentalt.operation.model.entity.OperationEntity;
import ta.presentation.dentalt.user.model.entity.UserEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private UserEntity patientEntity;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private UserEntity doctorEntity;
    @ManyToOne
    @JoinColumn(name = "operation_id")
    private OperationEntity operationEntity;
    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;
    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;
    @Column(name = "completion_status")
    @Enumerated(EnumType.STRING)
    private CompletionStatus completionStatus;
    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Column(name = "validity")
    private Boolean validity;
}
