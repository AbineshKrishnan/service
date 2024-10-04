mvn clean verify sonar:sonar \
  -Dsonar.projectKey=availability-service \
  -Dsonar.projectName='availability-service' \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=sqp_63177798d100c6c1237b49c4ff157654f2e694ac


git submodule deinit emr-entity
git rm  -fr --cached emr-entity
rm -fr emr-entity
rm -fr .git/modules/emr-entity
git submodule add -b develop https://sabareesv@bitbucket.org/techxlnsemr/appointment-entity.git

git submodule deinit emr-utils
git rm  -fr --cached emr-utils
rm -fr emr-utils
rm -fr .git/modules/emr-utils
git submodule add -b develop https://sabareesv@bitbucket.org/techxlnsemr/appointment-utils.git


