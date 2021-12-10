package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@CrossOrigin("http://localhost:3000/")
@Slf4j
@RestController
@RequestMapping("/member")
public class MemberApi {

  static final List<Member> DB = new ArrayList<>();

  @GetMapping
  public List<Member> queryAll() {
    return DB;
  }

  @PostMapping
  public void add(@RequestBody Member member) {
    var nextId = DB.stream()
        .map(Member::getId)
        .max(Comparator.naturalOrder())
        .map(id -> id + 1)
        .orElse(1L);
    member.setId(nextId);
    DB.add(member);
    log.info("insert member: {}", member);
  }

  @DeleteMapping("/{id}")
  public void add(@PathVariable Long id) {
    DB.stream()
        .filter(data -> data.id.equals(id))
        .findFirst()
        .ifPresent(DB::remove);
    log.info("insert member: {}", id);
  }

}
