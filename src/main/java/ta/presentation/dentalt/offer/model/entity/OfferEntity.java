package ta.presentation.dentalt.offer.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ta.presentation.dentalt.operation.model.entity.OperationEntity;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "begin")
    private LocalDate begin;
    @Column(name = "finish")
    private LocalDate finish;
    @Column(name = "price")
    private Double price;
    @ManyToMany
    @JoinTable(name = "offers_operations",
            joinColumns = @JoinColumn(name = "offers_id"),
            inverseJoinColumns = @JoinColumn(name = "operation_id")
    )
    private List<OperationEntity> operations;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @Column(name = "validity")
    private Boolean validity;
}
