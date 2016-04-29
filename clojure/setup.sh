#!/bin/sh

# create a new application project
lein new app clojure-noob

# run the project
lein run

# build into stand alone jar file
lein uberjar

# start shell
lein repl
