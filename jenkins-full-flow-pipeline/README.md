# Jenkins full flow pipeline

## Prerequisites
* Docker engine (with swarm init)
* Sonarqube

## How to use ?

#### Configure Pipeline
* Click on New item and select pipeline and setup name `FullFlowPipeline`
![alt text](/images/Jenkins8.png "")

* Configure Pipeline
  * Choose Definition `Pipeline script from SCM`
  * Repository URL `https://github.com/spy86/CI_CD.git`
  * Credentials empty
  * Branch to build `main`
  * Script Path `jenkins-full-flow-pipeline/Jenkinsfile`
![alt text](/images/Jenkins9.png "")

Now we can run our pipeline. 

* Click `Build now`, after a while, our pipeline should be:
a) Checkout git repo
b) Run sonarqube analyze
c) Build images with docker
d) Deploy images into swarm
e) Verify if services is runing

#### Example pipeline console output
```
```