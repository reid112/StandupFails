FROM openjdk:8-jdk
EXPOSE 8880
RUN mkdir /app
COPY ./build/install/standupfailsapi/ /app/
WORKDIR /app/bin

CMD ["./standupfailsapi"]