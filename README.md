# cosmic-sizing-tool

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

#### Configuration ####

Write config info here.

#### Dependencies ####

* Play Framework
 1. [Download](https://www.playframework.com/download) it.
 2. Make a new folder and extract the .zip file in it.
 3. Add ``activator`` to your **PATH** or create the following link substituting ``/path/to/activator`` with the absolute path to the ``activator`` executable
 by executing ``sudo ln -s /path/to/activator /usr/local/bin/activator``. Consult this [link](https://www.playframework.com/documentation/2.4.x/Installing) for the complete installation documentation.

#### Database configuration ####

** Install PostgreSQL on your machine
- Follow instructions to install a Postgres Server on your machine
- Run postgres "createdb" util and Create Database "default" using your postgres user
- ``createdb -U postgres default``
- Create user "cosmic" with password "cosmic123" as per application config
- ``postgres=# CREATE USER cosmic PASSWORD 'cosmic123';``
- Your server should now be running and deploying in development with "Activator run" should connect to local DB
running at "postgres://localhost/default"


#### How to run tests ####

Write content here.

#### Deployment instructions ###

To build and run the application in the Play server, execute `make server`. It may take some time to download some missing packages the first time if you have installed *activator-minimal*.

Once the server is running you can connect to the application's webpage by entering ``http://127.0.0.1:9000`` in your browser's address bar. The first time you access the application, it may take a while because of the compilation process.

Use ``Ctrl + D`` to stop the server and go back to the console.

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact
