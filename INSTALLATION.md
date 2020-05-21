# Steps to follow to setup API automation in local system:

**Install Java: [Skip this step if already installed]**
- Check if Java is installed.
  - In terminal enter java -version to check if java is installed in the system.
  - In terminal enter javac -version to check if java compiler is installed in the system.
  - Any version of java greater than 1.8 is supported.
  - Check with API team in case a lower version is installed 
  - Install java development kit if not available.

**Install Eclipse Photon: [Skip this step if already installed]** 
- Install eclipse photon if not available.
  - Download eclipse installer.
  - Run eclipse installer.
  - Select install eclipse for java developers.
  - Open workbench.

**Get Code base:**
  - Clone restassuredFramework
    - url : https://github.com/sithik1994/restassuredFramework.git

**Setup project in Eclipse:** 
  - File -> Open Project from File System -> Browse the folder and open the cloned project.
  - Open -> Help -> Eclipse Marketplace -> Search testng -> Install Testng for eclipse plugin -> Restart eclipse.
  - Maven will be available by default, with eclipse. To check, right click on project -> should have an option called maven.
```
Possible issues:
In case of error in pom.xml file ->Cannot read lifecycle mapping metadata for artifact org.apache.maven.plugins:mav
  - In terminal open Users/<profile_name>/.m2
  - Run rm -r repository
  - Right click on project -> Update project
In case of error in all import statements
  - Click on src/main/java folder -> build path -> remove from build path
  - Click on src -> main -> java ->right click -> build path -> use as source folder
  - Refresh the project
````
**Set up verification**
  - In business logic package -> Right click on any java file -> run as testng test.
