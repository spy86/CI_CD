# Pipeline Jenkins Job to build simple NodeJS.

## Requirements
* [NodeJS Plugin](https://plugins.jenkins.io/nodejs/)
* [Repository with code](https://github.com/spy86/CI_CD/tree/main/example-nodejs-app/App)

## Steps to be performed

#### Install plugin (If it has not been installed previously)

* Click `Manage Jenkins`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build1.png?raw=true)

* Next click `Manage Plugins` 
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build2.png?raw=true)

* Next choose **available** tab, and in the search field, type **nodejs**
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build3.png?raw=true)

* As the last step, select the plugin and click **install without restart**
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build4.png?raw=true)

* After a while the plugin will be installed and can be used with jenkins.
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build5.png?raw=true)

Now we can configure NodeJs tool in Jenkins
* Again click `Manage Jenkins`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build6.png?raw=true)

* Next click `Global Tool Configuration` and find **NodeJS**. Click **Add NodeJS** ,complete it as below and click save
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build7.png?raw=true)

#### Configure Jenkins Job

* Click `Dashboard`
* Click `New Item`
* Enter an item name **ExampleNodejsBuildPipeline** choose `Pipeline job` and click **OK**
* In Pipeline section choose `Definition Pipeline Script from SCM`. As SCM we will choose git and specify the same repository https://github.com/spy86/CI_CD. Change script path to `example-nodejs-app/Pipeline-Job/Jenkinsfile`. Rest of the settings we leave as default, the entire configuration of our pipeline is defined in the Jenkinsfile.

#### Run Job

* Click **Build Now**, after a while the code should be built and the artifacts should see the contents of the `dist` directory.

