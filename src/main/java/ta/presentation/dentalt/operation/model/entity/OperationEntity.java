package ta.presentation.dentalt.operation.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ta.presentation.dentalt.offer.model.entity.OfferEntity;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operation")
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @ManyToMany(mappedBy = "operation_offers")
    private List<OfferEntity> students;
    @Column(name = "validity")
    private Boolean validity;
}
