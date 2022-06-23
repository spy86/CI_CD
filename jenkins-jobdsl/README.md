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

![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build15.png?raw=true)

* The plugin that we will need to support Configuration as a Code is already installed when building the image, we can verify it in: `Manage Jenkins` -> `Manage Plugins` -> `Installed`. In filter field we need to type **job dsl** and installed plugin should be on the list
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build23.png?raw=true)

* We don't have a job that will allow us to create more jobs with Job DSL, so we need to create one.

> **_NOTE:_** If we use Jenkins as a Code, we can define such a job in the main Jenkins configuration

---
#### Create Seedjob from scratch

* Click `Dashboard`

* Click `New Item`

* Enter an item name **Seed_job_and_view** choose `Freestyle project` and click **OK**
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build24.png?raw=true)

* In Source Code Management change to `git` and add repository https://github.com/spy86/CI_CD and change branch to `main`.
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build28.png?raw=true)

* Enable `This project is parameterized` next add choice and string parameter which will be used in DSL.
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build25.png?raw=true)
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build26.png?raw=true)
* In Build click `Add build step` next choose **Process Job DSLs** and setup step as below:
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build27.png?raw=true)

* We leave the rest of the settings as default and click **Save**

#### Test Job DSL

* Seed Example job:
  * Run job with following parameters
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build29.png?raw=true)
  * When the job finishes running, you should see a job named **example**
![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build31.png?raw=true)

* Seed Example view:
  * Run job with following parameters
  ![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build30.png?raw=true)
  * When the job finishes running, you should see a view named **project-A** with job inside
  ![alt text](https://github.com/spy86/CI_CD/blob/gh-pages/images/build32.png?raw=true)