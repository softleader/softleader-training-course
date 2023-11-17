package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class SampleTxNestService {

  final SampleTxService sampleTxService;
  final SampleNoTxService sampleNoTxService;

  public void saveTxTwoX2() {
    sampleTxService.saveTwo();
    sampleTxService.saveTwo();
  }

  public void saveNoTxTwoX2() {
    sampleNoTxService.saveTwo();
    sampleNoTxService.saveTwo();
  }

  public void saveOneX2Complex() {
    sampleTxService.saveOneRequiresNew();
    sampleTxService.saveOneNotSupported();
  }

}
