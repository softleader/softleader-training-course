package tw.com.softleader.training._2.web;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
class SampleController {

  static final List<MemberDto> MEMBERS = new Vector<>();

  @GetMapping("/hello")
  public String hello() {
    return "Hello World";
  }

  @SneakyThrows
  @GetMapping("/error")
  public String error() {
    //    if (name.equals("Rhys")) {
    //      throw new IllegalArgumentException("Rhys不可用這支method");
    //    }
    //    throw new IllegalStateException();
    Thread.sleep(2000);
    throw new UnsupportedOperationException();
  }

  @GetMapping("/member/{eid}/hello")
  public String helloWithName(@PathVariable int eid, @RequestParam String lang) {
    switch (eid) {
      case 132:
        return lang.equalsIgnoreCase("ZH") ? "Hello 張鼎鑫" : "Hello Rhys";
      default:
        throw new UnsupportedOperationException("不支援跟這個人say hello " + eid);
    }
  }

  static final ExecutorService POOL = Executors.newSingleThreadExecutor();

  @GetMapping("/member")
  public String queryMemberSimple(
      @RequestParam(required = false) Integer eid,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Set<String> types) {
    if (eid != null && eid.equals(132)) {
      return "(132)rhys";
    }
    if (name != null && name.equals("Rhys")) {
      return "(132)rhys";
    }
    if (types != null && !types.isEmpty()) {
      return "(132)rhys:" + types;
    }
    return "";
  }

  @PostMapping("/member")
  public int inertMember(@RequestBody MemberDto memberDto) {
    memberDto.setId(MEMBERS.size() + 1L);
    MEMBERS.add(memberDto);
    return MEMBERS.size();
  }

  @PostMapping("/members")
  public int inertMembers(@RequestBody List<MemberDto> memberDtos) {
    for (int i = 0; i < memberDtos.size(); i++) {
      memberDtos.get(i).setId(MEMBERS.size() + 1L + i);
    }
    MEMBERS.addAll(memberDtos);
    return MEMBERS.size();
  }

  @GetMapping("/members")
  public List<MemberDto> queryMember(
      @RequestParam(required = false) Integer eid,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Set<String> types) {
    return MEMBERS.stream()
        .filter(m -> eid == null || Objects.equals(m.getEid(), eid))
        .filter(m -> name == null || name.isBlank() || Objects.equals(m.getName(), name))
        .filter(
            m ->
                types == null || types.isEmpty() || m.getTypes().stream().anyMatch(types::contains))
        .sorted(Comparator.comparing(MemberDto::getEid))
        .collect(Collectors.toList());
  }

  @PutMapping("/do-something/{id}")
  public void doSomething(@PathVariable Long id) {
    MEMBERS.stream()
        .filter(m -> id.equals(m.id))
        .findFirst()
        .ifPresent(
            member -> {
              member.setBusy(true);
              POOL.submit(
                  () -> {
                    try {
                      Thread.sleep(1000);
                      member.setBusy(false);
                    } catch (Exception e) {
                      log.error("error", e);
                    }
                  });
            });
  }
}
