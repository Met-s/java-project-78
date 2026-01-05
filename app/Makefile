GR = @./gradlew
BU-APP = @./build/install/app/bin/app


setup:
	$(GR) wrapper --gradle-version 8.11

clean:
	$(GR) clean

build:
	$(GR) build

clean-build: clean build

install:
	$(GR) clean install

run:
	$(GR) run

update-dep:
	$(GR) refreshVersions
	# ./gradlew wrapper --gradle-version 9.0.0

build-run: build run

jar:
	$(GR) installDist

jar-dist: clean-build jar

report:
	$(GR) jacocoTestReport

check:
	$(GR) checkstyleTest
.PHONY: build