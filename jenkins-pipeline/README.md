# Jenkins pipelines

## Prerequisites
* Docker engine
* Dockerhub account (https://hub.docker.com/signup)
* Jenkins plugin Docker Pipeline

## How to use ?

#### Credentials
* Create free account in dockerhub
![alt text](/images/dockerhub.png "")

* Create credentials on Jenkins for dockerhub, go to `Manage Jenkins`-> `Manage Credentials`
![alt text](/images/Jenkins1.png "")

* Click store `Jenkins` -> `Global credentials (unrestricted)` and then `Add Credentials` on the left menu
![alt text](/images/Jenkins2.png "")

* Choose `Username and Password`, paste login and password for dockerhub as below. At the end we save it with the name "dockerhub"
![alt text](/images/Jenkins3.png "")

---
#### Configure Pipeline
* Click on New item and select pipeline and setup name `DockerImageBuild`
![alt text](/images/Jenkins4.png "")

* Configure Pipeline
  * Enable `This project is parameterized` and setup string parametr with name **IMAGE_NAME**
  * Choose Definition `Pipeline script from SCM`
  * Credentials empty
  * Branch to build `main`
  * Script Path `jenkins-pipeline/Jenkinsfile`
![alt text](/images/Jenkins5.png "")


Now we can run our pipeline. After a while, our pipeline should build a docker image and then send it to the docherhub.

#### Example pipeline console output
```
dasdasd
```