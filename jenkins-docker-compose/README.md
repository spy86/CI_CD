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

![alt text](/images/JenkinsWelcomePage.png "Jenkins Welcome Page")