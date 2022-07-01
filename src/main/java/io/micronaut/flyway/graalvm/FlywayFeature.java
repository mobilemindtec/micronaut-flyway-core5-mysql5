package io.micronaut.flyway.graalvm;


import com.oracle.svm.core.annotate.AutomaticFeature;
import com.oracle.svm.core.configure.ResourcesRegistry;
import io.micronaut.core.annotation.Internal;
import io.micronaut.core.graal.AutomaticFeatureUtils;
import org.graalvm.nativeimage.hosted.Feature;

import static io.micronaut.core.graal.AutomaticFeatureUtils.addResourcePatterns;

/**
 * A JDBC feature that configures JDBC drivers correctly for native image.
 *
 * @author graemerocher
 * @author Iván López
 * @since 2.2.1
 */
@AutomaticFeature
@Internal
final class FlywayFeature implements Feature {


    private ResourcesRegistry resourcesRegistry;

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        handleMySql(access);
    }

    private void handleMySql(BeforeAnalysisAccess access) {

        addResourcePatterns(
                "db/migrations/*.*sql$",
                "org/flywaydb/core/internal/version.txt",
                "org/flywaydb/core/internal/database/.*/*.*sql$"
        );

        AutomaticFeatureUtils.initializeAtRunTime(access, "flywaydb-core:org.flywaydb.core.internal.util.FeatureDetector");
    }

}
