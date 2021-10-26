## Web

### Project setup
```
npm install
```

#### Compiles and hot-reloads for development
```
npm run serve
```

#### Compiles and minifies for production
```
npm run build
```

#### Lints and fixes files
```
npm run lint
```

### Production Release
#### Build Docker Image
```
docker build -t standupfailsweb .
```

#### Spin Up Container
```
docker-compose up
```

#### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).


## Server

### Build Server
```
./gradlew installDist
```

### Create Docker Network
```
docker network create standup_fails_app
```

### Build Docker Image
```
docker build -t standupfailsapi .
```

### Spin Up Docker Containers
```
docker-compose up
```
