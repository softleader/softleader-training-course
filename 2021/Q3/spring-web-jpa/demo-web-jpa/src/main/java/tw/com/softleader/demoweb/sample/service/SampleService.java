package tw.com.softleader.demoweb.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.softleader.demoweb.sample.dao.SampleDao;
import tw.com.softleader.demoweb.sample.entity.SampleDetailEntity;
import tw.com.softleader.demoweb.sample.entity.SampleEntity;
import tw.com.softleader.demoweb.sample.web.SampleDetailDto;
import tw.com.softleader.demoweb.sample.web.SampleDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service
public class SampleService {

    @Autowired
    private SampleDao sampleDao;

    public List<SampleDto> query(String name, LocalDate dateFrom, LocalDate dateTo) {
        var spec = Specification.<SampleEntity>where(null);
        if (name != null && name.length() > 0) {
            spec = spec.and((r, cq, cb) -> cb.equal(r.get("name"), name));
        }
        var dateSpec1 = Specification.<SampleEntity>where(null);
        if (dateFrom != null) {
            dateSpec1 = dateSpec1.and((r, cq, cb) -> cb.greaterThanOrEqualTo(r.get("date"), dateFrom));
        }
        if (dateFrom != null) {
            dateSpec1 = dateSpec1.and((r, cq, cb) -> cb.lessThanOrEqualTo(r.get("date"), dateTo));
        }
        var dateSpec2 = Specification.<SampleEntity>where(null);
        if (dateFrom != null) {
            dateSpec2 = dateSpec2.and((r, cq, cb) -> cb.greaterThanOrEqualTo(r.join("details").get("date"), dateFrom));
        }
        if (dateFrom != null) {
            dateSpec2 = dateSpec2.and((r, cq, cb) -> cb.lessThanOrEqualTo(r.join("details").get("date"), dateTo));
        }
        var dateAll = dateSpec1.or(dateSpec2);
        spec = spec.and(dateAll);

        Iterable<SampleEntity> entities = sampleDao.findAll(spec);
        return StreamSupport.stream(entities.spliterator(), false)
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public SampleDto queryOne(Long id) {
        return sampleDao.findById(id)
            .map(this::toDto)
            .orElseThrow(() -> new IllegalArgumentException(String.format("查無資料 ID:%s", id)));
    }

    public void insert(SampleDto sampleDto) {
        SampleEntity entity = toEntity(sampleDto);
        sampleDao.save(entity);
    }

//    public void changeDetail(Long sampleId, Long newDetailId) {
//        SampleEntity sample = sampleDao.findById(sampleId)
//            .orElseThrow(() -> new IllegalArgumentException(String.format("查無資料 ID:%s", sampleId)));
//
//        SampleDetailEntity sampleDetail = sampleDetailDao.findById(newDetailId)
//            .orElseThrow(() -> new IllegalArgumentException(String.format("查無Detail資料 ID:%s", newDetailId)));
//
////        sample.getDetails().get(0).setSampleId(newDetailId);
//        sample.setDetails(List.of(sampleDetail));
//    }

    public void update(SampleDto sampleDto) {
        Long id = requiredId(sampleDto);
        SampleEntity dbEntity = sampleDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format("查無資料 ID:%s", id)));
        updateEntity(sampleDto, dbEntity);
        sampleDao.save(dbEntity);
    }

    private Long requiredId(SampleDto sampleDto) {
        return Optional.ofNullable(sampleDto.getId())
            .orElseThrow(() -> new IllegalArgumentException(String.format("沒有輸入 ID")));
    }

    public void delete(Long id) {
        sampleDao.deleteById(id);
    }

    SampleDto toDto(SampleEntity entity) {
        List<SampleDetailDto> details = entity.getDetails().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
        return SampleDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .amount(entity.getAmount())
            .date(entity.getDate())
            .details(details)
            .build();
    }

    SampleDetailDto toDto(SampleDetailEntity entity) {
        return SampleDetailDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .date(entity.getDate())
            .build();
    }

    SampleEntity toEntity(SampleDto dto) {
        List<SampleDetailEntity> details = dto.getDetails().stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
        return SampleEntity.builder()
            .id(dto.getId())
            .name(dto.getName())
            .amount(dto.getAmount())
            .date(dto.getDate())
            .details(details)
            .build();
    }

    void updateEntity(SampleDto dto, SampleEntity entity) {
        entity.setName(dto.getName());
        entity.setAmount(dto.getAmount());
    }

    SampleDetailEntity toEntity(SampleDetailDto dto) {
        return SampleDetailEntity.builder()
            .id(dto.getId())
            .name(dto.getName())
            .date(dto.getDate())
            .build();
    }
}
