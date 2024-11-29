package tw.com.softleader.example.training_20241129.data;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(builder = @Builder(disableBuilder = true))
public interface SampleMapper {

  SampleMainEntity toEntity(SampleMainVo vo);

  SampleDetailEntity toEntity(SampleDetailVo vo);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "main", ignore = true)
  SampleDetailEntity replaceEntity(
      SampleDetailEntity entity, @MappingTarget SampleDetailEntity dbEntity);

  SampleMainVo fromEntity(SampleMainEntity entity);

  SampleDetailVo fromEntity(SampleDetailEntity entity);
}
