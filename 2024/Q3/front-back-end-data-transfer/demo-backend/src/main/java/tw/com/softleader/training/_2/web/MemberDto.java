package tw.com.softleader.training._2.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MemberDto {

  Long id;
  @NotNull
  Integer eid;
  @NotBlank
  String name;
  List<String> types;
  boolean busy;
  String phone;

}
