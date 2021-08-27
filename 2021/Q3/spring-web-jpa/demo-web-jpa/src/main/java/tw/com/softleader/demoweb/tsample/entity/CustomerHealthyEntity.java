package tw.com.softleader.demoweb.tsample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tw.com.softleader.demoweb.sample.entity.GenericEntity;

import javax.persistence.Entity;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
public class CustomerHealthyEntity extends GenericEntity {
}
