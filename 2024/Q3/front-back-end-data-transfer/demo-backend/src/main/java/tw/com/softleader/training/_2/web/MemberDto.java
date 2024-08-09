package tw.com.softleader.training._2.web;

import java.util.List;
import lombok.Data;

@Data
public class MemberDto {

  Long id;
  Integer eid;
  String name;
  List<String> types;
  boolean busy;
}
