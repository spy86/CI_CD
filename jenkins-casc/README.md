# Jenkins as a Code

## Prerequisites
* Installed plugin Jenkins Configuration as a code
* Docker
* Docker-compose

## How to run

#### Build and Run Jenkins 
```bash
git clone https://github.com/spy86/CI_CD
cd jenkins-casc
chown -R 1000:1000 jenkins_home/
docker-compose build && docker-compose up -d
```
After a while, the jenkins image will be built and run. We can enter it using the address `http://<IP-ADDRESS>:8089`, when jenkins starts up, an empty jenkins dashboard will appear.

![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build15.png?raw=true)

* The plugin that we will need to support Configuration as a Code is already installed when building the image, we can verify it in: `Manage Jenkins` -> `Manage Plugins` -> `Installed`. In filter field we need to type **Configuration as Code Plugin** and installed plugin should be on the list

![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build16.png?raw=true)

* We currently don't have any configuration applied as code yet. Only the default Jenkins configuration that is generated based on the current state is loaded. We can check this on: `Manage Jenkins` -> `Configuration as Code`. As we see controller has no configuration as code file set.
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build17.png?raw=true)

* Apply some example configuraion. We can do this by specifying a directory on disk or a URL. For the test we will provide the following URL and click **Apply new configuration**

⚠️Jenkins validates the configuration for errors before applying it. If it is incorrect, it will not be applied

**URL with configuration:** https://raw.githubusercontent.com/spy86/jenkins-as-code-docker-demo/master/jenkins-controller/configurations.yml

* After the configuration is applied, the information where the current config is downloaded from and when it was applied should appear.

![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build18.png?raw=true)

* So let's check if the configuration of our Jenkins has been changed. Things that should change are:

  * On main dashboard we should see information **Jenkins configured automatically by Jenkins Configuration as Code plugin**
  ![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build19.png?raw=true)

  * You should see an example job named **Seedjob**
  ![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build20.png?raw=true)
  * Newly created credentials should appear. We can check this in `Manage Jenkins` -> `Manage Credentials`
  ![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build21.png?raw=true)

#### Load configuration from directory

* First, we need to stop the previous container and remove all files from jenkins' home directory

```bash
cd jenkins-casc
docker-compose down
cd jenkins_home
rm -rf * 
```

* Nowe we can run Jenkins with configuration from local file.
```bash
cd jenkins-casc
docker-compose -f docker-compose-with-local-config.yml build && docker-compose -f docker-compose-with-local-config.yml up -d
```
After a while, the jenkins image will be built and run. We can enter it using the address `http://<IP-ADDRESS>:8089`, when jenkins starts up, an empty jenkins dashboard will appear with loaded configuration.
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build22.png?raw=true)

* Let's change the configuration in the file and load the new configuration, to do this, just edit the file called "configurations.yml" which is mounted directly to the Jenkins container

```
cd jenkins-casc
nano configurations.yml
## Edit line with systemMessage, save file
```
Now we can apply new configuration. Go to `Manage Jenkins` --> `Configuration as Code` and cick **Apply new configuration** . After a while the configuration should be changed.

#### Usefull Links
* Plugin website(https://www.jenkins.io/projects/jcasc/)
* Documentation(https://github.com/jenkinsci/configuration-as-code-plugin/blob/master/README.md)
* Secrets in JCASC(https://github.com/jenkinsci/configuration-as-code-plugin/blob/master/docs/features/secrets.adoc)
* Configuration Reload (https://github.com/jenkinsci/configuration-as-code-plugin/blob/master/docs/features/configurationReload.md)
