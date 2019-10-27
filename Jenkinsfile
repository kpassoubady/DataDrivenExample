node('win') {
    def mvnHome
    def javaHome
    stage('Preparation') {
        git 'https://github.com/kpassoubady/DataDrivenExample.git'
        mvnHome = tool 'MVN-WIN'
        javaHome = tool 'JDK11-WIN'
    }
    stage('Build') {
        // Run the maven build
        withEnv([
                "MVN_HOME=$mvnHome",
                "JAVA_HOME=$javaHome",
                "PATH=$javaHome\bin:$PATH"
        ]) {
            bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean/)
            sleep 1
            parallel allure: {
                bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore test -P allure-example/)
            }, extent: {
                bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore test -P extent-example/)
            }, pdf: {
                bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore test -P pdf-example/)
            }, reportng: {
                bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore test -P reportng-example/)
            }

        }
    }
    stage('Publish') {
        parallel junit: {
            junit '**/target/surefire-reports/TEST-*.xml'
        }, allure: {
            allure jdk: '', report: 'target/allure-report', results: [[path: 'target/allure-results']]
        }

        publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'target/', reportFiles: 'surefire-reports/emailable-report.html, surefire-reports/index.html, extentreport/report.html,allure-report/index.html', reportName: 'Access Reports', reportTitles: 'TestNG Emailable Report, TestNG Outline Report, Extent HTML Report, Allure Report'])

        archiveArtifacts allowEmptyArchive: true, artifacts: 'target/*.jar', fingerprint: true, onlyIfSuccessful: true

    }
    stage('Archive') {
        archiveArtifacts allowEmptyArchive: true, artifacts: 'target/*.jar', fingerprint: true, onlyIfSuccessful: true
    }

}