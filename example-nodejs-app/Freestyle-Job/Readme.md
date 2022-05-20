# Freesyte Jenkins Job to build simple NodeJS.

## Requirements
* [NodeJS Plugin](https://plugins.jenkins.io/nodejs/)
* [Repository with code](https://github.com/spy86/CI_CD/tree/main/example-nodejs-app/App)

## Steps to be performed

#### Install plugin

* Click `Manage Jenkins`
![alt text](/images/build1.png "")

* Next click `Manage Plugins` 
![alt text](/images/build2.png "")

* Next choose **available** tab, and in the search field, type **nodejs**
![alt text](/images/build3.png "")

* As the last step, select the plugin and click **install without restart**
![alt text](/images/build4.png "")

* After a while the plugin will be installed and can be used with jenkins.
![alt text](/images/build5.png "")

Now we can configure NodeJs tool in Jenkins
* Again click `Manage Jenkins`
![alt text](/images/build6.png "")

* Next click `Global Tool Configuration` and find **NodeJS**. Click **Add NodeJS** ,complete it as below and click save
![alt text](/images/build7.png "")


#### Configure Jenkins Job

* Click `Dashboard`
* Click `New Item`
* Enter an item name **ExampleNodejsBuild** choose `Freestyle project` and click **OK**
![alt text](/images/build8.png "")
* In Source Code Management change to `git` and add repository https://github.com/spy86/CI_CD and change branch to `main`.
![alt text](/images/build9.png "")
* In Build Environment enable `Provide Node & npm bin/ folder to PATH` and choose **nodejs**
![alt text](/images/build10.png "")
* In Build click `Add build step` next choose **Execute shell** and paste the following commands inside
![alt text](/images/build11.png "")
```
cd example-nodejs-app/App
npm -version
npm install
npm run build
```
* In Post-build Actions add post-build action and choose **Archive the artifacts**. Path to archive should be set like below.
![alt text](/images/build12.png "")

Path to set is `example-nodejs-app/App/dist/*.js`. If we setup all we can click **Save** and at this point we have a fully configured job that we can run.

#### Run Job

* Click **Build Now**, after a while the code should be built and the artifacts should see the contents of the `dist` directory.

* Example Console output
```
Started by user Admin
Running as SYSTEM
Building in workspace /var/lib/jenkins/workspace/ExampleNodejsBuild
The recommended git tool is: NONE
using credential github
 > git rev-parse --resolve-git-dir /var/lib/jenkins/workspace/ExampleNodejsBuild/.git # timeout=10
Fetching changes from the remote Git repository
 > git config remote.origin.url https://github.com/spy86/CI_CD # timeout=10
Fetching upstream changes from https://github.com/spy86/CI_CD
 > git --version # timeout=10
 > git --version # 'git version 2.25.1'
 > git fetch --tags --force --progress -- https://github.com/spy86/CI_CD +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git rev-parse refs/remotes/origin/main^{commit} # timeout=10
Checking out Revision cbd83fbd28428785707417b151c22bf20b2dda08 (refs/remotes/origin/main)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f cbd83fbd28428785707417b151c22bf20b2dda08 # timeout=10
Commit message: "update"
First time build. Skipping changelog.
[ExampleNodejsBuild] $ /bin/sh -xe /tmp/jenkins15518591846278400595.sh
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

up to date, audited 453 packages in 5s

18 vulnerabilities (5 moderate, 10 high, 3 critical)

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
Archiving artifacts
Finished: SUCCESS
```

* Files in artifacts
![alt text](/images/build13.png "")