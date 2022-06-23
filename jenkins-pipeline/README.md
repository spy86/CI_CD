# Jenkins pipelines

## Prerequisites
* Docker engine
* Dockerhub account (https://hub.docker.com/signup)
* Jenkins plugin Docker Pipeline

## How to use ?

#### Credentials
* Create free account in dockerhub
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/dockerhub.png?raw=true)

* Create credentials on Jenkins for dockerhub, go to `Manage Jenkins`-> `Manage Credentials`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins1.png?raw=true)

* Click store `Jenkins` -> `Global credentials (unrestricted)` and then `Add Credentials` on the left menu
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins2.png?raw=true)

* Choose `Username and Password`, paste login and password for dockerhub as below. At the end we save it with the name "dockerhub"
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins3.png?raw=true)

---
#### Configure Pipeline
* Click on New item and select pipeline and setup name `DockerImageBuild`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins4.png?raw=true)

* Configure Pipeline
  * Enable `This project is parameterized` and setup string parametr with name **IMAGE_NAME**
  * Choose Definition `Pipeline script from SCM`
  * Repository URL `https://github.com/spy86/CI_CD.git`
  * Credentials empty
  * Branch to build `main`
  * Script Path `jenkins-pipeline/Jenkinsfile`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins5.png?raw=true)


Now we can run our pipeline. 

* Click `Build with Parameters` provide image name and click **Build**
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins7.png?raw=true)

After a while, our pipeline should build a docker image and then send it to the docherhub.

#### Example pipeline console output
```
Started by user unknown or anonymous
Obtained jenkins-pipeline/Jenkinsfile from git https://github.com/spy86/CI_CD
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/DockerImageBuild
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Declarative: Checkout SCM)
[Pipeline] checkout
Selected Git installation does not exist. Using Default
The recommended git tool is: NONE
using credential 70150275-3513-4731-82e5-46abe898117e
 > git rev-parse --resolve-git-dir /var/lib/jenkins/workspace/DockerImageBuild/.git # timeout=10
Fetching changes from the remote Git repository
 > git config remote.origin.url https://github.com/spy86/CI_CD # timeout=10
Fetching upstream changes from https://github.com/spy86/CI_CD
 > git --version # timeout=10
 > git --version # 'git version 2.25.1'
 > git fetch --tags --force --progress -- https://github.com/spy86/CI_CD +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git rev-parse refs/remotes/origin/main^{commit} # timeout=10
Checking out Revision 22eab72fb980f6cd4b39fc00611577cda106a336 (refs/remotes/origin/main)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 22eab72fb980f6cd4b39fc00611577cda106a336 # timeout=10
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
 > git rev-parse --resolve-git-dir /var/lib/jenkins/workspace/DockerImageBuild/.git # timeout=10
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
 > git rev-list --no-walk 50684617b5b19c6b17dc8810135e3884aa930b5d # timeout=10
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
+ docker build -t spy86/test-docker .
Sending build context to Docker daemon  3.088MB

Step 1/5 : FROM spy86/ubuntu:latest
 ---> bead38211656
Step 2/5 : RUN   mkdir -p /tmp/dart &&   cd /tmp/dart &&   curl -O https://storage.googleapis.com/dart-archive/channels/stable/release/2.16.2/linux_packages/dart_2.16.2-1_amd64.deb &&   dpkg -i dart_2.16.2-1_amd64.deb &&   rm -fr /tmp/dart
 ---> Using cache
 ---> 7782d13b4c2d
Step 3/5 : ENV PATH /usr/lib/dart/bin:$PATH
 ---> Using cache
 ---> b6084c04a88b
Step 4/5 : WORKDIR /data
 ---> Using cache
 ---> a483d9ab3ddc
Step 5/5 : CMD ["bash"]
 ---> Using cache
 ---> 3168b32385b7
Successfully built 3168b32385b7
Successfully tagged spy86/test-docker:latest
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Push docker Image)
[Pipeline] script
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] withDockerRegistry
$ docker login -u spy86 -p ******** https://index.docker.io/v1/
WARNING! Using --password via the CLI is insecure. Use --password-stdin.
Login Succeeded
[Pipeline] {
[Pipeline] isUnix
[Pipeline] withEnv
[Pipeline] {
[Pipeline] sh
+ docker tag spy86/test-docker spy86/test-docker:3
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] isUnix
[Pipeline] withEnv
[Pipeline] {
[Pipeline] sh
+ docker push spy86/test-docker:3
The push refers to repository [docker.io/spy86/test-docker]
e1d51135e78f: Preparing
978273909336: Preparing
33064b690f81: Preparing
cee5d7972bcd: Preparing
aa13b2cfe506: Preparing
20e733608f82: Preparing
bfd8249e5d8f: Preparing
e59fc9495612: Preparing
20e733608f82: Waiting
bfd8249e5d8f: Waiting
e59fc9495612: Waiting
e1d51135e78f: Layer already exists
aa13b2cfe506: Layer already exists
cee5d7972bcd: Layer already exists
33064b690f81: Layer already exists
978273909336: Layer already exists
20e733608f82: Layer already exists
bfd8249e5d8f: Layer already exists
e59fc9495612: Layer already exists
3: digest: sha256:a44ce8e86935f09eed3fe96e8b7dfa4faa8be10fe5b3575d56263503717ae093 size: 1992
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] isUnix
[Pipeline] withEnv
[Pipeline] {
[Pipeline] sh
+ docker tag spy86/test-docker spy86/test-docker:latest
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] isUnix
[Pipeline] withEnv
[Pipeline] {
[Pipeline] sh
+ docker push spy86/test-docker:latest
The push refers to repository [docker.io/spy86/test-docker]
e1d51135e78f: Preparing
978273909336: Preparing
33064b690f81: Preparing
cee5d7972bcd: Preparing
aa13b2cfe506: Preparing
20e733608f82: Preparing
bfd8249e5d8f: Preparing
e59fc9495612: Preparing
20e733608f82: Waiting
bfd8249e5d8f: Waiting
e59fc9495612: Waiting
aa13b2cfe506: Layer already exists
33064b690f81: Layer already exists
cee5d7972bcd: Layer already exists
978273909336: Layer already exists
e1d51135e78f: Layer already exists
20e733608f82: Layer already exists
e59fc9495612: Layer already exists
bfd8249e5d8f: Layer already exists
latest: digest: sha256:a44ce8e86935f09eed3fe96e8b7dfa4faa8be10fe5b3575d56263503717ae093 size: 1992
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // withDockerRegistry
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Remove Unused docker image)
[Pipeline] sh
+ docker rmi spy86/test-docker:3
Untagged: spy86/test-docker:3
[Pipeline] sh
+ docker rmi spy86/test-docker:latest
Untagged: spy86/test-docker:latest
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

![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins6.png?raw=true)