package tw.com.softleader.springdatatraining.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import tw.com.softleader.springdatatraining.common.GenericEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(indexes = {
    @Index(columnList = "name")
})
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class MemberEntity extends GenericEntity {

    @Column(length = 50, nullable = false)
    String name;

    // 十兆千百十億千百十萬千百十個零零零零零零
    @Column(precision = 26, scale = 6)
    BigDecimal salary;

    LocalDateTime loginTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    MemberType type;

    @Singular
    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH })
    @JoinColumn(name = "MEMBER_ID")
    List<PhoneEntity> phones;

    Long phoneId;
}
