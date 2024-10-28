package tw.com.softleader.training_project;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SampleMapper {

  @Mapping(target = "sampleColumn00", ignore = true)
  @Mapping(target = "sampleColumn09_1", source = "sampleColumn09")
  Sample0xDto to0xDto(SampleEntity entity);

  Sample1xDto to1xDto(SampleEntity entity);

  Sample2xDto to2xDto(SampleEntity entity);

}
