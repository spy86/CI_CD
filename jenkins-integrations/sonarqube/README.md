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

#### Example pipeline console output
```

```