package tw.com.softleader.demoweb.tsample.web;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.softleader.demoweb.tsample.service.NbService;

@Transactional
@RestController
@RequestMapping("/nb")
@RequiredArgsConstructor
public class NbController {

    final NbService nbService;

    @PostMapping
    public void save() {
        nbService.saveCustomer();
        nbService.savePolicy();
    }

}
