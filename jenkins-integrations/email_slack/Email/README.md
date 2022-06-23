# Jenkins Email

## Prerequisites
* SMTP Server Access (provided on the day of training)

## How to use ?

#### Setup SMTP in Jenkins
* CLick `Manage Jenkins` -> `Configure System` 
* Setup **System Admin e-mail address** (provided on the day of training)
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins25.png?raw=true)
* Go to section `Extended E-mail Notification`, setup SMTP Server and credentials (will be provided on the day of training)
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins24.png?raw=true)

#### Configure Pipeline
* Click on New item and select pipeline and setup name `EmailIntegration`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins26.png?raw=true)

* Configure Pipeline
  * Choose Definition `Pipeline script from SCM`
  * Repository URL `https://github.com/spy86/CI_CD.git`
  * Credentials empty
  * Branch to build `main`
  * Script Path `jenkins-integrations/email_slack/Email/Jenkinsfile`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins27.png?raw=true)

Now we can run our pipeline. 

* Click `Build now`, after a while, our pipeline should be:
a) Run test stage
b) Send email with notofication

---
#### Example pipeline console output

```
Started by user unknown or anonymous
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/EmailIntegration
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Test)
[Pipeline] sh
+ echo Run TEST!
Run TEST!
+ exit 1
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] echo
This will always run
[Pipeline] echo
This will run only if the state of the Pipeline has changed
[Pipeline] echo
For example, if the Pipeline was previously failing but is now successful
[Pipeline] emailext
Sending email to: contact@mtraining.ovh
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
ERROR: script returned exit code 1
Finished: FAILURE
```

#### Example notification in Email
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins29.png?raw=true)
