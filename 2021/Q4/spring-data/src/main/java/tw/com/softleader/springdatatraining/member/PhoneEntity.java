package tw.com.softleader.springdatatraining.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tw.com.softleader.springdatatraining.common.GenericEntity;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PhoneEntity extends GenericEntity {

    String phoneNo;

}
