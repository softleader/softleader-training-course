package tw.com.softleader.demoweb.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.softleader.demoweb.sample.dao.SampleDao;
import tw.com.softleader.demoweb.sample.dao.SampleDetailDao;
import tw.com.softleader.demoweb.sample.entity.SampleDetailEntity;
import tw.com.softleader.demoweb.sample.entity.SampleEntity;
import tw.com.softleader.demoweb.sample.web.SampleDetailDto;
import tw.com.softleader.demoweb.sample.web.SampleDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class SampleService {

    @Autowired SampleDao sampleDao;
    @Autowired SampleDetailDao sampleDetailDao;

    public List<SampleDto> query(String name, LocalDate dateFrom, LocalDate dateTo) {
        Specification<SampleEntity> spec = Specification.where(null);
        if (name != null) {
            spec = spec.and((r, cq, cb) -> cb.like(r.get("name"), name + "%"));
        }
        if (dateFrom != null) {
            spec = spec.and((r, cq, cb) -> cb.greaterThanOrEqualTo(r.get("date"), dateFrom));
        }
        if (dateTo != null) {
            spec = spec.and((r, cq, cb) -> cb.lessThanOrEqualTo(r.get("date"), dateTo));
        }

        List<SampleEntity> entities = sampleDao.findAll(spec);
        List<SampleDto> dtos = new ArrayList<>();
        for (SampleEntity entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public SampleDto queryOne(Long id) {
        SampleEntity entity = sampleDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("查無物件 ID:" + id));
        return toDto(entity);
    }

    public void insert(SampleDto sampleDto) {
        SampleEntity entity = new SampleEntity();
        entity.setName(sampleDto.getName());
        entity.setDate(sampleDto.getDate());

//        List<SampleDetailEntity> detailEntities = new ArrayList<>();
//        for (SampleDetailDto detailDto : sampleDto.getDetails()) {
//            SampleDetailEntity detailEntity = new SampleDetailEntity();
//            updateToEntity(detailDto, detailEntity);
//            detailEntities.add(detailEntity);
//        }

        sampleDao.save(entity);
//        entity.setDetails(detailEntities);
    }

    public void update(SampleDto sampleDto) {
        SampleEntity entity = Optional.ofNullable(sampleDto.getId())
            .flatMap(sampleDao::findById)
            .orElseThrow(() -> new IllegalArgumentException("查無物件 ID:" + sampleDto.getId()));

        entity.setName(sampleDto.getName());
        entity.setDate(sampleDto.getDate());
    }

    public void delete(Long id) {
        sampleDao.deleteById(id);
    }

    private SampleDto toDto(SampleEntity entity) {
        SampleDto dto = new SampleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDate(entity.getDate());
        return dto;
    }

    private void updateToEntity(SampleDetailDto dto, SampleDetailEntity entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
    }
}
