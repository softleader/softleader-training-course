package tw.com.softleader.training.mapstruct_training;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface SampleEntityMapper {

  SampleVo toVo(SampleEntity entity);

  @Mapping(target = "id", ignore = true)
  SampleEntity copy(SampleEntity entity);

  List<SampleEntity> copy(List<SampleEntity> entities);

  SampleEntity update(@MappingTarget SampleEntity dbEntity, SampleUpdateCmd cmd);
}
