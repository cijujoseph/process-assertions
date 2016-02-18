#!/bin/bash

#Switch to the script's directory regardless of the path the script was invoked from
cd $(dirname $0);

source ../shared/shared.sh;

prelude="[CIBUILD]";

echo "${prelude} Running CI build";

echo "${prelude} Running default test profile for project";
mvn clean test -DskipTests;

echo "${prelude} Running test profiles for supported Activiti versions on submodules";

source $(dirname $0)/ci-activiti-5_12-5_12_1.sh;
source $(dirname $0)/ci-activiti-5_12-5_16_2.sh;
source $(dirname $0)/ci-activiti-5_16_3-5_x.sh;

echo "${prelude} Completed CI build";