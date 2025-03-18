package tw.com.softleader.training.mapstruct_training;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(uses = SampleEntityMapper.class)
public abstract class SamplePackMapper {

  private SampleEntityMapper sampleEntityMapper;

  abstract SamplePackEntity copy(SamplePackEntity entity);

  @Mapping(target = "samples", source = "samples", qualifiedByName = "updateSamples")
  abstract SamplePackEntity update(
      @MappingTarget SamplePackEntity dbEntity, SamplePackUpdateCmd cmd);

  @Named("updateSamples")
  void updateSamples(@MappingTarget List<SampleEntity> entities, List<SampleUpdateCmd> cmds) {

    var entityById =
        entities.stream().collect(Collectors.toMap(SampleEntity::getId, Function.identity()));
    var cmdById =
        cmds.stream().collect(Collectors.toMap(SampleUpdateCmd::getId, Function.identity()));
    entityById.forEach(
        (id, entity) ->
            Optional.ofNullable(cmdById.get(id))
                .ifPresent(cmd -> sampleEntityMapper.update(entity, cmd)));
  }
}
