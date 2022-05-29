# Jenkins Job-DSL

## Prerequisites
* Installed plugin Jenkins Job-DSL

## How to run

#### Build and Run Jenkins 
```bash
git clone https://github.com/spy86/CI_CD
cd jenkins-casc
docker-compose build && docker-compose up -d
```
After a while, the jenkins image will be built and run. We can enter it using the address `http://<IP-ADDRESS>:8089`, when jenkins starts up, an empty jenkins dashboard will appear.

![alt text](/images/build15.png "")

* The plugin that we will need to support Configuration as a Code is already installed when building the image, we can verify it in: `Manage Jenkins` -> `Manage Plugins` -> `Installed`. In filter field we need to type **job dsl** and installed plugin should be on the list
![alt text](/images/build23.png "")

* We don't have a job that will allow us to create more jobs with Job DSL, so we need to create one.

> **_NOTE:_** If we use Jenkins as a Code, we can define such a job in the main Jenkins configuration

---
#### Create Seedjob from scratch

* Click `Dashboard`

* Click `New Item`

* Enter an item name **Seedjob** choose `Freestyle project` and click **OK**
![alt text](/images/build24.png "")

* In Source Code Management change to `git` and add repository https://github.com/spy86/CI_CD and change branch to `main`.
![alt text](/images/build9.png "")

* Enable `This project is parameterized` next add choice and string parameter which will be used in DSL.
![alt text](/images/build25.png "")
![alt text](/images/build26.png "")

* In Build click `Add build step` next choose **Process Job DSLs** and setup step as below:
![alt text](/images/build27.png "")

* We leave the rest of the settings as default and click **Save**

#### Test Job DSL
