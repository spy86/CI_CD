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
![alt text](/images/dockerhub.png "")

* Create credentials on Jenkins for dockerhub, go to `Manage Jenkins`-> `Manage Credentials`
![alt text](/images/Jenkins1.png "")

* Click store `Jenkins` -> `Global credentials (unrestricted)` and then `Add Credentials` on the left menu
![alt text](/images/Jenkins2.png "")

* Choose `Username and Password`, paste login and password for dockerhub as below. At the end we save it with the name "dockerhub"
![alt text](/images/Jenkins3.png "")

---
#### Slack Setup Instructions

* In your Jenkins dashboard, click on `Manage Jenkins` from the left navigation.
* Click on Manage Plugins and search for `Slack Notification` in the Available tab. Click the checkbox and install the plugin.
![alt text](/images/Jenkins16.png "")
* After it's installed, click on `Manage Jenkins` again in the left navigation, and then go to `Configure System`. Find the `Slack` section and add the following values:
  * Workspace
  * Credential  

  The other fields are optional. You can click on the question mark icons next to them for more information. Press Save when you're done.

![alt text](/images/Jenkins17.png "")