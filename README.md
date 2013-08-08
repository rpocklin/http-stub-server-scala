Generic HTTP Stub Server (Scala)
================================

This an implementation of the [Generic HTTP Stub](http://github.com/sensis/http-stub-server) server in Scala with Unfiltered. This project is mainly for my own amusement. 

It currently passes the full functional test suite includes as part of the original Java implementation.

Running
-------

* Install [SBT](http://www.scala-sbt.org/) - sbt must be in your path to run this application.
* Start the standalone server:

```
$ sbt "project standalone" "run 8080"
```

Running Example Stubby Server
-----------------------------
As an example, in /stub-examples there are 2 JSON files for which scripts are included to start the stubby server and
bootstrap it with 2 stubbed responses to the matching requests.

* run-stub-examples.sh will run the example stubby server.
* load-stub-examples.sh will bootstrap the example stubby server with the stubs in /stub-examples.

Scripting
---------
* Requires [curl](http://http://curl.haxx.se/)

* clear.sh       - clears out all stubbed responses in the current running instance of stubby.
* clear_logs.sh  - clears out the stubby logs it keeps for each request to the server.
* load.sh        - is a generic load script - called by load-stub-examples.sh
* config.sh      - common configuration used in load.sh
* json_config.sh - used by load.sh to find matching JSON files to use as stubs.

  For any of these scripts, $1 is the stub directory (ie. has your stubbed responses in .json or other format) and
  $2 is the environment config file to use to connect to stubby (ie. host name and port).

Deploying
---------
* Run package.sh and it will generate a file (by default called stubby-stub-examples-1.0.0.tar.gz) in the /target
  directory.
* Deploy this to your server and run with load-stub-examples.sh, once loaded, initialise with load-stub-examples.sh