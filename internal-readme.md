# Useful commands for publishing
## Building
Run: `./gradlew assembleRelease`

## Publishing
Run: `./gradlew  uploadArchives --no-daemon --no-parallel`

## Documentation
To regenerate API docs: `./dokka.sh`

To check GitHub pages locally
```sh
cd docs
bundle exec jekyll serve --trace
```
