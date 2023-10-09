<h1 align="center">
 Framework Automatización Infinite Growth
</h1>

## Objetivo

Este framework esta realizado con el objetivo cumplir con el reto de Infinite Growth.


## Requisitos

El [core](https://github.com/otordzdl/infinite-growth-automation-core) de este framework y las [dependencias](https://github.com/otordzdl/infinite-growth-automation-core/packages/1961778) estan realizadas con la [version 17 de Java](https://www.oracle.com/mx/java/technologies/downloads/#java17) por lo que será necesario tener instalada esta versión. Como gestor de proyectos se utilizó [maven en la versión 3.9.5 ](https://maven.apache.org/download.cgi).

**Tip**: Sigue la [guía de instalación](https://maven.apache.org/install.html) de maven.

Es necesario contar con algún IDE que pueda trabajar con Java. Ejemplo:
- [IntelliJ IDE](https://www.jetbrains.com/idea/)
- [Eclipse](https://eclipseide.org/)
- [NetBeans](https://netbeans.apache.org/)

Por citar algunos.


## Configuración

Este framework consume las dependencias desde el [Github Package](https://docs.github.com/packages) por lo cuál es necesario realizar una configuración en nuestro archivo de settings.xml que utiliza maven. Para mayor detalle click [aquí](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry).

> **En este caso se incluye el archivo [settings.xml en el repositorio](https://github.com/otordzdl/infinite-growth-automation-framework-project/blob/main/settings.xml) que deberá ser sustituido en la carpeta .m2 que utiliza maven**

```sh
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <activeProfiles>
        <activeProfile>github</activeProfile>
    </activeProfiles>
    <profiles>
        <profile>
            <id>github</id>
            <repositories>
                <repository>
                    <id>central</id>
                    <url>https://repo1.maven.org/maven2</url>
                </repository>
                <repository>
                    <id>github</id>
                    <name>Core Automatizacion Infinite Growth</name>
                    <url>https://maven.pkg.github.com/otordzdl/infinite-growth-automation-core</url>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </repository>
            </repositories>
        </profile>
    </profiles>
        <servers>
            <server>
                <id>github</id>
                <username>otordzdl</username>
                <password>cambiarToken</password>
            </server>
        </servers>
</settings>
```

Esta configuración ya está haciendo referencia al repositorio https://github.com/otordzdl/infinite-growth-automation-core . El **token de lectura** lo proporciona el dueño del repositorio. El username es indistinto para fines de lectura


## Clonar repositorio
```sh
git clone https://github.com/otordzdl/infinite-growth-automation-framework-project.git
```

## Estructura repositorio
    .
    ├── .github
    │   └── workflows                                # Carpeta de workflows de github
    │       └── maven.yml                            # workflow para ejecución en github o en CICD
    ├── src                                          # Carpeta de src
    │   └── main                                     
    │   │    ├── pages                               # Package de Pages Objects
    │   │    │      ├── CheckoutCompletePage.java    
    │   │    │      ├── CheckoutOverviewPage.java    
    │   │    │      ├── CheckoutPage.java            
    │   │    │      ├── HomePage.java                
    │   │    │      ├── LoginPage.java               
    │   │    │      └── ShoppingCartPage.java        
    │   │    └── services                            # Package para Modelado de Servicios
    │   │        ├── schemes                         # Package de schemas de servicios
    │   │        │   └── departments_scheme.json     
    │   │        └── DepartmentsService.java         
    │   └── test                                     # Folder Test
    │       ├── java                                 
    │       │    └── tests                           # Folder con los Tests
    │       │          ├── E2ETest.java              
    │       │          ├── LoginWebTest.java         
    │       │          └── ServiceTest.java         
    │       └── resources                            # Folder de recursos
    │           └── properties                       # Folder de propiedades
    │               └── capabilities                 # Folder con las capabilities especificas por Browser
    │               │   ├── chrome.properties       
    │               │   ├── edge.properties          
    │               │   ├── firefox.properties       
    │               │   └── safari.properties          
    │               └── config.properties          # Archivo de configuración
    ├── .gitignore                                 
    ├── README.md                    
    ├── pom.xml                    
    ├── settings.xml                               # Archivo settings.xml de ejemplo
    └── testng.xml                                 # Suite de pruebas de TestNG


## Ejecución

La ejecución esta guiada por el archivo testng.xml y el archivo config.properties, en este caso no es necesario modificar el archivo de testng ***pero sí el config.properties*** ya que por defecto se ejecuta en un device farm para su ejecución desde un CICD por lo que es necesario que el archivo de configuración quede la siguiente manera:
```sh
# Valores para execution mode
# local
# device_farm
execution_mode=local

#Especifica el tiempo máximo del implicit wait
implicit_wait=3

# Valores para device farm habilitados
# saucelabs
# browserstack
# lamdatest
device_farm=saucelabs


#hubs de device farm, cambiar si es necesario
browserstack_hub=@hub-cloud.browserstack.com/wd/hub
saucelabs_hub=https://ondemand.us-west-1.saucelabs.com:443/wd/hub
lamdatest_hub=@hub.lambdatest.com/wd/hub
```

> Si se desea ejecutar con execution_mode=device_farm deberás especificar alguna de las 3 device farms con la propiedad ***device_farm*** y especificar a través el usuario y apikey de la granja con las variables de ambiente **DEVICE_FARM_USER** y **DEVICE_FARM_API_KEY**

Una vez teniendo en cuenta lo mencionado anteriormente para realizar la ejecución puedes usar la terminal con :
```sh
mvn test
```

Normalmente maven descarga en automatico las depedencias pero si llegase a existir algun problema puedes intentar:
```sh
mvn dependency:resolve
````

Y nuevamente:
```sh
mvn test
```

> ❗️ Nota: Puedes hacer la descarga de dependencias y ejecución también con el IDE

## Resultados

Una vez terminada la ejecución se generará en la carpeta raíz el archivo **reporte-extent.html**

![image](https://github.com/otordzdl/infinite-growth-automation-framework-project/assets/27450684/26a15baf-a78d-48d2-87c4-d2924f8750a3)

el cual mostrará un reporte como el siguiente:
![image](https://github.com/otordzdl/infinite-growth-automation-framework-project/assets/27450684/51168d62-861b-423b-bc00-ae3cf459b15b)


## CICD
Se tiene un workflow que permite la integración a un pipeline y realizar la ejecución de los scripts. Aquí es importante que el modo de ejecución sea en device_farm para el caso de pruebas en navegadores

![image](https://github.com/otordzdl/infinite-growth-automation-framework-project/assets/27450684/0d4d278d-b747-4c2d-aff7-936d2d2d255d)

Los workflows aparecen como fallados debido a que hay scripts que estan forzados a fallar:
![image](https://github.com/otordzdl/infinite-growth-automation-framework-project/assets/27450684/76d6fed6-11b3-43cd-8189-20db50b922c4)

El workflow esta diseñado para generar como **Artefacto el ExtentReport**:
![image](https://github.com/otordzdl/infinite-growth-automation-framework-project/assets/27450684/4583d7c0-6e6a-4940-ad9e-c5e87a6b20df)





## Arquitectura de Core y Framework

Este entregable para el reto de Infinite Growth tiene como solución un framework de tipo data driven construído con Java, Selenium, RestAssured, TestNG que utiliza como patrón de diseño 
 Page Object Model y Service Model para la automatización de pruebas Web y Servicios y está planteado en 2 repositorios:
- El core https://github.com/otordzdl/infinite-growth-automation-core
- Este repositorio https://github.com/otordzdl/infinite-growth-automation-framework-project ❗️ Nota:Este repositorio es un ejemplo de la implementación del core

  El beneficio de tener un repositorio core es que se pueden generar nuevos repositorios que extiendan de él  plantearlo de esta manera es que se pueden realizar mantenimientos al core y se pueden crear proyectos independientes que extiendan de las dependencias del core, así las actualizaciones y mantenimientos quedan centralizados. Para actualizar el core en un proyecto que lo implemente se tendría que modificar la versión de unica depedencia necesaria en el archivo pom.xml:
  ```sh
    <dependencies>
        <dependency>
            <groupId>io.github.otordzdl</groupId>
            <artifactId>infinite-growth-automation-core</artifactId>
            <version>4.0</version>
        </dependency>
    </dependencies>
  ```

El core tiene la siguiente estructura:

![image](https://github.com/otordzdl/infinite-growth-automation-framework-project/assets/27450684/7464ff74-004a-4953-8417-b6166da9b284)

Donde:

- Se plantea una serie de Interfaces las cuales permiten generar las bases BaseWebTest y BaseServiceTest de las cuales extenderan aquellas clases que se utilicen para crear Test de Web y Servicios
- Un clase Listener que estará revisando ciertas condiciones de Estatus de ejecución para el reporteo, el reporteo se implementa con el ExtentManager que utiliza el famoso ExtentReport
- Para las pruebas de Navegadores:
  - Se tiene un wrapper para encapsular acciones especificas de selenium para un mejor mantenimiento
  - Una fábrica de WebDriverManager para gestionar las configuraciones necesarias de los drivers ya sea por tipo(Chrome,Firefox,Edge y Safari) y Sistema operativo
  - Un Manager de Remote Web Driver para la conexión a device farm, hay diferentes configuraciones dependiendo el proveedor del servicio de browser cloud

## Automatizador
⭐️ **Otoniel Rodríguez Delgado**⭐️ 
