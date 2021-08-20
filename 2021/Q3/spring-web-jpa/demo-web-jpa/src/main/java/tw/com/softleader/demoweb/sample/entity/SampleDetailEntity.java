package tw.com.softleader.demoweb.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SampleDetailEntity extends GenericEntity {

    Long sampleId;
    String name;
    LocalDate date;

}
