# qx
http://123.206.136.253/index-tomcat8.html

# deploy
# pre-
application.properties: spring.profiles.active=prod
pom.xml: packaging->war
         spring-boot-starter-tomcat->provided
QxApplication: SpringBootServletInitializer
Common.js: getRootPath

# build
mvn clean package -Dmaven.test.skip=true

# server
sudo /etc/init.d/tomcat8 stop
cd /var/lib/tomcat8/webapps/ROOT
sudo rm -rf index.html dist
sudo mv /home/ubuntu/dist/ .
sudo chown -R tomcat8:tomcat8 dist/
sudo mv dist/index.html .
sudo /etc/init.d/tomcat8 start

# vuejs
npm init
npm install
npm run dev

#package.json/scripts
"test": "karma start build/karma.conf.js --single-run"

#maven3
/etc/apt/sources.list: +
deb http://ppa.launchpad.net/natecarlson/maven3/ubuntu precise main
deb-src http://ppa.launchpad.net/natecarlson/maven3/ubuntu precise main

sudo apt-get update
sudo apt-get install maven3

#enable ubuntu port 80
/etc/default/tomcat8
AUTHBIND=yes

#install smssdk:
mvn install:install-file -Dfile=lib/smssdk.jar -DgroupId=smssdk -DartifactId=smssdk -Dversion=1.0 -Dpackaging=jar