* Example Console output
```
Started by user Admin
Obtained example-nodejs-app/Pipeline-Job/Jenkinsfile from git https://github.com/spy86/CI_CD.git
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/ExampleNodejsBuildPipeline
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Declarative: Checkout SCM)
[Pipeline] checkout
Selected Git installation does not exist. Using Default
The recommended git tool is: NONE
using credential github
Cloning the remote Git repository
Cloning repository https://github.com/spy86/CI_CD.git
 > git init /var/lib/jenkins/workspace/ExampleNodejsBuildPipeline # timeout=10
Fetching upstream changes from https://github.com/spy86/CI_CD.git
 > git --version # timeout=10
 > git --version # 'git version 2.25.1'
 > git fetch --tags --force --progress -- https://github.com/spy86/CI_CD.git +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git config remote.origin.url https://github.com/spy86/CI_CD.git # timeout=10
 > git config --add remote.origin.fetch +refs/heads/*:refs/remotes/origin/* # timeout=10
Avoid second fetch
 > git rev-parse refs/remotes/origin/main^{commit} # timeout=10
Checking out Revision 2c0391edd3cf6a884e1f03a51094c4c4b51ebe16 (refs/remotes/origin/main)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 2c0391edd3cf6a884e1f03a51094c4c4b51ebe16 # timeout=10
Commit message: "add jenkinsfile and update readme.md"
First time build. Skipping changelog.
[Pipeline] }
[Pipeline] // stage
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Declarative: Tool Install)
[Pipeline] tool
[Pipeline] envVarsForTool
[Pipeline] }
[Pipeline] // stage
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Checkout repository)
[Pipeline] tool
[Pipeline] envVarsForTool
[Pipeline] withEnv
[Pipeline] {
[Pipeline] cleanWs
[WS-CLEANUP] Deleting project workspace...
[WS-CLEANUP] Deferred wipeout is used...
[WS-CLEANUP] done
[Pipeline] git
Selected Git installation does not exist. Using Default
The recommended git tool is: NONE
using credential github
Cloning the remote Git repository
Cloning repository https://github.com/spy86/CI_CD
 > git init /var/lib/jenkins/workspace/ExampleNodejsBuildPipeline # timeout=10
Fetching upstream changes from https://github.com/spy86/CI_CD
 > git --version # timeout=10
 > git --version # 'git version 2.25.1'
using GIT_ASKPASS to set credentials 
 > git fetch --tags --force --progress -- https://github.com/spy86/CI_CD +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git config remote.origin.url https://github.com/spy86/CI_CD # timeout=10
 > git config --add remote.origin.fetch +refs/heads/*:refs/remotes/origin/* # timeout=10
Avoid second fetch
 > git rev-parse refs/remotes/origin/main^{commit} # timeout=10
Checking out Revision 2c0391edd3cf6a884e1f03a51094c4c4b51ebe16 (refs/remotes/origin/main)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 2c0391edd3cf6a884e1f03a51094c4c4b51ebe16 # timeout=10
 > git branch -a -v --no-abbrev # timeout=10
 > git checkout -b main 2c0391edd3cf6a884e1f03a51094c4c4b51ebe16 # timeout=10
Commit message: "add jenkinsfile and update readme.md"
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Build)
[Pipeline] tool
[Pipeline] envVarsForTool
[Pipeline] withEnv
[Pipeline] {
[Pipeline] sh
+ cd example-nodejs-app/App
+ npm -version
8.8.0
+ npm install
npm WARN old lockfile 
npm WARN old lockfile The package-lock.json file was created with an old version of npm,
npm WARN old lockfile so supplemental metadata must be fetched from the registry.
npm WARN old lockfile 
npm WARN old lockfile This is a one-time fix-up, please be patient...
npm WARN old lockfile 
npm WARN deprecated ini@1.3.5: Please update to ini >=1.3.6 to avoid a prototype pollution issue
npm WARN deprecated urix@0.1.0: Please see https://github.com/lydell/urix#deprecated
npm WARN deprecated resolve-url@0.2.1: https://github.com/lydell/resolve-url#deprecated
npm WARN deprecated source-map-url@0.4.0: See https://github.com/lydell/source-map-url#deprecated
npm WARN deprecated debug@3.2.6: Debug versions >=3.2.0 <3.2.7 || >=4 <4.3.1 have a low-severity ReDos regression when used in a Node.js environment. It is recommended you upgrade to 3.2.7 or 4.3.1. (https://github.com/visionmedia/debug/issues/797)
npm WARN deprecated chokidar@2.1.8: Chokidar 2 does not receive security updates since 2019. Upgrade to chokidar 3 with 15x fewer dependencies
npm WARN deprecated debug@4.1.1: Debug versions >=3.2.0 <3.2.7 || >=4 <4.3.1 have a low-severity ReDos regression when used in a Node.js environment. It is recommended you upgrade to 3.2.7 or 4.3.1. (https://github.com/visionmedia/debug/issues/797)
npm WARN deprecated debug@4.1.1: Debug versions >=3.2.0 <3.2.7 || >=4 <4.3.1 have a low-severity ReDos regression when used in a Node.js environment. It is recommended you upgrade to 3.2.7 or 4.3.1. (https://github.com/visionmedia/debug/issues/797)
npm WARN deprecated source-map-resolve@0.5.2: See https://github.com/lydell/source-map-resolve#deprecated
npm WARN deprecated mkdirp@0.5.1: Legacy versions of mkdirp are no longer supported. Please update to mkdirp 1.x. (Note that the API surface has changed to use Promises in 1.x.)
npm WARN deprecated bson@1.1.1: Fixed a critical issue with BSON serialization documented in CVE-2019-2391, see https://bit.ly/2KcpXdo for more details
npm WARN deprecated core-js@2.6.9: core-js@<3.4 is no longer maintained and not recommended for usage due to the number of issues. Because of the V8 engine whims, feature detection in old core-js versions could cause a slowdown up to 100x even if nothing is polyfilled. Please, upgrade your dependencies to the actual version of core-js.
npm WARN deprecated core-js@2.6.9: core-js@<3.4 is no longer maintained and not recommended for usage due to the number of issues. Because of the V8 engine whims, feature detection in old core-js versions could cause a slowdown up to 100x even if nothing is polyfilled. Please, upgrade your dependencies to the actual version of core-js.
npm WARN deprecated core-js@2.6.9: core-js@<3.4 is no longer maintained and not recommended for usage due to the number of issues. Because of the V8 engine whims, feature detection in old core-js versions could cause a slowdown up to 100x even if nothing is polyfilled. Please, upgrade your dependencies to the actual version of core-js.
npm WARN deprecated core-js@3.2.1: core-js@<3.4 is no longer maintained and not recommended for usage due to the number of issues. Because of the V8 engine whims, feature detection in old core-js versions could cause a slowdown up to 100x even if nothing is polyfilled. Please, upgrade your dependencies to the actual version of core-js.

added 452 packages, and audited 453 packages in 1m

19 vulnerabilities (5 moderate, 11 high, 3 critical)

To address issues that do not require attention, run:
  npm audit fix

To address all issues (including breaking changes), run:
  npm audit fix --force

Run `npm audit` for details.
+ npm run build

> node-babel-starter@1.0.0 build
> babel src --out-dir dist

Browserslist: caniuse-lite is outdated. Please run next command `npm update`
Successfully compiled 3 files with Babel.
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] archiveArtifacts
Archiving artifacts
[Pipeline] cleanWs
[WS-CLEANUP] Deleting project workspace...
[WS-CLEANUP] Deferred wipeout is used...
[WS-CLEANUP] done
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS
```
* Files in artifacts
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build14.png?raw=true)
