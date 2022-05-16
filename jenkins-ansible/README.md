### Simple Ansible playbook to install Jenkins

## How to run
```bash
git clone https://github.com/spy86/CI_CD
cd jenkins-ansible
ansible-playbook jenkins.yaml -i inventory
```

## Ansible Output
```bash
root@vagrant:/home/vagrant# ansible-playbook jenkins.yaml -i inventory

PLAY [target] **********************************************************************************************************************************************

TASK [Gathering Facts] *************************************************************************************************************************************
changed: [target]

TASK [Import the jenkins apt key] **************************************************************************************************************************
changed: [target]

TASK [Add Jenkins apt repository entry] ********************************************************************************************************************
changed: [target]

TASK [Ansible apt install ca-certificates] *****************************************************************************************************************
changed: [target]

TASK [Update and apt packages] *****************************************************************************************************************************
changed: [target]

TASK [Ansible apt install default-jre] *********************************************************************************************************************
changed: [target]

TASK [Ansible apt install jenkins] *********************************************************************************************************************
changed: [target]

TASK [Ensure jenkins is installed] *************************************************************************************************************************
changed: [target]

TASK [Ensure jenkins is running] ***************************************************************************************************************************
changed: [target]

PLAY RECAP *************************************************************************************************************************************************
target                     : ok=9    changed=9    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0

```

Wait for the playbook to finish and run the address http://<'IP-ADDRESS'>:8080 in the browser, You should see Jenkins welcome page.

![alt text](/images/JenkinsWelcomePage.png "Jenkins Welcome Page")


## Configuration

* Get Temporary Admin Password
```bash
root@vagrant:/home/vagrant/jenkins# cat /var/lib/jenkins/secrets/initialAdminPassword
```

* Paste this password inside page and click Continue
![alt text](/images/AdminPassword.png "Admin Password")

* Next install suggested plugins
![alt text](/images/SuggestedPlugins.png "Suggested Plugins")

* Wait For installation completed and click 
![alt text](/images/SuggestedPlugins2.png "Suggested Plugins")

* Setup local admin account and click **Save and Continue** and then **Save and FInish**
![alt text](/images/LocalAdminAccount.png "Suggested Plugins")

After that our Jenkins setup is complete and we can start using Jenkins.
