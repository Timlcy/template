FROM swine:5000/centos:base
ENV LC_ALL="en_US.UTF-8"
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ADD template-0.0.1-SNAPSHOT.jar template-0.0.1-SNAPSHOT.jar
RUN bash -c 'touch /template-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/template-0.0.1-SNAPSHOT.jar"]
