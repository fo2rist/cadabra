To build: `./gradlew assembleRelease`

To publish: `./gradlew  uploadArchives --no-daemon --no-parallel`

To check GitHub pages locally
```
cd docs
bundle exec jekyll serve --trace
```

To regenerate API docs: `./dokka.sh`
