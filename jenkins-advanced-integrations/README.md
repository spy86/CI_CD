# Jenkins Advanced Integrations

## Prerequisites
* Installed plugins: [HTTP Requests](https://plugins.jenkins.io/http_request/), [Bitbucket Build Status Notifier](https://plugins.jenkins.io/bitbucket-build-status-notifier/) , [Bitbucket OAuth](https://plugins.jenkins.io/bitbucket-oauth/)
* Generated OAuth consumers (provided on the day of training)
* Bitbucket repository

## How to use ?

#### Credentials
* Create credentials on Jenkins for bitbucket integration, go to `Manage Jenkins`-> `Manage Credentials`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins1.png?raw=true)

* Click store `Jenkins` -> `Global credentials (unrestricted)` and then `Add Credentials` on the left menu
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins2.png?raw=true)

* Choose `Username and Password`, paste login and password. At the end we save it with the name "bitbucket-oauth2". Details will be provided on the day of training.

---
#### Configure Pipeline

##### Freestyle job
* Click on New item and select Freestyle project and setup name `JenkinsAdvancedIntegrationsOne` and click **OK**
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins35.png?raw=true)
* Setup Source Code Management as git and paste Repository URL `https://bitbucket.org/mtraining/example-repo.git`

* Go to `Post-build Actions` and choose **Bitbucket notify build status** and enable all options (`Notify build start`, `Notify build finish`, `Only show latest build status`). As credentials please use those generated earlier.

Now we can run our pipeline. 
* Click `Build Now` and wait for job finish. Status should be sent to bitbucket after build is complete

#### Example pipeline console output
```
Started by user admin
Running as SYSTEM
Building in workspace /var/lib/jenkins/workspace/JenkinsAdvancedIntegrationsOne
The recommended git tool is: NONE
No credentials specified
 > git rev-parse --resolve-git-dir /var/lib/jenkins/workspace/JenkinsAdvancedIntegrationsOne/.git # timeout=10
Fetching changes from the remote Git repository
 > git config remote.origin.url https://bitbucket.org/mtraining/example-repo.git # timeout=10
Fetching upstream changes from https://bitbucket.org/mtraining/example-repo.git
 > git --version # timeout=10
 > git --version # 'git version 2.25.1'
 > git fetch --tags --force --progress -- https://bitbucket.org/mtraining/example-repo.git +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git rev-parse refs/remotes/origin/master^{commit} # timeout=10
Checking out Revision 446b0a6614796340efbd24fa406ee1e74ab7b015 (refs/remotes/origin/master)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 446b0a6614796340efbd24fa406ee1e74ab7b015 # timeout=10
Commit message: "Initial commit"
 > git rev-list --no-walk 446b0a6614796340efbd24fa406ee1e74ab7b015 # timeout=10
Sending build status INPROGRESS for commit 446b0a6614796340efbd24fa406ee1e74ab7b015 to BitBucket is done!
Sending build status SUCCESSFUL for commit 446b0a6614796340efbd24fa406ee1e74ab7b015 to BitBucket is done!
Finished: SUCCESS
```
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins37.png?raw=true)

---

##### Pipeline Job
* Click on New item and select Freestyle project and setup name `JenkinsAdvancedIntegrationsTwo` and click **OK**
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins38.png?raw=true)
* Configure Pipeline
  * Choose Definition `Pipeline script from SCM`
  * Repository URL `https://github.com/spy86/CI_CD.git`
  * Credentials empty
  * Branch to build `main`
  * Script Path `jenkins-advanced-integrations/Jenkinsfile`
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/Jenkins39.png?raw=true)

Now we can run our pipeline. 

* Click `Build Now` and wait for job finish. After a while, our pipeline should be show pull requests.

#### Example pipeline console output
```
Started by user admin
[Pipeline] Start of Pipeline
[Pipeline] httpRequest
HttpMethod: POST
URL: https://bitbucket.org/site/oauth2/access_token
Content-Type: application/x-www-form-urlencoded; charset=ISO-8859-1
Using authentication: bitbucket-oauth2
Sending request to url: https://bitbucket.org/site/oauth2/access_token
Response Code: HTTP/1.1 200 OK
Success: Status code 200 is in the accepted range: 100:399
[Pipeline] httpRequest
HttpMethod: GET
URL: https://api.bitbucket.org/2.0/repositories/mtraining/example-repo/pullrequests
Authorization: *****
Sending request to url: https://api.bitbucket.org/2.0/repositories/mtraining/example-repo/pullrequests
Response Code: HTTP/1.1 200 OK
Success: Status code 200 is in the accepted range: 100:399
[Pipeline] echo
develop
[Pipeline] echo
Maciej/testtxt-created-online-with-bitbucket-1655050725270
[Pipeline] End of Pipeline
Finished: SUCCESS
```