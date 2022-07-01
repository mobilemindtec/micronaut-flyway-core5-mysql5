package io.micronaut.flyway.graalvm;


import com.oracle.svm.core.annotate.AutomaticFeature;
import com.oracle.svm.core.configure.ResourcesRegistry;
import io.micronaut.core.annotation.Internal;
import io.micronaut.core.graal.AutomaticFeatureUtils;
import org.graalvm.nativeimage.hosted.Feature;

import java.util.Arrays;
import java.util.HashSet;

import static io.micronaut.core.graal.AutomaticFeatureUtils.*;

/**
 * A JDBC feature that configures JDBC drivers correctly for native image.
 *
 * @author graemerocher
 * @author Iván López
 * @since 2.2.1
 */
@AutomaticFeature
@Internal
final class Mysql5Feature implements Feature {

    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

    private ResourcesRegistry resourcesRegistry;

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        handleMySql(access);
    }

    private void handleMySql(BeforeAnalysisAccess access) {
        Class<?> mysqlDriver = access.findClassByName(MYSQL_DRIVER);
        if (mysqlDriver != null) {
            new HashSet<>(Arrays.asList(
                "com.mysql.jdbc.NonRegisteringDriver",
                "com.mysql.jdbc.MysqlIO",
                "com.mysql.jdbc.ConnectionImpl",
                "com.mysql.jdbc.Util",
                "com.mysql.jdbc.ReflectiveStatementInterceptorAdapter",
                "com.mysql.jdbc.ServerPreparedStatement",
                "com.mysql.jdbc.ResultSetImpl",
                "com.mysql.jdbc.JDBC4ResultSet",
                "com.mysql.jdbc.log.LogFactory",
                "com.mysql.jdbc.PreparedStatement",
                "com.mysql.jdbc.ConnectionPropertiesImpl",
                "com.mysql.jdbc.exceptions.CommunicationsException",
                "com.mysql.jdbc.exceptions.MySQLDataException",
                "com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException",
                "com.mysql.jdbc.exceptions.MySQLInvalidAuthorizationSpecException",
                "com.mysql.jdbc.exceptions.MySQLNonTransientConnectionException",
                "com.mysql.jdbc.exceptions.MySQLNonTransientException",
                "com.mysql.jdbc.exceptions.MySQLQueryInterruptedException",
                "com.mysql.jdbc.exceptions.MySQLSyntaxErrorException",
                "com.mysql.jdbc.exceptions.MySQLTimeoutException",
                "com.mysql.jdbc.exceptions.MySQLTransactionRollbackException",
                "com.mysql.jdbc.exceptions.MySQLTransientConnectionException",
                "com.mysql.jdbc.exceptions.MySQLTransientException",
                "com.mysql.jdbc.exceptions.jdbc4.CommunicationsException",
                "com.mysql.jdbc.exceptions.jdbc4.MySQLDataException",
                "com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException",
                "com.mysql.jdbc.exceptions.jdbc4.MySQLInvalidAuthorizationSpecException",
                "com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException",
                "com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientException",
                "com.mysql.jdbc.exceptions.jdbc4.MySQLQueryInterruptedException",
                "com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException",
                "com.mysql.jdbc.exceptions.jdbc4.MySQLTimeoutException",
                "com.mysql.jdbc.exceptions.jdbc4.MySQLTransactionRollbackException",
                "com.mysql.jdbc.exceptions.jdbc4.MySQLTransientConnectionException",
                "com.mysql.jdbc.exceptions.jdbc4.MySQLTransientException",
                "com.mysql.fabric.jdbc.JDBC4FabricMySQLConnectionProxy",
                "com.mysql.jdbc.JDBC42CallableStatement",
                "com.mysql.jdbc.JDBC4CallableStatement",
                "com.mysql.jdbc.JDBC4Connection",
                "com.mysql.jdbc.JDBC4DatabaseMetaData",
                "com.mysql.jdbc.MySQLConnection",
                "com.mysql.jdbc.JDBC4DatabaseMetaDataUsingInfoSchema",
                "com.mysql.jdbc.JDBC4MySQLConnection",
                "com.mysql.jdbc.JDBC4LoadBalancedMySQLConnection",
                "com.mysql.jdbc.JDBC4MySQLConnection",
                "com.mysql.jdbc.JDBC4MultiHostMySQLConnection",
                "com.mysql.jdbc.JDBC4PreparedStatement",
                "com.mysql.jdbc.JDBC4MySQLConnection",
                "com.mysql.jdbc.JDBC4ReplicationMySQLConnection",
                "com.mysql.jdbc.JDBC42ResultSet",
                "com.mysql.jdbc.JDBC4ResultSet",
                "com.mysql.jdbc.JDBC42UpdatableResultSet",
                "com.mysql.jdbc.JDBC4UpdatableResultSet",
                "com.mysql.jdbc.JDBC42ServerPreparedStatement",
                "com.mysql.jdbc.JDBC4ServerPreparedStatement",
                "com.mysql.jdbc.JDBC42PreparedStatement"
            )).forEach(name -> {
                AutomaticFeatureUtils.registerClassForRuntimeReflection(access, name);
                AutomaticFeatureUtils.registerConstructorsForRuntimeReflection(access, name);
            });
            registerFieldsAndMethodsWithReflectiveAccess(access, MYSQL_DRIVER);

            registerAllForRuntimeReflection(access, "com.mysql.jdbc.log.StandardLogger");

            registerFieldsAndMethodsWithReflectiveAccess(access, "com.mysql.jdbc.StandardSocketFactory");
            registerFieldsAndMethodsWithReflectiveAccess(access, "com.mysql.jdbc.AbandonedConnectionCleanupThread");

            addResourcePatterns(
                "META-INF/services/java.sql.Driver",
                "com/mysql/jdbc/TimeZoneMapping.properties",
                "com/mysql/jdbc/LocalizedErrorMessages.properties",
                "com/mysql/jdbc/configs/solarisMaxPerformance.properties",
                "com/mysql/jdbc/configs/coldFusion.properties",
                "com/mysql/jdbc/configs/5-0-Compat.properties",
                "com/mysql/jdbc/configs/3-0-Compat.properties",
                "com/mysql/jdbc/configs/clusterBase.properties",
                "com/mysql/jdbc/configs/fullDebug.properties",
                "com/mysql/jdbc/configs/maxPerformance.properties",
                "com/mysql/jdbc/Charsets.properties"
            );
            addResourceBundles("com.mysql.jdbc.LocalizedErrorMessages");

            initializeAtRunTime(access, "java.sql.DriverManager");
        }
    }

}
