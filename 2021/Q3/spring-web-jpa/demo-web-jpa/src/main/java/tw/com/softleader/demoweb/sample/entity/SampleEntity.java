package tw.com.softleader.demoweb.sample.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "SAMPLE")
public class SampleEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 20)
    String name;

    LocalDate date;

    @OneToMany(cascade = { CascadeType.ALL })
    List<SampleDetailEntity> details;

}
