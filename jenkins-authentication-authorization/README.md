# Jenkins Authentication/Authorization

## Prerequisites
* Installed plugins: [Role-based Authorization Strategy](https://plugins.jenkins.io/role-strategy/)
* Two Jenkins account for test.
## How to use ?
#### Create second account
* Go to `Manage Jenkins` -> `Manage Users` and click **Create User**
* Fill all fields
* First user:
  * Username: test_user_01
  * Password: P@ssw0rd_123
  * Confirm password: ssw0rd_123
  * Full name: Test User 01
  * E-mail address: test_user_01@example.com
* Second user:
  * Username: test_user_02
  * Password: P@ssw0rd_123
  * Confirm password: ssw0rd_123
  * Full name: Test User 02
  * E-mail address: test_user_02@example.com
  ![alt text](/images/Jenkins40.png "")
* Now we will be configure Global Security go to `Manage Jenkins` -> `Configure Global Security`, set as below and click save
  * For **Security Realm** set `Jenkins’ own user database`
  * For **Authorization** set `Role-Based Strategy`
  * For **CSRF Protection** check `Enable proxy compatibility`
* Create two empty Freestyle project with name `Project_01` and `Project_02`
![alt text](/images/Jenkins41.png "")
 * Now we will be configure roles go to `Manage Jenkins` -> `Manage and Assign Roles` -> `Manage Roles`, set as below and click save
   * For **Global roles** set new role called `user` with Overall Read Access
![alt text](/images/Jenkins42.png "")
   * For **Item roles** set two roles for `Project_01` and `Project_02`
 ![alt text](/images/Jenkins43.png "")
 * Now we will be configure access restrictions go to `Manage Jenkins` -> `Manage and Assign Roles` -> `Assign Roles`, set as below and click save
   * For **Item roles** set access for `test_user_01` and `test_user_02`
![alt text](/images/Jenkins44.png "")
---
#### Testing
* Login as `test_user_01` and we should see only project with name `Project_01`
![alt text](/images/Jenkins45.png "")
* Login as `test_user_02` and we should see only project with name `Project_02`
![alt text](/images/Jenkins46.png "")