package tw.com.softleader.training.effectiveusingdatajpa;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 保單
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
    name = "policy",
    indexes = {
        @Index(name = "policy_idx", columnList = "policyNo", unique = true)
    })
public class PolicyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String policyNo;

  @JoinColumn(name = "policy_id")
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<CoverageEntity> coverage;

  /**
   * 險別代碼ID
   */
  @Column(name = "PRODUCT_TYPE_ID")
  protected Long productTypeId;

  /**
   * 前年度保單號碼
   */
  @Column(name = "PREVIOUS_POLICY_NO")
  protected String previousPolicyNo;

  /**
   * 批單號碼
   */
  @Column(name = "ENDST_POLICY_NO")
  protected String endstPolicyNo;

  /**
   * 報價單號
   */
  @Column(name = "QUOTATION_NO")
  protected String quotationNo;

  /**
   * 原報價單號
   */
  @Column(name = "ORIGIN_QUOTATION_NO")
  protected String originQuotationNo;

  /**
   * 續保單號碼
   */
  @Column(name = "RENEW_POLICY_NO")
  protected String renewPolicyNo;

  /**
   * 內部批單號
   */
  @Column(name = "ENDST_NO")
  protected Long endstNo;

  /**
   * 主保單內部批單號
   */
  @Column(name = "MAIN_POLICY_ENDST_NO")
  protected Long mainPolicyEndstNo;

  /**
   * 外部批單號
   */
  @Column(name = "EXT_ENDST_NO")
  protected Long extEndstNo;

  /**
   * 批單是否顯示
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "SHOW_ENDST", length = 1)
  protected YesNo showEndst;

}
