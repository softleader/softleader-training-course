package tw.com.softleader.training._2.web;

import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tw.com.softleader.training._2.SimpleBean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@Controller
class SampleController {

  static final List<MemberDto> MEMBERS = new Vector<>();

  @GetMapping("/hello")
  @ResponseBody
  public String hello(SimpleBean simpleBean) {
    log.info("{}", simpleBean);
    return "Hello World";
  }

  @SneakyThrows
  @GetMapping("/error")
  @ResponseBody
  public String error() {
    //    if (name.equals("Rhys")) {
    //      throw new IllegalArgumentException("Rhys不可用這支method");
    //    }
    //    throw new IllegalStateException();
    Thread.sleep(2000);
    throw new UnsupportedOperationException();
  }

  @GetMapping("/member/{eid}/hello")
  @ResponseBody
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
  @ResponseBody
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
  @ResponseBody
  public int inertMember(
    @RequestBody @Validated MemberDto memberDto,
    BindingResult bindingResult) {
//    if (memberDto.getEid() == null) {
//      throw new IllegalArgumentException("EID不可為空");
//    }
    memberDto.setId(MEMBERS.size() + 1L);
    MEMBERS.add(memberDto);
    return MEMBERS.size();
  }

  @PostMapping("/members")
  @ResponseBody
  public int inertMembers(@RequestBody List<MemberDto> memberDtos) {
    for (int i = 0; i < memberDtos.size(); i++) {
      memberDtos.get(i).setId(MEMBERS.size() + 1L + i);
    }
    MEMBERS.addAll(memberDtos);
    return MEMBERS.size();
  }

  @GetMapping("/members")
  @ResponseBody
  public List<MemberDto> queryMember(
      @RequestParam(required = false) Integer eid,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Set<String> types,
      Pageable pageable) {
    log.info("pageable={}", pageable);
    var pageNumber = pageable.getPageNumber();
    var pageSize = pageable.getPageSize();
    return MEMBERS.stream()
        .filter(m -> eid == null || Objects.equals(m.getEid(), eid))
        .filter(m -> name == null || name.isBlank() || Objects.equals(m.getName(), name))
        .filter(
            m ->
                types == null || types.isEmpty() || m.getTypes().stream().anyMatch(types::contains))
        .sorted(Comparator.comparing(MemberDto::getEid))
        .skip((long)pageNumber * pageSize)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  @PutMapping("/do-something/{id}")
  @ResponseBody
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

  @PostMapping("/member/phone")
  @ResponseBody
  public int inertMembers(
    @RequestParam Integer memberEid,
    @RequestParam String phone) {
    MEMBERS.stream().filter(m -> m.getEid().equals(memberEid))
      .findFirst().ifPresent(m -> {
        m.setPhone(phone);
      });
    return MEMBERS.size(); // ViewResolver
  }

  @GetMapping("/download")
  public void download(HttpServletResponse response) throws IOException {
    String content = "124rewfdsfsgf32rfwsvd";
    response.setHeader("Content-disposition", "attachment; filename=download.txt");
    response.setContentType("plain/text");
    var bytes = content.getBytes(); // 整串byte全都會被load到server記憶體中
    var inputStream = new ByteArrayInputStream(bytes);
    response.setContentLength(inputStream.available());
    IOUtils.copy(
      inputStream,
      response.getOutputStream());
  }

}
