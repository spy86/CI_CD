# Simple docker-compose and bash script for install prerequisites and run Jenkins in docker

## Prerequisites
* Docker engine
* Docker-compose


## How to use ?
```bash
git clone https://github.com/spy86/CI_CD
cd jenkins-docker-compose
chmod +x install.sh
./install.sh
```

Wait for the script to finish and run the address http://<'IP-ADDRESS'>:8080 in the browser You should see Jenkins welcome page.

![alt text](/images/.png "Jenkins Welcome Page")
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/JenkinsWelcomePage.png?raw=true)

## Configuration

* Get Temporary Admin Password
```bash
container_id=$(docker ps -aqf "name=jenkins")
docker exec $container_id cat /var/jenkins_home/secrets/initialAdminPassword
```

* Paste this password inside page and click Continue
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/AdminPassword.png?raw=true)

* Next install suggested plugins
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/SuggestedPlugins.png?raw=true)

* Wait For installation completed and then website will be redirect to creation local admin account.
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/SuggestedPlugins2.png?raw=true)

* Setup local admin account and click **Save and Continue** and then **Save and Finish**
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/LocalAdminAccount.png?raw=true)

After that our Jenkins setup is complete and we can start using Jenkins.