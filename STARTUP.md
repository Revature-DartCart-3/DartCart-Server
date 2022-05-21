## Startup API

### Running the application locally

The applicaiton is currently set up to be run locally with the client side being on localhost:3000 and the server side being on localhost:9005.

The server side is currently connected to a PostgreSQL database. Connection details are found in the application.yaml file. Environment variables are currently being used for the database connection. 

### Set up the environment variables for a PostgreSQL database for each user

1. Go to the run configurations for your IDE.
2. Go to the environment variables for the Dart Cart Server Application.
3. Add the following environment variables.
    Name: pg-db       Value: name of your database,
    Name: pg-username Value: your postgres username (postgres by defualt),
    Name: pg-password Value: your postgress password

### Set up for the reset password feature from the second group

Currently the email account used by the second group is no longer active. If you wish the use the reset password feature a new gmail account along with the password must be added to the application.yaml file.
The email also sends you to the azurewebsite link. If you wish to change this so that the reset password feature works locally or wherever you wish to host this website, the link is located in the AuthController.java file under the @GetMapping("/resetpass/{username}"). The current link uses https://dart-cart-p3.azurewebsites.net/. Change this to either http://localhost:3000/ or where ever you decide to host the website for the email to send you the correct link.

If you wish to set up an Azure Web App you can follow the below instructions from the Dart Cart 2 group. For this to work the .env file on the client side must be changed to https://dart-cart-p3.azurewebsites.net/ and the azure-webapps-java-jar.yml file must be uncommented on both the client and server side. 
### Setting up Azure Web App

The way this work flow is requires that the Azure App Service is already created so it starts with that.

1. First step is is pretty much the same as this example https://docs.microsoft.com/en-us/azure/app-service/quickstart-java?tabs=javase&pivots=platform-linux. It has some requirements like having an Azure account and Azure subscription. The only real deviation from the example is to clone from your repo. Make sure to change the pricing plan to your need.
  
2. Download the Publish Profile which can be found on the overview of the Web App in Azure Portal.![net  Import publish profile from Azure web app into Visual Studio Code or  vs for mac  Stack Overflow](https://i.stack.imgur.com/oFVBv.png)
  
3. Go to the settings of the Github repo and go to the security section and select Secrets/Actions to set up a new repository secret. Click new repository secret and make sure to name the AZURE_WEBAPP_PUBLISH_PROFILE and paste your publish profile you got from before. Add the secret to the repo. ![image](https://user-images.githubusercontent.com/32827900/159562889-897a5da1-628c-4768-a460-1e3b429e920f.png) ![image](https://user-images.githubusercontent.com/32827900/159562964-d4644e2f-b6d8-4fcd-80d1-0372f218e3f3.png)


  
4. Ideally the repo should still have the azure-webapps-java-jar.yml which is located in .github/workflows/ . This means that you don't have to create the file yourself. This file is what's telling Github actions to create and deploy to Azure. You can change the values in the env section to alter the name and version of java being used. Now everything should be setup where any pushes to the main will cause the create and deploy to occur. ![image](https://user-images.githubusercontent.com/32827900/159563116-6d9f5235-876f-48a5-a58e-85d6271509e8.png)

  

if seeking more information then please refer to the comments on the azure-webapps-java-jar.yml. There are multiple links to documentation which should fill in some knowledge gaps. This is intended to just get you running.
