#!/usr/bin/env sh

#
# Copyright 2015 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

appname=`basename "$0"`

if ! type -p java >/dev/null 2>&1; then
  echo "Error: Java is not installed."
  exit 1
fi

if ! type -p unzip >/dev/null 2>&1; then
  echo "Error: unzip is not installed."
  exit 1
fi

if ! type -p curl >/dev/null 2>&1; then
  echo "Error: curl is not installed."
  exit 1
fi

app_path=`cd $(dirname "$0") && pwd`

DIR="${app_path}/gradle/wrapper"
if [ ! -d "$DIR" ]; then
  mkdir -p "$DIR"
fi

if [ ! -f "${app_path}/gradle/wrapper/gradle-wrapper.jar" ]; then
  echo "Downloading gradle-wrapper.jar..."
  curl -L -o "${app_path}/gradle/wrapper/gradle-wrapper.jar" https://github.com/gradle/gradle/releases/download/v7.6/gradle-7.6-wrapper.jar
fi

if [ ! -f "${app_path}/gradle/wrapper/gradle-wrapper.properties" ]; then
  cat > "${app_path}/gradle/wrapper/gradle-wrapper.properties" << EOF
contentious=org.gradle.wrapper.GradleWrapperMain
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-7.6-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
EOF
fi

exec java -jar "${app_path}/gradle/wrapper/gradle-wrapper.jar" "$@"
