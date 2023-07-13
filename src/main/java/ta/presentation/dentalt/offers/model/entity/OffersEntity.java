package ta.presentation.dentalt.offers.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ta.presentation.dentalt.category.model.entity.CategoryEntity;
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
public class OffersEntity {

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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "offers_operation",
            joinColumns = @JoinColumn(name = "offers_id"),
            inverseJoinColumns = @JoinColumn(name = "operation_id")
    )
    private List<OperationEntity> operations;
    @ManyToOne
    private CategoryEntity category;
    @Column(name = "validity")
    private Boolean validity;
}
