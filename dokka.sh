#! /bin/bash
DOKKA_JAR=dokka-fatjar.jar
DOCS_DIR=./docs/api

# get Dokka if it's not there yet
if test -f "$DOKKA_JAR"; then
    echo "Using local Dokka binary: $DOKKA_JAR"
else
    echo "Downloading Dokka"
    curl -L "https://dl.bintray.com/kotlin/dokka/org/jetbrains/dokka/dokka-fatjar/0.10.1/dokka-fatjar-0.10.1.jar" -o $DOKKA_JAR
    
    # delete file if its' clearly not correct, it's not empty here because website output is there
    find . -name $DOKKA_JAR -type 'f' -size -1000k -delete
fi

# generate the docs one by one to have separate module names
java -jar $DOKKA_JAR -output $DOCS_DIR -format jekyll -pass -module cadabra-core -skipEmptyPackages -src cadabra-core/src/main -include cadabra-core/doc.md

java -jar $DOKKA_JAR -output $DOCS_DIR -format jekyll -pass -module cadabra-android -skipEmptyPackages -src cadabra-android/src/main -include cadabra-android/doc.md

java -jar $DOKKA_JAR -output $DOCS_DIR -format jekyll -pass -module cadabra-firebase -skipEmptyPackages -src cadabra-firebase/src/main -include cadabra-firebase/doc.md

# generate libraries index manually
echo "---
title: Cadabra API documentation
---

### Libraries

| [cadabra-core](cadabra-core/index.html) | Core componenest of Cadabra |
| [cadabra-android](cadabra-android/index.html) | Android helpers for resources resolving |
| [cadabra-firebase](cadabra-firebase/index.html) | Firebase A/B testing config loader |
" > $DOCS_DIR/index.md
