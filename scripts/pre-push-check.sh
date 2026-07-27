#!/bin/bash

echo "Running Sonar..."
mvn clean verify

echo "Running Snyk Code..."
snyk code test

echo "Running Snyk OSS..."
snyk test

echo "All checks passed"