# Jenkins JiraCloud

## Prerequisites
* JIRA Pipeline Steps plugin (https://plugins.jenkins.io/jira-steps/)
* JIRA cloud Access (provided on the day of training)

## How to use ?

#### Setup JIRA STEPS plugin in Jenkins
* Go to `Manage Jenkins` -> `Configure System`
* G0 to section `JIRA Steps` and setup jira credentials (will be provided on the day of training).
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins30.png?raw=true)

#### Configure Pipeline
* Click on New item and select pipeline and setup name `JIRAIntegration`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins31.png?raw=true)

* Configure Pipeline
  * Enable `This project is parameterized` and setup string parametr with name **TICKET_NUMBER**
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins32.png?raw=true)
  * Choose Definition `Pipeline script from SCM`
  * Repository URL `https://github.com/spy86/CI_CD.git`
  * Credentials empty
  * Branch to build `main`
  * Script Path `jenkins-integrations/jira/Jenkinsfile`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins33.png?raw=true)

Now we can run our pipeline. 

* Click `Build with Parameters` provide ticket number and click **Build**

  * Ticket number: TP-1
    * Issue type: BUG 
  * Ticket number: TP-2
    * Issue type: TASK
  * Ticket number: TP-3
    * Issue type: EPIC

> **_NOTE:_** Pipeline verifies the type of ticket.

After a while, our buil should verify that the given ticket is of the correct type. If the type is correct, the next stage will run, which will perform an example action, and in the last step, a comment will be added to the ticket.

##### Comment in JIra ticket

![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins34.png?raw=true)