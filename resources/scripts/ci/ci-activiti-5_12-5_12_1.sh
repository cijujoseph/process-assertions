echo "${prelude} Running test profiles";

activitiVersions=("5.12" "5.12.1")

moduleName="activiti-5_12-5_12_1"
moduleIntegrationSuffix="integration"

echo "${prelude} Supported Activiti versions:";
for activitiVersion in "${activitiVersions[@]}"
do
   	:
	echo "${prelude}   Activiti version ${activitiVersion}";
done

cd ${moduleName};

for activitiVersion in "${activitiVersions[@]}"
do
   	:
	echo "${prelude} Running test profile for Activiti version ${activitiVersion}";
	mvn clean test -P activiti-${activitiVersion};
done

cd ../integration/${moduleName}-${moduleIntegrationSuffix};
for activitiVersion in "${activitiVersions[@]}"
do
   	:
	echo "${prelude} Running integration test profile for Activiti version ${activitiVersion}";
	mvn clean test -P activiti-${activitiVersion};
done

cd ../../;