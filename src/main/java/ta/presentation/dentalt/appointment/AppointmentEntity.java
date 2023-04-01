package ta.presentation.dentalt.appointment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ta.presentation.dentalt.operation.OperationEntity;
import ta.presentation.dentalt.user.UserEntity;

import java.time.LocalDateTime;

@Data
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
    private UserEntity patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private UserEntity doctor;
    @ManyToOne
    @JoinColumn(name = "operation_id")
    private OperationEntity operation;
    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;
    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;
    @Column(name = "completion_status")
    private String completionStatus;
    @Column(name = "payment_status")
    private String paymentStatus;
}
