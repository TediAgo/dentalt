package ta.presentation.dentalt.category.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ta.presentation.dentalt.category.model.enums.Category;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Category name;
    @Column(name = "discount_percentage")
    private Double discountPercentage;
    @Column(name = "validity")
    private Boolean validity;
}
