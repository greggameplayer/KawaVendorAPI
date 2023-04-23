package com.kawa;

import static com.tngtech.archunit.base.DescribedPredicate.alwaysTrue;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.belongToAnyOf;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packagesOf = KawaVendorApiApp.class, importOptions = DoNotIncludeTests.class)
class TechnicalStructureTest {

    // prettier-ignore
    @ArchTest
    static final ArchRule respectsTechnicalArchitectureLayers = layeredArchitecture()
        .layer("Config").definedBy("..config..")
        .layer("Client").definedBy("..client..")
        .layer("Management").definedBy("..management..")
        .layer("Web").definedBy("..web..")
        .optionalLayer("Service").definedBy("..service..")
        .layer("Security").definedBy("..security..")
        .layer("Persistence").definedBy("..repository..")
        .layer("Domain").definedBy("..domain..")
        .layer("Bean").definedBy("..bean..")
        .layer("RequestMapper").definedBy("..mapper.request..")
        .layer("ResponseMapper").definedBy("..mapper.response..")
        .layer("RequestDTO").definedBy("..dto.request..")
        .layer("ResponseDTO").definedBy("..dto.response..")

        .whereLayer("Config").mayNotBeAccessedByAnyLayer()
        .whereLayer("Client").mayNotBeAccessedByAnyLayer()
        .whereLayer("Web").mayOnlyBeAccessedByLayers("Config")
        .whereLayer("Service").mayOnlyBeAccessedByLayers("Web", "Config", "Management")
        .whereLayer("Security").mayOnlyBeAccessedByLayers("Config", "Client", "Service", "Web")
        .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service", "Security", "Web", "Config")
        .whereLayer("Domain").mayOnlyBeAccessedByLayers("Persistence", "Service", "Security", "Web", "Config")
        .whereLayer("Bean").mayOnlyBeAccessedByLayers("Persistence", "Service", "Security", "Web", "Config")
        .whereLayer("RequestMapper").mayOnlyBeAccessedByLayers("Service")
        .whereLayer("ResponseMapper").mayOnlyBeAccessedByLayers("Service")
        .whereLayer("RequestDTO").mayOnlyBeAccessedByLayers("Service", "RequestMapper", "Management", "Web")
        .whereLayer("ResponseDTO").mayOnlyBeAccessedByLayers("Service", "ResponseMapper", "Management", "Web")

        .ignoreDependency(belongToAnyOf(KawaVendorApiApp.class), alwaysTrue())
        .ignoreDependency(alwaysTrue(), belongToAnyOf(
            com.kawa.config.Constants.class,
            com.kawa.config.ApplicationProperties.class
        ));
}
