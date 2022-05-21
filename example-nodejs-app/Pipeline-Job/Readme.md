# Pipeline Jenkins Job to build simple NodeJS.

## Requirements
* [NodeJS Plugin](https://plugins.jenkins.io/nodejs/)
* [Repository with code](https://github.com/spy86/CI_CD/tree/main/example-nodejs-app/App)
* Docker

## Steps to be performed

#### Install plugin (If it has not been installed previously)

* Click `Manage Jenkins`
![alt text](/images/build1.png "")

* Next click `Manage Plugins` 
![alt text](/images/build2.png "")

* Next choose **available** tab, and in the search field, type **nodejs**
![alt text](/images/build3.png "")

* As the last step, select the plugin and click **install without restart**
![alt text](/images/build4.png "")

* After a while the plugin will be installed and can be used with jenkins.
![alt text](/images/build5.png "")

Now we can configure NodeJs tool in Jenkins
* Again click `Manage Jenkins`
![alt text](/images/build6.png "")

* Next click `Global Tool Configuration` and find **NodeJS**. Click **Add NodeJS** ,complete it as below and click save
![alt text](/images/build7.png "")

#### Configure Jenkins Job

* Click `Dashboard`
* Click `New Item`
* Enter an item name **ExampleNodejsBuildPipeline** choose `Freestyle project` and click **OK**
* In Pipeline section choose `Definition Pipeline Script from SCM`. As SCM we will choose git and specify the same repository https://github.com/spy86/CI_CD. We leave the rest of the settings as default. The entire configuration of our pipeline is defined in the Jenkinsfile


#### Run Job

* Click **Build Now**, after a while the code should be built and the artifacts should see the contents of the `dist` directory.

* Example Console output
```

```
* Files in artifacts