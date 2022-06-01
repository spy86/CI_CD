# Jenkins sonarqube

## Prerequisites
* Docker engine (with swarm init)
* Sonarqube

## How to use ?

#### Configure Pipeline
* Click on New item and select pipeline and setup name `SonarqubeIntegration`
![alt text](/images/Jenkins12.png "")

* Configure Pipeline
  * Choose Definition `Pipeline script from SCM`
  * Repository URL `https://github.com/spy86/CI_CD.git`
  * Credentials empty
  * Branch to build `main`
  * Script Path `jenkins-integrations/sonarqube/Jenkinsfile`
![alt text](/images/Jenkins13.png "")

Now we can run our pipeline. 

* Click `Build now`, after a while, our pipeline should be:
a) Checkout git repo
b) Build maven
c) Run sonarqube analyze

---
#### Example pipeline console output
```
Started by user unknown or anonymous
Obtained jenkins-integrations/sonarqube/Jenkinsfile from git https://github.com/spy86/CI_CD.git
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/SonarqubeIntegration
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Clear)
[Pipeline] cleanWs
[WS-CLEANUP] Deleting project workspace...
[WS-CLEANUP] Deferred wipeout is used...
[WS-CLEANUP] done
[Pipeline] }
[Pipeline] // stage
[Pipeline] isUnix
[Pipeline] withEnv
[Pipeline] {
[Pipeline] sh
+ docker pull gradle:jdk8-alpine
jdk8-alpine: Pulling from library/gradle
Digest: sha256:1cb081cfa6d25d718f6276c3df6474b332c06278d8bb64bcb0fc1243b1e4491e
Status: Image is up to date for gradle:jdk8-alpine
docker.io/library/gradle:jdk8-alpine
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] stage
[Pipeline] { (Checkout repo)
[Pipeline] git
The recommended git tool is: NONE
No credentials specified
Cloning the remote Git repository
Cloning repository https://github.com/wardviaene/gs-gradle
 > git init /var/lib/jenkins/workspace/SonarqubeIntegration # timeout=10
Fetching upstream changes from https://github.com/wardviaene/gs-gradle
 > git --version # timeout=10
 > git --version # 'git version 2.25.1'
 > git fetch --tags --force --progress -- https://github.com/wardviaene/gs-gradle +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git config remote.origin.url https://github.com/wardviaene/gs-gradle # timeout=10
 > git config --add remote.origin.fetch +refs/heads/*:refs/remotes/origin/* # timeout=10
Avoid second fetch
 > git rev-parse refs/remotes/origin/master^{commit} # timeout=10
Checking out Revision 34e902769b0c9ff6ca53945ab130a9148d31dc4e (refs/remotes/origin/master)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 34e902769b0c9ff6ca53945ab130a9148d31dc4e # timeout=10
 > git branch -a -v --no-abbrev # timeout=10
 > git checkout -b master 34e902769b0c9ff6ca53945ab130a9148d31dc4e # timeout=10
Commit message: "gradle 7 breaking changes"
First time build. Skipping changelog.
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Build mvn)
[Pipeline] isUnix
[Pipeline] withEnv
[Pipeline] {
[Pipeline] sh
+ docker inspect -f . gradle:jdk8-alpine
.
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] withDockerContainer
Jenkins does not seem to be running inside a container
$ docker run -t -d -u 111:117 -v /var/lib/jenkins/.gradle:/home/gradle/.gradle -w /var/lib/jenkins/workspace/SonarqubeIntegration -v /var/lib/jenkins/workspace/SonarqubeIntegration:/var/lib/jenkins/workspace/SonarqubeIntegration:rw,z -v /var/lib/jenkins/workspace/SonarqubeIntegration@tmp:/var/lib/jenkins/workspace/SonarqubeIntegration@tmp:rw,z -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** -e ******** gradle:jdk8-alpine cat
$ docker top a6b8283f448c63640ae633d4f80b8bcc76720fea91431b9eadb290137c7991f2 -eo pid,comm
[Pipeline] {
[Pipeline] sh
+ cd complete
+ /opt/gradle/bin/gradle build

Welcome to Gradle 5.4.1!

Here are the highlights of this release:
 - Run builds with JDK12
 - New API for Incremental Tasks
 - Updates to native projects, including Swift 5 support

For more details see https://docs.gradle.org/5.4.1/release-notes.html

Starting a Gradle Daemon (subsequent builds will be faster)
> Task :compileJava
> Task :processResources NO-SOURCE
> Task :classes
> Task :jar
> Task :startScripts
> Task :distTar
> Task :distZip
> Task :assemble
> Task :compileTestJava
> Task :processTestResources NO-SOURCE
> Task :testClasses
> Task :test
> Task :check
> Task :build

BUILD SUCCESSFUL in 18s
7 actionable tasks: 7 executed
[Pipeline] }
$ docker stop --time=1 a6b8283f448c63640ae633d4f80b8bcc76720fea91431b9eadb290137c7991f2
$ docker rm -f a6b8283f448c63640ae633d4f80b8bcc76720fea91431b9eadb290137c7991f2
[Pipeline] // withDockerContainer
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Sonarqube scanner)
[Pipeline] sh
+ /opt/sonar-scanner/bin/sonar-scanner -e -Dsonar.host.url=http://127.0.0.1:9000 -Dsonar.login=08f65dfd2e9957f27ae3d5aed4714651c5fb6f88 -Dsonar.projectName=gs-gradle -Dsonar.projectVersion=3 -Dsonar.projectKey=GS -Dsonar.sources=complete/src/main/ -Dsonar.tests=complete/src/test/ -Dsonar.language=java -Dsonar.java.binaries=.
INFO: Option -e/--errors is no longer supported and will be ignored
INFO: Scanner configuration file: /opt/sonar-scanner/conf/sonar-scanner.properties
INFO: Project root configuration file: NONE
INFO: SonarScanner 4.7.0.2747
INFO: Java 11.0.14.1 Eclipse Adoptium (64-bit)
INFO: Linux 5.4.0-42-generic amd64
INFO: User cache: /var/lib/jenkins/.sonar/cache
INFO: Scanner configuration file: /opt/sonar-scanner/conf/sonar-scanner.properties
INFO: Project root configuration file: NONE
INFO: Analyzing on SonarQube server 9.4.0
INFO: Default locale: "en_US", source code encoding: "UTF-8"
INFO: Load global settings
INFO: Load global settings (done) | time=134ms
INFO: Server id: 147B411E-AYEQeIcalSdIn8Qd2X-f
INFO: User cache: /var/lib/jenkins/.sonar/cache
INFO: Load/download plugins
INFO: Load plugins index
INFO: Load plugins index (done) | time=118ms
INFO: Load/download plugins (done) | time=313ms
INFO: Process project properties
INFO: Process project properties (done) | time=15ms
INFO: Execute project builders
INFO: Execute project builders (done) | time=1ms
INFO: Project key: GS
INFO: Base dir: /var/lib/jenkins/workspace/SonarqubeIntegration
INFO: Working dir: /var/lib/jenkins/workspace/SonarqubeIntegration/.scannerwork
INFO: Load project settings for component key: 'GS'
INFO: Load project settings for component key: 'GS' (done) | time=31ms
INFO: Auto-configuring with CI 'Jenkins'
INFO: Load quality profiles
INFO: Load quality profiles (done) | time=91ms
INFO: Load active rules
INFO: Load active rules (done) | time=1721ms
INFO: Load project repositories
INFO: Load project repositories (done) | time=20ms
INFO: Indexing files...
INFO: Project configuration:
INFO: 3 files indexed
INFO: 0 files ignored because of scm ignore settings
INFO: Quality profile for java: Sonar way
INFO: ------------- Run sensors on module gs-gradle
INFO: Load metrics repository
INFO: Load metrics repository (done) | time=46ms
INFO: Sensor JavaSensor [java]
INFO: Configured Java source version (sonar.java.source): none
INFO: JavaClasspath initialization
INFO: JavaClasspath initialization (done) | time=7ms
INFO: JavaTestClasspath initialization
INFO: JavaTestClasspath initialization (done) | time=1ms
INFO: Using ECJ batch to parse 2 Main java source files with batch size 49 KB.
INFO: Starting batch processing.
INFO: Cannot determine whether the context allows skipping unchanged files: canSkipUnchangedFiles not part of sonar-plugin-api. Not skipping. org.sonar.scanner.sensor.ModuleSensorContext.canSkipUnchangedFiles()
INFO: 100% analyzed
INFO: Batch processing: Done.
INFO: Did not optimize analysis for any files, performed a full analysis for all 2 files.
WARN: Dependencies/libraries were not provided for analysis of SOURCE files. The 'sonar.java.libraries' property is empty. Verify your configuration, as you might end up with less precise results.
WARN: Dependencies/libraries were not provided for analysis of TEST files. The 'sonar.java.test.libraries' property is empty. Verify your configuration, as you might end up with less precise results.
WARN: Unresolved imports/types have been detected during analysis. Enable DEBUG mode to see them.
INFO: Using ECJ batch to parse 1 Test java source files with batch size 49 KB.
INFO: Starting batch processing.
INFO: 100% analyzed
INFO: Batch processing: Done.
INFO: Did not optimize analysis for any files, performed a full analysis for all 1 files.
WARN: Unresolved imports/types have been detected during analysis. Enable DEBUG mode to see them.
WARN: Use of preview features have been detected during analysis. Enable DEBUG mode to see them.
INFO: No "Generated" source files to scan.
INFO: Sensor JavaSensor [java] (done) | time=2626ms
INFO: Sensor JaCoCo XML Report Importer [jacoco]
INFO: 'sonar.coverage.jacoco.xmlReportPaths' is not defined. Using default locations: target/site/jacoco/jacoco.xml,target/site/jacoco-it/jacoco.xml,build/reports/jacoco/test/jacocoTestReport.xml
INFO: No report imported, no coverage information will be imported by JaCoCo XML Report Importer
INFO: Sensor JaCoCo XML Report Importer [jacoco] (done) | time=12ms
INFO: Sensor CSS Rules [javascript]
INFO: No CSS, PHP, HTML or VueJS files are found in the project. CSS analysis is skipped.
INFO: Sensor CSS Rules [javascript] (done) | time=1ms
INFO: Sensor C# Project Type Information [csharp]
INFO: Sensor C# Project Type Information [csharp] (done) | time=1ms
INFO: Sensor C# Analysis Log [csharp]
INFO: Sensor C# Analysis Log [csharp] (done) | time=34ms
INFO: Sensor C# Properties [csharp]
INFO: Sensor C# Properties [csharp] (done) | time=1ms
INFO: Sensor SurefireSensor [java]
INFO: parsing [/var/lib/jenkins/workspace/SonarqubeIntegration/target/surefire-reports]
INFO: Sensor SurefireSensor [java] (done) | time=2ms
INFO: Sensor HTML [web]
INFO: Sensor HTML [web] (done) | time=7ms
INFO: Sensor Text Sensor [text]
INFO: 3 source files to be analyzed
INFO: 3/3 source files have been analyzed
INFO: Sensor Text Sensor [text] (done) | time=13ms
INFO: Sensor VB.NET Project Type Information [vbnet]
INFO: Sensor VB.NET Project Type Information [vbnet] (done) | time=0ms
INFO: Sensor VB.NET Analysis Log [vbnet]
INFO: Sensor VB.NET Analysis Log [vbnet] (done) | time=35ms
INFO: Sensor VB.NET Properties [vbnet]
INFO: Sensor VB.NET Properties [vbnet] (done) | time=1ms
INFO: ------------- Run sensors on project
INFO: Sensor Zero Coverage Sensor
INFO: Sensor Zero Coverage Sensor (done) | time=21ms
INFO: Sensor Java CPD Block Indexer
INFO: Sensor Java CPD Block Indexer (done) | time=30ms
INFO: CPD Executor 2 files had no CPD blocks
INFO: CPD Executor Calculating CPD for 0 files
INFO: CPD Executor CPD calculation finished (done) | time=0ms
INFO: Analysis report generated in 175ms, dir size=115.7 kB
INFO: Analysis report compressed in 37ms, zip size=18.0 kB
INFO: Analysis report uploaded in 59ms
INFO: ANALYSIS SUCCESSFUL, you can find the results at: http://127.0.0.1:9000/dashboard?id=GS
INFO: Note that you will be able to access the updated dashboard once the server has processed the submitted analysis report
INFO: More about the report processing at http://127.0.0.1:9000/api/ce/task?id=AYEgN2FLlSdIn8Qd2dXd
INFO: Analysis total time: 10.703 s
INFO: ------------------------------------------------------------------------
INFO: EXECUTION SUCCESS
INFO: ------------------------------------------------------------------------
INFO: Total time: 13.760s
INFO: Final Memory: 19M/69M
INFO: ------------------------------------------------------------------------
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS
```
![alt text](/images/Jenkins14.png "")

![alt text](/images/Jenkins15.png "")