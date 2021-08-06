package tw.com.softleader.demoweb.sample.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tw.com.softleader.demoweb.sample.service.SampleService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Valid
@Tag(name = "範例", description = "示範用的API")
@Slf4j
@Controller
@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired SampleService sampleService;

    @Operation(summary = "show error sample", description = "顯示錯誤")
    @GetMapping("/error")
    public void error() {
        String str = null;
        str.length();
    }

    @Operation(summary = "Query", description = "查詢資源")
    @GetMapping
    public List<SampleDto> query(
        @Parameter(description = "姓名(模糊查詢)") @RequestParam(required = false) String name,
        @Parameter(description = "日期(起)") @RequestParam(required = false) LocalDate dateFrom,
        @Parameter(description = "日期(迄)") @RequestParam(required = false) LocalDate dateTo
    ) {
        log.debug("query: name:{}, dateFrom:{}, dateTo:{}", name, dateFrom, dateTo);
        return sampleService.query(name, dateFrom, dateTo);
    }

    @Operation(summary = "Query", description = "以ID查詢單一物件")
    @GetMapping("/{id}")
    public SampleDto queryOne(@Parameter(description = "欲查詢的資源ID") @PathVariable Long id) {
        log.debug("query id: {}", id);
        return sampleService.queryOne(id);
    }

    @Operation(summary = "Insert", description = "新增資源")
    @PostMapping
    public void insert(@Parameter(description = "要新增的物件") @Valid @RequestBody SampleDto sampleDto) {
        log.info("insert dto: {}", sampleDto);
        sampleService.insert(sampleDto);
    }

    @Operation(summary = "Update", description = "修改資源")
    @PutMapping
    public void update(@Parameter(description = "要修改的物件") @Valid @RequestBody SampleDto sampleDto) {
        log.info("update dto: {}", sampleDto);
        sampleService.update(sampleDto);
    }

    @Operation(summary = "Delete", description = "刪除資源")
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "欲刪除的資源ID") @PathVariable Long id) {
        log.info("delete id: {}", id);
        sampleService.delete(id);
    }

    private void handleError(final String method, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                log.warn(method + " valid error: {}.{}:[{}]{}", error.getObjectName(), error.getField(), error.getCode(), error.getDefaultMessage());
            }
        }
    }

}
