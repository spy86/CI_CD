# Jenkins full flow pipeline

## Prerequisites
* Docker engine (with swarm init)
* Sonarqube token

## How to use ?

#### Credentials
* Login into Sonarqube and generate access token

* Create credentials on Jenkins for dockerhub, go to `Manage Jenkins`-> `Manage Credentials`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins1.png?raw=true)

* Click store `Jenkins` -> `Global credentials (unrestricted)` and then `Add Credentials` on the left menu
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins2.png?raw=true)

* Choose `Secret text`, paste token generated in Sonarqube. At the end we save it with the name **sonarkey**


#### Configure Pipeline
* Click on New item and select pipeline and setup name `FullFlowPipeline`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins8.png?raw=true)

* Configure Pipeline
  * Choose Definition `Pipeline script from SCM`
  * Repository URL `https://github.com/spy86/CI_CD.git`
  * Credentials empty
  * Branch to build `main`
  * Script Path `jenkins-full-flow-pipeline/Jenkinsfile`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins9.png?raw=true)

Now we can run our pipeline. 

* Click `Build now`, after a while, our pipeline should be:
a) Checkout git repo
b) Run sonarqube analyze
c) Build images with docker
d) Deploy images into swarm
e) Verify if services is runing
---

#### Example pipeline console output
```
Started by user unknown or anonymous
Obtained jenkins-full-flow-pipeline/Jenkinsfile from git https://github.com/spy86/CI_CD.git
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/FullFlowPipeline
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Declarative: Checkout SCM)
[Pipeline] checkout
Selected Git installation does not exist. Using Default
The recommended git tool is: NONE
using credential 70150275-3513-4731-82e5-46abe898117e
 > git rev-parse --resolve-git-dir /var/lib/jenkins/workspace/FullFlowPipeline/.git # timeout=10
Fetching changes from the remote Git repository
 > git config remote.origin.url https://github.com/spy86/CI_CD.git # timeout=10
Fetching upstream changes from https://github.com/spy86/CI_CD.git
 > git --version # timeout=10
 > git --version # 'git version 2.25.1'
using GIT_ASKPASS to set credentials 
 > git fetch --tags --force --progress -- https://github.com/spy86/CI_CD.git +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git rev-parse refs/remotes/origin/main^{commit} # timeout=10
Checking out Revision b19c71cd07f129bdc1bca2f1adb7b2ce931d12d5 (refs/remotes/origin/main)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f b19c71cd07f129bdc1bca2f1adb7b2ce931d12d5 # timeout=10
Commit message: "update"
 > git rev-list --no-walk d2d8c339ba02d2d33bbcc2e022b59e08572ccc6a # timeout=10
[Pipeline] }
[Pipeline] // stage
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Clear)
[Pipeline] cleanWs
[WS-CLEANUP] Deleting project workspace...
[WS-CLEANUP] Deferred wipeout is used...
[WS-CLEANUP] done
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Checkout)
[Pipeline] git
Selected Git installation does not exist. Using Default
The recommended git tool is: NONE
using credential 70150275-3513-4731-82e5-46abe898117e
Cloning the remote Git repository
Cloning repository https://github.com/spy86/CI_CD.git
 > git init /var/lib/jenkins/workspace/FullFlowPipeline # timeout=10
Fetching upstream changes from https://github.com/spy86/CI_CD.git
 > git --version # timeout=10
 > git --version # 'git version 2.25.1'
 > git fetch --tags --force --progress -- https://github.com/spy86/CI_CD.git +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git config remote.origin.url https://github.com/spy86/CI_CD.git # timeout=10
 > git config --add remote.origin.fetch +refs/heads/*:refs/remotes/origin/* # timeout=10
Avoid second fetch
 > git rev-parse refs/remotes/origin/main^{commit} # timeout=10
Checking out Revision b19c71cd07f129bdc1bca2f1adb7b2ce931d12d5 (refs/remotes/origin/main)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f b19c71cd07f129bdc1bca2f1adb7b2ce931d12d5 # timeout=10
 > git branch -a -v --no-abbrev # timeout=10
 > git checkout -b main b19c71cd07f129bdc1bca2f1adb7b2ce931d12d5 # timeout=10
Commit message: "update"
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Run Sonarqube)
[Pipeline] dir
Running in /var/lib/jenkins/workspace/FullFlowPipeline/example-nodejs-app/App
[Pipeline] {
[Pipeline] sh
+ /opt/sonar-scanner/bin/sonar-scanner -Dsonar.projectKey=example-nodejs-app -Dsonar.sources=. -Dsonar.host.url=http://127.0.0.1:9000 -Dsonar.login=****************************
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
INFO: Load global settings (done) | time=116ms
INFO: Server id: 147B411E-AYEQeIcalSdIn8Qd2X-f
INFO: User cache: /var/lib/jenkins/.sonar/cache
INFO: Load/download plugins
INFO: Load plugins index
INFO: Load plugins index (done) | time=80ms
INFO: Load/download plugins (done) | time=214ms
INFO: Process project properties
INFO: Process project properties (done) | time=15ms
INFO: Execute project builders
INFO: Execute project builders (done) | time=4ms
INFO: Project key: example-nodejs-app
INFO: Base dir: /var/lib/jenkins/workspace/FullFlowPipeline/example-nodejs-app/App
INFO: Working dir: /var/lib/jenkins/workspace/FullFlowPipeline/example-nodejs-app/App/.scannerwork
INFO: Load project settings for component key: 'example-nodejs-app'
INFO: Load project settings for component key: 'example-nodejs-app' (done) | time=23ms
INFO: Auto-configuring with CI 'Jenkins'
INFO: Load quality profiles
INFO: Load quality profiles (done) | time=123ms
INFO: Load active rules
INFO: Load active rules (done) | time=1564ms
INFO: Load project repositories
INFO: Load project repositories (done) | time=24ms
INFO: Indexing files...
INFO: Project configuration:
INFO: 8 files indexed
INFO: 0 files ignored because of scm ignore settings
INFO: Quality profile for js: Sonar way
INFO: Quality profile for json: Sonar way
INFO: Quality profile for yaml: Sonar way
INFO: ------------- Run sensors on module example-nodejs-app
INFO: Load metrics repository
INFO: Load metrics repository (done) | time=47ms
INFO: Sensor JaCoCo XML Report Importer [jacoco]
INFO: 'sonar.coverage.jacoco.xmlReportPaths' is not defined. Using default locations: target/site/jacoco/jacoco.xml,target/site/jacoco-it/jacoco.xml,build/reports/jacoco/test/jacocoTestReport.xml
INFO: No report imported, no coverage information will be imported by JaCoCo XML Report Importer
INFO: Sensor JaCoCo XML Report Importer [jacoco] (done) | time=13ms
INFO: Sensor IaC CloudFormation Sensor [iac]
INFO: 0 source files to be analyzed
INFO: 0/0 source files have been analyzed
INFO: Sensor IaC CloudFormation Sensor [iac] (done) | time=223ms
INFO: Sensor JavaScript analysis [javascript]
INFO: 4 source files to be analyzed
INFO: 4/4 source files have been analyzed
INFO: Sensor JavaScript analysis [javascript] (done) | time=7752ms
INFO: Sensor TypeScript analysis [javascript]
INFO: No input files found for analysis
INFO: Sensor TypeScript analysis [javascript] (done) | time=1ms
INFO: Sensor CSS Rules [javascript]
INFO: No CSS, PHP, HTML or VueJS files are found in the project. CSS analysis is skipped.
INFO: Sensor CSS Rules [javascript] (done) | time=0ms
INFO: Sensor C# Project Type Information [csharp]
INFO: Sensor C# Project Type Information [csharp] (done) | time=1ms
INFO: Sensor C# Analysis Log [csharp]
INFO: Sensor C# Analysis Log [csharp] (done) | time=31ms
INFO: Sensor C# Properties [csharp]
INFO: Sensor C# Properties [csharp] (done) | time=4ms
INFO: Sensor HTML [web]
INFO: Sensor HTML [web] (done) | time=4ms
INFO: Sensor Text Sensor [text]
INFO: 7 source files to be analyzed
INFO: 7/7 source files have been analyzed
INFO: Sensor Text Sensor [text] (done) | time=99ms
INFO: Sensor VB.NET Project Type Information [vbnet]
INFO: Sensor VB.NET Project Type Information [vbnet] (done) | time=1ms
INFO: Sensor VB.NET Analysis Log [vbnet]
INFO: Sensor VB.NET Analysis Log [vbnet] (done) | time=43ms
INFO: Sensor VB.NET Properties [vbnet]
INFO: Sensor VB.NET Properties [vbnet] (done) | time=0ms
INFO: ------------- Run sensors on project
INFO: Sensor Zero Coverage Sensor
INFO: Sensor Zero Coverage Sensor (done) | time=19ms
INFO: CPD Executor Calculating CPD for 4 files
INFO: CPD Executor CPD calculation finished (done) | time=16ms
INFO: Analysis report generated in 154ms, dir size=120.2 kB
INFO: Analysis report compressed in 49ms, zip size=21.9 kB
INFO: Analysis report uploaded in 85ms
INFO: ANALYSIS SUCCESSFUL, you can find the results at: http://127.0.0.1:9000/dashboard?id=example-nodejs-app
INFO: Note that you will be able to access the updated dashboard once the server has processed the submitted analysis report
INFO: More about the report processing at http://127.0.0.1:9000/api/ce/task?id=AYEf-GSvlSdIn8Qd2dW9
INFO: Analysis total time: 21.316 s
INFO: ------------------------------------------------------------------------
INFO: EXECUTION SUCCESS
INFO: ------------------------------------------------------------------------
INFO: Total time: 23.919s
INFO: Final Memory: 16M/69M
INFO: ------------------------------------------------------------------------
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Build Image and deploy)
[Pipeline] dir
Running in /var/lib/jenkins/workspace/FullFlowPipeline/example-nodejs-app/App
[Pipeline] {
[Pipeline] sh
+ docker-compose up --force-recreate --build -d
The Docker Engine you're using is running in swarm mode.

Compose does not use swarm mode to deploy services to multiple nodes in a swarm. All containers will be scheduled on the current node.

To deploy your application across the swarm, use `docker stack deploy`.

Building app
Sending build context to Docker daemon  185.9kB

Step 1/15 : FROM node:10.15.2-alpine AS build
 ---> 072459fe4d8a
Step 2/15 : WORKDIR /usr/src/app
 ---> Using cache
 ---> bd57602a00d8
Step 3/15 : COPY package.json ./
 ---> Using cache
 ---> 8d9508f2e96b
Step 4/15 : COPY .babelrc ./
 ---> Using cache
 ---> 825bc984613b
Step 5/15 : RUN npm install
 ---> Using cache
 ---> c46753f55aa6
Step 6/15 : COPY ./src ./src
 ---> 85ef621cc011
Step 7/15 : RUN npm run build
 ---> Running in 6c2877ccf2d8

> node-babel-starter@1.0.0 build /usr/src/app
> babel src --out-dir dist

Successfully compiled 3 files with Babel (662ms).
Removing intermediate container 6c2877ccf2d8
 ---> a755699adf26
Step 8/15 : FROM node:10.15.2-alpine
 ---> 072459fe4d8a
Step 9/15 : WORKDIR /usr/src/app
 ---> Using cache
 ---> bd57602a00d8
Step 10/15 : COPY package.json ./
 ---> Using cache
 ---> 8d9508f2e96b
Step 11/15 : COPY .babelrc ./
 ---> Using cache
 ---> 825bc984613b
Step 12/15 : RUN npm install
 ---> Using cache
 ---> c46753f55aa6
Step 13/15 : COPY --from=build /usr/src/app/dist ./dist
 ---> Using cache
 ---> 4257ea06fa7e
Step 14/15 : EXPOSE 4002
 ---> Using cache
 ---> d577471f5935
Step 15/15 : CMD npm start
 ---> Using cache
 ---> 149e6a7be510
Successfully built 149e6a7be510
Successfully tagged app_app:latest
Recreating mongo ... 
Recreating mongo ... done
Recreating app   ... 
Recreating app   ... done
[Pipeline] sh
+ docker image prune -f
Deleted Images:
deleted: sha256:a755699adf26ad920da32def18bb0a836d1aeb826224e063e5bc24bd83557dd2
deleted: sha256:e93624b17bb9e2153e6dae17b105b253825eecf4a7b7bef55d3f94c67ef27b9a
deleted: sha256:85ef621cc0118ff18e3f7487f51b14d02d0279670962660d44fc50fdbe3b418c
deleted: sha256:f339f46abd172b4a45c42b0dc38973618dfa27e28af253456d9d49ec3de54297

Total reclaimed space: 7.926kB
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Check Service)
[Pipeline] dir
Running in /var/lib/jenkins/workspace/FullFlowPipeline/example-nodejs-app/App
[Pipeline] {
[Pipeline] sh
+ docker-compose ps
Name              Command             State                      Ports                    
------------------------------------------------------------------------------------------
app     /bin/sh -c npm start          Up      0.0.0.0:4002->4002/tcp,:::4002->4002/tcp    
mongo   docker-entrypoint.sh mongod   Up      0.0.0.0:27017->27017/tcp,:::27017->27017/tcp
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS
```

![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins10.png?raw=true)

![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins11.png?raw=true)