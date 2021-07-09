package tw.com.soflteader.training.demo.sample2;

import lombok.Data;
import tw.com.soflteader.training.demo.sample1.MemberEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class GroupEntity {

    @Id
    @GeneratedValue
    Long id;

    String name;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<MemberEntity> members;


}
