echo 'Iniciando build.. Aguarde..'
mvn clean 
mvn install
mvn clean compile assembly:single
echo 'Build feito em /target'

