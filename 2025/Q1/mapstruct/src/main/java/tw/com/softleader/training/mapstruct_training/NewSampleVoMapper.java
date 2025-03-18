package tw.com.softleader.training.mapstruct_training;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface NewSampleVoMapper {

  @Mapping(target = "chtName", source = "name")
  NewSampleVo fromOldVo(SampleVo vo);

  @AfterMapping
  default NewSampleVo fromOldVoAfter(@MappingTarget NewSampleVo newVo, SampleVo vo) {
    newVo.setNameAge(vo.getName() + "-" + vo.getAge());
    return newVo;
  }
}
