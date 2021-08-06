package tw.com.softleader.demoweb.sample.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "SAMPLE_DETAIL")
public class SampleDetailEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 20)
    String name;

    LocalDate date;

}
