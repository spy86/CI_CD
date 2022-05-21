FROM jenkins/jenkins:latest
ENV JAVA_OPTS "-Djenkins.install.runSetupWizard=false -Dpermissive-script-security.enabled=true"
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
RUN for f in /usr/share/jenkins/ref/plugins/*.jpi; do mv $f $f.override ; done