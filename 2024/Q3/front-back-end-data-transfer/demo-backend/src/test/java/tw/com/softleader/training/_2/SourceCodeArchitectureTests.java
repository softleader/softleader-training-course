package tw.com.softleader.training._2;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars;
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import tw.com.softleader.kapok.test.archunit.GeneralRules.GeneralRulesSuite;
import tw.com.softleader.kapok.test.archunit.JavaClassesThat;
import tw.com.softleader.kapok.test.archunit.JavaConstructorsShould;
import tw.com.softleader.kapok.test.archunit.JavaMethodsShould;
import tw.com.softleader.kapok.test.archunit.JavaMethodsThat;
import tw.com.softleader.kapok.test.archunit.NamingRule.NamingRuleSuite;

@AnalyzeClasses(
    packagesOf = SourceCodeArchitectureTests.class,
    importOptions = {DoNotIncludeTests.class, DoNotIncludeJars.class})
class SourceCodeArchitectureTests {

  @ArchTest static final ArchTests general_rules = ArchTests.in(GeneralRulesSuite.class);

  @ArchTest static final ArchTests naming_rules = ArchTests.in(NamingRuleSuite.class);

  @ArchTest
  static final ArchRule methods_should_have_at_most_60_lines =
      ArchRuleDefinition.methods()
          .that(JavaMethodsThat.areNotLombokBuilderDefaults())
          .and()
          .areDeclaredInClassesThat(JavaClassesThat.areNotLombokBuilders())
          .and()
          .areDeclaredInClassesThat(JavaClassesThat.areNotMapStructMapperImplementations())
          .and()
          .areDeclaredInClassesThat(JavaClassesThat.areNotAotGenerated())
          .should(JavaMethodsShould.haveLinesAtMost(60))
          .because(
              "you should avoid long method that contains too many lines of code, see more: https://refactoring.guru/smells/long-method");

  @ArchTest
  static final ArchRule methods_should_have_at_most_7_parameters =
      ArchRuleDefinition.methods()
          .that(JavaMethodsThat.areNotLombokBuilderDefaults())
          .and()
          .areDeclaredInClassesThat(JavaClassesThat.areNotLombokBuilders())
          .and()
          .areDeclaredInClassesThat(JavaClassesThat.areNotMapStructMapperImplementations())
          .and()
          .areDeclaredInClassesThat(JavaClassesThat.areNotAotGenerated())
          .should(JavaMethodsShould.haveParametersAtMost(7))
          .because(
              "it’s hard to understand such lists, which become contradictory and hard to use as they grow longer, see more: https://refactoring.guru/smells/long-parameter-list");

  @ArchTest
  static final ArchRule constructors_should_have_at_most_30_arguments =
      ArchRuleDefinition.constructors()
          .that()
          .areDeclaredInClassesThat(JavaClassesThat.areNotLombokBuilders())
          .and()
          .areDeclaredInClassesThat(JavaClassesThat.areNotMapStructMapperImplementations())
          .and()
          .areDeclaredInClassesThat(JavaClassesThat.areNotAotGenerated())
          .should(JavaConstructorsShould.haveArgumentsAtMost(30))
          .because(
              "classes shouldn't have too many responsibilities, see more: https://refactoring.guru/smells/large-class");
}
