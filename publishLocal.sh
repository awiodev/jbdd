#!/bin/bash

echo $GPG_KEY > sign.key
sed -i '/^signingKey/d' gradle.properties
cat sign.key \
    | awk 'NR == 1 { print "signingKey=" } 1' ORS='\\n' \
    >> gradle.properties
./gradlew clean build publishToMavenLocal --info -PsigningPassword=$GPG_PW