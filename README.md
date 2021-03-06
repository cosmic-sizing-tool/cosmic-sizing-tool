﻿# cosmic-sizing-tool

# OVERVIEW #

## User stories ##
[![Stories in Ready](https://badge.waffle.io/cosmic-sizing-tool/cosmic-sizing-tool.svg?label=ready&title=Ready)](http://waffle.io/cosmic-sizing-tool/cosmic-sizing-tool)

## Dasboard ##

[Consult the dashboard](https://waffle.io/cosmic-sizing-tool/cosmic-sizing-tool)

# README #

This README documents the steps necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

#### Summary of set up####

The Play framework is needed in order to run this program.

#### Database configuration ####

* Install PostgreSQL on your machine
1. Follow instructions to install a Postgres Server on your machine
2. Run postgres "createdb" util and Create Database "default" using your postgres user
using ``createdb -U postgres default`` or ``CREATE DATABASE "default";`` inside the postgres console
3. Create user "cosmic" with password "cosmic123" as per application config;
run ``CREATE USER cosmic PASSWORD 'cosmic123';`` inside the database console
4. Your server should now be running and deploying in development with "Activator run" should connect to local DB
running at "postgres://localhost/default"

#### Dependencies ####

* Play Framework
 1. [Download](https://www.playframework.com/download) it.
 2. Make a new folder and extract the .zip file in it.
 3. Add ``activator`` to your **PATH** or create the following link substituting ``/path/to/activator`` with the absolute path to the ``activator`` executable
 by executing ``sudo ln -s /path/to/activator /usr/local/bin/activator``. Consult this [link](https://www.playframework.com/documentation/2.4.x/Installing) for the complete installation documentation.

* SQLite 3
 1. Download and install SQLite 3 for your platform at https://www.sqlite.org/.

#### Internationalization instructions ####

* HOW TO ADD A NEW LANGUAGE</br>
 1. Create a file in public/resources called **xx.json** where **xx** is the language code you're trying to add according to ISO 639-1. https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
 2. Add your language to the following section of public/javascript/app/app.js :
 ```javascript  
 $scope.languages = [
    {id:'en', name:'English'},
    {id:'fr', name:'Français'},
    {id:'xx', name:'NewLanguage'}
 ];
 
 $translateProvider.registerAvailableLanguageKeys(['en', 'fr', xx], {
    'en_*': 'en',
    'fr_*': 'fr',
    'xx_*': 'xx'
   });
  ```
    where **xx** is the code of the language you're trying to add.

* HOW TO FORMAT HTML LABELS</br>
  https://angular-translate.github.io/docs/#/guide/05_using-translate-directive

  i. Add ressource to the file just created :
  ```html
   {
     "WEBSITE_SECTION":
       {
         "RESOURCE_NAME":"Text to print"
       }
   }
   ```
   Where **WEBSITE_SECTION** and **RESOURCE_NAME** are the same for all the languages JSON files (refer to the english JSON file).
   
  ii. Add filter and ID in your html tag :
  ```html
   <p>'WEBSITE_SECTION.RESOURCE_NAME' | translate</p>
   ```

* ANY MODULE THAT YOU ADD MUST INJECT THE $translate MODULE AS SHOWN IN THE FOLLOWING EXAMPLE :
 ```javascript
 .controller('RegistrationCtrl', ['$scope', '$translate', function ($scope, $translate) {
 ```

#### Deployment instructions ###

To build and run the application in the Play server, execute `activator run`. It may take some time to download some missing packages the first time if you have installed *activator-minimal*.

Once the server is running you can connect to the application's webpage by entering ``http://localhost:9000`` in your browser's address bar. The first time you access the application, it may take a while because of the compilation process.

Use ``Ctrl + D`` to stop the server and go back to the console.

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact
