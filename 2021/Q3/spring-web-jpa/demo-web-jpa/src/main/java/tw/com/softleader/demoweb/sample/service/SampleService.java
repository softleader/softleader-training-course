package tw.com.softleader.demoweb.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.softleader.demoweb.sample.dao.SampleDao;
import tw.com.softleader.demoweb.sample.entity.SampleEntity;
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
        Iterable<SampleEntity> entities = sampleDao.findAll();
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
        return SampleDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .amount(entity.getAmount())
            .date(entity.getDate())
            .build();
    }

    SampleEntity toEntity(SampleDto dto) {
        return SampleEntity.builder()
            .id(dto.getId())
            .name(dto.getName())
            .amount(dto.getAmount())
            .date(dto.getDate())
            .build();
    }

    void updateEntity(SampleDto dto, SampleEntity entity) {
        entity.setName(dto.getName());
        entity.setAmount(dto.getAmount());
    }

}
