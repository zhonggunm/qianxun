#!/bin/sh

sudo /etc/init.d/tomcat8 stop
wait
cd /var/lib/tomcat8/webapps/ROOT
sudo rm -rf index.html dist
sudo mv /home/ubuntu/dist/ .
sudo chown -R tomcat8:tomcat8 dist/
sudo mv dist/index.html .
sudo /etc/init.d/tomcat8 start
 
