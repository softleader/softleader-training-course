package tw.com.softleader.training.demoreactbff.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Transactional
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberDao memberDao;

    @GetMapping
    public Page<MemberEntity> query(Pageable pageable) {
        return memberDao.findAll(pageable);
    }

    @GetMapping("/{id}")
    public MemberEntity queryOne(@PathVariable Long id) {
        return memberDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("no resource of id:" + id));
    }

    @RequestMapping(method = { POST, PUT })
    public void save(@RequestBody MemberEntity entity) {
        MemberEntity toSave = Optional.ofNullable(entity.getId())
            .flatMap(memberDao::findById)
            .orElseGet(() -> {
                entity.setId(null);
                return entity;
            });
        toSave.setName(entity.getName());
        toSave.setDutyDate(entity.getDutyDate());

        memberDao.save(toSave);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        memberDao.deleteById(id);
    }

    @PostConstruct
    void init() {
        for (int i = 0; i < 105; i++) {
            memberDao.save(MemberEntity.builder().name("name" + i).dutyDate(LocalDateTime.now()).build());
        }
    }

}
