package tw.com.softleader.example.training_20241129;

import com.tngtech.archunit.core.importer.ImportOption.OnlyIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import tw.com.softleader.kapok.test.archunit.GeneralRules;

@AnalyzeClasses(
    packagesOf = TestCodeArchitectureTests.class,
    importOptions = OnlyIncludeTests.class)
class TestCodeArchitectureTests {

  @ArchTest
  static final ArchRule tests_should_include_assertions =
      GeneralRules.testsShouldIncludeAssertions();
}
