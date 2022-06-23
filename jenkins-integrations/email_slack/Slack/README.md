# Jenkins Slack

## Prerequisites
* Docker engine
* Dockerhub account (https://hub.docker.com/signup)
* Jenkins plugin Docker Pipeline
* Jenkins plugin Slack Notification (https://plugins.jenkins.io/slack/)
* Slack account

## How to use ?

#### Dockerhub Credentials
* Create free account in dockerhub
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/dockerhub.png?raw=true)

* Create credentials on Jenkins for dockerhub, go to `Manage Jenkins`-> `Manage Credentials`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins1.png?raw=true)

* Click store `Jenkins` -> `Global credentials (unrestricted)` and then `Add Credentials` on the left menu
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins2.png?raw=true)

* Choose `Username and Password`, paste login and password for dockerhub as below. At the end we save it with the name "dockerhub"
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins3.png?raw=true)

---
#### Slack Setup Instructions

##### Slack
* Create account in https://slack.com/
* Create workspace 
* After login to slack go to **Apps** and type **Jenkins CI** in Search.
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins18.png?raw=true)
* Click **Add to slack** and follow the instructions

##### Jenkins
* In your Jenkins dashboard, click on `Manage Jenkins` from the left navigation.
* Click on Manage Plugins and search for `Slack Notification` in the Available tab. Click the checkbox and install the plugin.
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins16.png?raw=true)
* After it's installed, click on `Manage Jenkins` again in the left navigation, and then go to `Configure System`. Find the `Slack` section and add the following values:
  * Workspace
  * Credential  

  The other fields are optional. You can click on the question mark icons next to them for more information. Press Save when you're done.

![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins17.png?raw=true)

#### Configure Pipeline
* Click on New item and select pipeline and setup name `SlackIntegration`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins19.png?raw=true)

* Configure Pipeline
  * Enable `This project is parameterized` and setup string parametr with name **IMAGE_NAME**
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins20.png?raw=true)
  * Choose Definition `Pipeline script from SCM`
  * Repository URL `https://github.com/spy86/CI_CD.git`
  * Credentials empty
  * Branch to build `main`
  * Script Path `jenkins-integrations/email_slack/Slack/Jenkinsfile`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins21.png?raw=true)


Now we can run our pipeline. 

* Click `Build with Parameters` provide image name and click **Build**
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins22.png?raw=true)

After a while, our pipeline should build a docker image and then send notification on Slack channel.

---
#### Example pipeline console output

```
Started by user unknown or anonymous
Obtained jenkins-integrations/email_slack/Slack/Jenkinsfile from git https://github.com/spy86/CI_CD.git
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/SlackIntegration
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Declarative: Checkout SCM)
[Pipeline] checkout
Selected Git installation does not exist. Using Default
The recommended git tool is: NONE
using credential 70150275-3513-4731-82e5-46abe898117e
 > git rev-parse --resolve-git-dir /var/lib/jenkins/workspace/SlackIntegration/.git # timeout=10
Fetching changes from the remote Git repository
 > git config remote.origin.url https://github.com/spy86/CI_CD.git # timeout=10
Fetching upstream changes from https://github.com/spy86/CI_CD.git
 > git --version # timeout=10
 > git --version # 'git version 2.25.1' 
 > git fetch --tags --force --progress -- https://github.com/spy86/CI_CD.git +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git rev-parse refs/remotes/origin/main^{commit} # timeout=10
Checking out Revision 33fe74ba8bc3a1d06614c4f09829622a19ae4b47 (refs/remotes/origin/main)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 33fe74ba8bc3a1d06614c4f09829622a19ae4b47 # timeout=10
Commit message: "update"
First time build. Skipping changelog.
[Pipeline] }
[Pipeline] // stage
[Pipeline] withEnv
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Checkout repository)
[Pipeline] git
The recommended git tool is: NONE
No credentials specified
 > git rev-parse --resolve-git-dir /var/lib/jenkins/workspace/SlackIntegration/.git # timeout=10
Fetching changes from the remote Git repository
 > git config remote.origin.url https://github.com/spy86/docker-dart.git # timeout=10
Fetching upstream changes from https://github.com/spy86/docker-dart.git
 > git --version # timeout=10
 > git --version # 'git version 2.25.1'
 > git fetch --tags --force --progress -- https://github.com/spy86/docker-dart.git +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git rev-parse refs/remotes/origin/main^{commit} # timeout=10
Checking out Revision 50684617b5b19c6b17dc8810135e3884aa930b5d (refs/remotes/origin/main)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 50684617b5b19c6b17dc8810135e3884aa930b5d # timeout=10
 > git branch -a -v --no-abbrev # timeout=10
 > git branch -D main # timeout=10
 > git checkout -b main 50684617b5b19c6b17dc8810135e3884aa930b5d # timeout=10
Commit message: "Update azure-pipelines.yml for Azure Pipelines"
First time build. Skipping changelog.
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Building docker image)
[Pipeline] script
[Pipeline] {
[Pipeline] isUnix
[Pipeline] withEnv
[Pipeline] {
[Pipeline] sh
+ docker build -t testimage .
Sending build context to Docker daemon  15.44MB

Step 1/5 : FROM spy86/ubuntu:latest
 ---> bead38211656
Step 2/5 : RUN   mkdir -p /tmp/dart &&   cd /tmp/dart &&   curl -O https://storage.googleapis.com/dart-archive/channels/stable/release/2.16.2/linux_packages/dart_2.16.2-1_amd64.deb &&   dpkg -i dart_2.16.2-1_amd64.deb &&   rm -fr /tmp/dart
 ---> Using cache
 ---> 09099075ee05
Step 3/5 : ENV PATH /usr/lib/dart/bin:$PATH
 ---> Using cache
 ---> 265ce2258551
Step 4/5 : WORKDIR /data
 ---> Using cache
 ---> 915ac187ea55
Step 5/5 : CMD ["bash"]
 ---> Using cache
 ---> ef5c38218a51
Successfully built ef5c38218a51
Successfully tagged testimage:latest
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Remove Unused docker image)
[Pipeline] sh
+ docker rmi testimage:latest
Untagged: testimage:latest
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] slackSend
Slack Send Pipeline step running, values are - baseUrl: <empty>, teamDomain: mtrainingworkspace, channel: , color: good, botUser: false, tokenCredentialId: 6116b420-44c8-4ff2-b9bd-a21d00b8a283, notifyCommitters: false, iconEmoji: <empty>, username: <empty>, timestamp: <empty>
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
#### Example notification in Slack

![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins23.png?raw=true)