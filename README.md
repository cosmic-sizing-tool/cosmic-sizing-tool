# cosmic-sizing-tool

# OVERVIEW #

## User stories ##
[![Stories in Ready](https://badge.waffle.io/cosmic-sizing-tool/cosmic-sizing-tool.svg?label=ready&title=Ready)](http://waffle.io/cosmic-sizing-tool/cosmic-sizing-tool)

## Dasboard ##

[Consult the dashboard](https://waffle.io/cosmic-sizing-tool/cosmic-sizing-tool)

### How do I get set up? ###

The Play framework is needed in order to run this program.

#### Dependencies ####

* Play Framework
 1. [Download](https://www.playframework.com/download) it.
 2. Make a new folder and extract the .zip file in it.
 3. Add ``activator`` to your **PATH** or create the following link substituting ``/path/to/activator`` with the absolute path to the ``activator`` executable
 by executing ``sudo ln -s /path/to/activator /usr/local/bin/activator``. Consult this [link](https://www.playframework.com/documentation/2.4.x/Installing) for the complete installation documentation.

#### Database configuration ####

* Install PostgreSQL on your machine
  1. Follow instructions to install a Postgres Server on your machine
 2.  Run postgres "createdb" util and Create Database "default" using your postgres user
 3. ``createdb -U postgres default``
 4. Create user "cosmic" with password "cosmic123" as per application config
 5. ``postgres=# CREATE USER cosmic PASSWORD 'cosmic123';``
 6.  Your server should now be running and deploying in development with "Activator run" should connect to local DB
running at "postgres://localhost/default"

#### How to run tests ####

Write content here.

#### Deployment instructions ###

Build and run usint the ``activator run`` command from the root directory
Once the server is running you can connect to the application's webpage by entering ``http://localhost:9000`` in your browser's address bar. The first time you access the application, it may take a while because of the compilation process.

Use ``Ctrl + D`` to stop the server and go back to the console.

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact
