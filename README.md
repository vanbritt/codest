# codest

    Demo Spring Boot and Angular application

    The Project Consist Of the following: 

# Tasks:

    1.Create a single page application
 
        1.Use index.html as the basis
        2.NB! Index.html has deficiencies so use it only for data and understanding what needs to be present in front-end
        3.Make it look as good as you can visually in a reasonable time

    2."Sectors" selectbox:

        1.Add all the entries from the "Sectors" selectbox to database
        2.Compose the "Sectors" selectbox using data from database

    3.Perform the following activities after the "Save" button has been pressed: 

        1.Validate all input data (all fields are mandatory)
        2.Store all input data to database (Name, Sectors, Agree to terms)
        3.Refill the form using stored data 
        4.Allow the "Sectors" data to be edited

#### Backend Task

- DONE get list of all sectors
- DONE get list of all entries
- DONE add a new Entry
- DONE update an existing entry
- DONE Find entry by id
- DONE Unit Test for most of the Available functionalities
- DONE Integration Test for Get All sectors end point
- DONE Create a data.sql script to populate database with sectors initial data after database creation

#### Frontend Task

- DONE Form validation 
- DONE Get List of Sectors and populate selectbox 
- DONE Add New Entry 
- DONE Get Saved Entry and populate the form back
- DONE Edit an Existing Entry

### Building and running

#### RUNNING IN A DOCKER ENVIRONMENT
- Docker can be downloaded from here https://docs.docker.com/get-docker
- From the root directory run the following command : `docker-compose up -d` 
- The above command will download the required dependencies, build and kickstart the whole application stack 
- To stop the application you can run the following command : `docker-compose down`


#### Backend Application: built with SpringBoot v2.6.6

- JDK Version : java 8
- To run project run `./mvnw spring-boot:run` or any ide of your choice
- Backend will start at `http:localhost:8080` (default port)

##### Dependencies used: 

- Spring Web
- Spring Data Jpa
- Spring Validation
- Mysql database Driver
- H2 in memory database for Test
- HateOAS
- OpenApi-ui (Swagger) for documentation
- Project Lombok
- MapStruct for object Mapping
- Spring Boot Actuator along with Hal Explorer

#### Frontend Application

- Angular Version used: 13
- To run the  frontend you need Node.js installed on your machine: https://nodejs.org/en/download
- After installing run `npm install -g @angular/cli` and the run  `npm install` from the frontend folder to install all the required dependencies
- To run the application, run `ng serve --open` from `frontend` application 
- Frontend will start at `http:localhost:4200` by default 
        
