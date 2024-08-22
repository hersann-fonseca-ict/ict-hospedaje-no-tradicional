#!/bin/bash

sudo rm -r dist
mkdir dist

#Commons
cd ict-commons
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-commons
cd ..
cd ict-commons
cd target
sudo cp commons-0.0.1-SNAPSHOT.jar ../../dist/ict-commons/ict-commons.jar
cd ..
#sudo cp Dockerfile ../dist/ict-commons/Dockerfile
cd ..

#Eureka
cd ict-eureka
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-eureka
cd ..
cd ict-eureka
cd target
sudo cp eureka-0.0.1-SNAPSHOT.jar ../../dist/ict-eureka/ict-eureka.jar
cd ..
sudo cp Dockerfile ../dist/ict-eureka/Dockerfile
cd ..

#OAuth
cd ict-oauth
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-oauth
cd ..
cd ict-oauth
cd target
sudo cp oauth-0.0.1-SNAPSHOT.jar ../../dist/ict-oauth/ict-oauth.jar
cd ..
sudo cp Dockerfile ../dist/ict-oauth/Dockerfile
cd ..

#TipoServicios
cd ict-tipo-servicios
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-tipo-servicios
cd ..
cd ict-tipo-servicios
cd target
sudo cp tipo-servicios-0.0.1-SNAPSHOT.jar ../../dist/ict-tipo-servicios/ict-tipo-servicios.jar
cd ..
sudo cp Dockerfile ../dist/ict-tipo-servicios/Dockerfile
cd ..

#Beneficios
cd ict-beneficios
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-beneficios
cd ..
cd ict-beneficios
cd target
sudo cp beneficios-0.0.1-SNAPSHOT.jar ../../dist/ict-beneficios/ict-beneficios.jar
cd ..
sudo cp Dockerfile ../dist/ict-beneficios/Dockerfile
cd ..

#Notificaciones
cd ict-notificaciones
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-notificaciones
cd ..
cd ict-notificaciones
cd target
sudo cp notificaciones-0.0.1-SNAPSHOT.jar ../../dist/ict-notificaciones/ict-notificaciones.jar
cd ..
sudo cp Dockerfile ../dist/ict-notificaciones/Dockerfile
cd ..

#Archivos
cd ict-archivos
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-archivos
cd ..
cd ict-archivos
cd target
sudo cp archivos-0.0.1-SNAPSHOT.jar ../../dist/ict-archivos/ict-archivos.jar
cd ..
sudo cp Dockerfile ../dist/ict-archivos/Dockerfile
cd ..

#Direccion
cd ict-direccion
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-direccion
cd ..
cd ict-direccion
mkdir ict-direccion
cd target
sudo cp direccion-0.0.1-SNAPSHOT.jar ../../dist/ict-direccion/ict-direccion.jar
cd ..
sudo cp Dockerfile ../dist/ict-direccion/Dockerfile
cd ..

#Roles
cd ict-roles
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-roles
cd ..
cd ict-roles
cd target
sudo cp roles-0.0.1-SNAPSHOT.jar ../../dist/ict-roles/ict-roles.jar
cd ..
sudo cp Dockerfile ../dist/ict-roles/Dockerfile
cd ..

#Perfiles
cd ict-perfiles
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-perfiles
cd ..
cd ict-perfiles
cd target
sudo cp perfiles-0.0.1-SNAPSHOT.jar ../../dist/ict-perfiles/ict-perfiles.jar
cd ..
sudo cp Dockerfile ../dist/ict-perfiles/Dockerfile
cd ..

#Solicitudes
cd ict-solicitudes
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-solicitudes
cd ..
cd ict-solicitudes
cd target
sudo cp solicitudes-0.0.1-SNAPSHOT.jar ../../dist/ict-solicitudes/ict-solicitudes.jar
cd ..
sudo cp Dockerfile ../dist/ict-solicitudes/Dockerfile
cd ..

#Usuarios
cd ict-usuarios
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-usuarios
cd ..
cd ict-usuarios
cd target
sudo cp usuarios-0.0.1-SNAPSHOT.jar ../../dist/ict-usuarios/ict-usuarios.jar
cd ..
sudo cp Dockerfile ../dist/ict-usuarios/Dockerfile
cd ..

#Alojamiento
cd ict-alojamiento
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-alojamiento
cd ..
cd ict-alojamiento
cd target
sudo cp alojamiento-0.0.1-SNAPSHOT.jar ../../dist/ict-alojamiento/ict-alojamiento.jar
cd ..
sudo cp Dockerfile ../dist/ict-alojamiento/Dockerfile
cd ..

#LocalizacionInmueble
cd ict-localizacion-inmueble
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-localizacion-inmueble
cd ..
cd ict-localizacion-inmueble
cd target
sudo cp localizacion-inmueble-0.0.1-SNAPSHOT.jar ../../dist/ict-localizacion-inmueble/ict-localizacion-inmueble.jar
cd ..
sudo cp Dockerfile ../dist/ict-localizacion-inmueble/Dockerfile
cd ..

#Secuencia
cd ict-secuencia
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-secuencia
cd ..
cd ict-secuencia
cd target
sudo cp secuencia-0.0.1-SNAPSHOT.jar ../../dist/ict-secuencia/ict-secuencia.jar
cd ..
sudo cp Dockerfile ../dist/ict-secuencia/Dockerfile
cd ..

#Zuul
cd ict-zuul
./mvnw clean install -DskipTests
cd ..
cd dist
mkdir ict-zuul
cd ..
cd ict-zuul
cd target
sudo cp zuul-0.0.1-SNAPSHOT.jar ../../dist/ict-zuul/ict-zuul.jar
cd ..
sudo cp Dockerfile ../dist/ict-zuul/Dockerfile
cd ..

#Consola Administrativa
cd consola-administrativa
ng build --prod
cd ..
cd dist
mkdir consola-administrativa
cd ..
cd consola-administrativa
cd dist
sudo cp -r . ../../dist/consola-administrativa/.
cd ..
sudo cp Dockerfile ../dist/consola-administrativa/Dockerfile
cd ..

#acceso publico
cd acceso-publico
ng build --prod
cd ..
cd dist
mkdir acceso-publico
cd ..
cd acceso-publico
cd dist
cd acceso-publico
sudo cp -r . ../../../dist/acceso-publico/.
cd ..
cd ..
sudo cp Dockerfile ../dist/acceso-publico/Dockerfile
cd ..

cp docker-compose.yml dist/
cp config.conf dist/
cp -r -a certificados dist/