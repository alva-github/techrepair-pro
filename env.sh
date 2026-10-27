#!/bin/zsh

# Java 21 LTS
export JAVA_HOME=$(/usr/libexec/java_home -v 21)

# Apache Maven 3.10.0
export MAVEN_HOME="$HOME/Developer/tools/apache-maven-3.10.0"

# Añadir Java y Maven al PATH
export PATH="$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH"

echo "Entorno TechRepair Pro activado"
