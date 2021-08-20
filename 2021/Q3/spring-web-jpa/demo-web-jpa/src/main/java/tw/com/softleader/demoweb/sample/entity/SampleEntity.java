package tw.com.softleader.demoweb.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(
    name = "SAMPLE",
    uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SampleEntity extends GenericEntity {

    @Column(length = 20)
    String name;

    @Column(precision = 26, scale = 6)
    BigDecimal amount;

    LocalDate date;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sampleId")
    List<SampleDetailEntity> details;

}
