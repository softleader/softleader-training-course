package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class SampleTxNestService {

  final SampleTxService sampleTxService;
  final SampleNoTxService sampleNoTxService;

  public void saveTxTwoX2() {
    sampleTxService.saveTwo();
    sampleTxService.saveTwo();
  }

  public void saveTxTwoX2Error() {
    sampleTxService.saveTwo();
    sampleTxService.saveTwoError();
  }

  public void saveTxTwoX2CatchError() {
    sampleTxService.saveTwo();
    try {
      sampleTxService.saveTwoError();
    } catch (Exception e) {
      log.error("catch saveTwoError: {}", e.getMessage());
    }
  }

  public void saveNoTxTwoX2() {
    sampleNoTxService.saveTwo();
    sampleNoTxService.saveTwo();
  }

  public void saveNoTxTwoX2Error() {
    sampleNoTxService.saveTwo();
    sampleNoTxService.saveTwoError();
  }

  public void saveNoTxTwoX2CatchError() {
    sampleNoTxService.saveTwo();
    try {
      sampleNoTxService.saveTwoError();
    } catch (Exception e) {
      log.error("catch saveTwoError: {}", e.getMessage());
    }
  }

  public void saveOneX2Complex() {
    sampleTxService.saveOneRequiresNew();
    sampleTxService.saveOneNotSupported();
  }

  public void saveOneX2ComplexCatchError() {
    try {
      sampleTxService.saveOneRequiresNewError();
    } catch (Exception e) {
      log.error("catch saveTwoError: {}", e.getMessage());
    }
    sampleTxService.saveOneNotSupported();
  }

  public void saveAndInnerSaveRequiresNew() {
    sampleTxService.saveTwo();
    saveRequiresNew();
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void saveRequiresNew() {
    sampleTxService.saveTwo();
  }

}
