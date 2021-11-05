package tw.com.softleader.springdatatraining.common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GenericEntity {
    @Id
    @GeneratedValue
    Long id;

    @CreatedBy
    @Column(length = 50)
    String createdBy;

    @CreatedDate
    LocalDateTime createdTime;

    @LastModifiedBy
    @Column(length = 50)
    String modifiedBy;

    @LastModifiedDate
    LocalDateTime modifiedTime;
}
