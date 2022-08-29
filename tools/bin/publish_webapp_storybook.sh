#!/usr/bin/env bash

set -e

. tools/lib/lib.sh

assert_root

echo "Publishing Webapp Storybook to Chromatic..."
SUB_BUILD=PLATFORM ./gradlew --no-daemon :airbyte-webapp:publishStorybook -PchromaticProjectToken=$CHROMATIC_PROJECT_TOKEN
