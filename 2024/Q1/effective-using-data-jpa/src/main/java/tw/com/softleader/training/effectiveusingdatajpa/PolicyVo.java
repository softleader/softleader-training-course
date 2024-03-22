package tw.com.softleader.training.effectiveusingdatajpa;

public interface PolicyVo {

  String getPolicyNo();
  Long getProductTypeId();
  String getPreviousPolicyNo();
  String getEndstPolicyNo();
  String getQuotationNo();
  String getOriginQuotationNo();
  String getRenewPolicyNo();
  Long getEndstNo();
  Long getMainPolicyEndstNo();
  Long getExtEndstNo();

}
