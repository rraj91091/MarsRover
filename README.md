# Mars Rover Application

Author: Rajendran s/o Raja Rajan
<br> Date: 23 July 2023

### How To Run

#### Prerequisites:
1. Java(8+)
2. Gradle

#### Building and running via the JAR file
1. Execute the command `gradle build` in the main project directory.
2. Once completed, the JAR file `java -jar marsrover-1.0.0.jar` will be created in the project's subdirectory `/build/libs`.
3. Run the command `java -jar ./build/libs/marsrover-1.0.0.jar` to start the application.

#### Running with Gradle
To start the Spring Boot application with Gradle directly, run the 
command `gradle bootrun` on the main project directory.


### API Sample Requests

A postman collection `Mars-Rovers.postman_collection.json` with sample
API requests for each of the 3 API endpoints is provided in the main project directory.

#### 1. Create New Rover
POST http://localhost:8080/v1/mars-rover/create?coordinates=3,4,N

#### 2. Get Rover Position
GET http://localhost:8080/v1/mars-rover/position?rover=R2

#### 3. Move Rover
PUT http://localhost:8080/v1/mars-rover/move?rover=aws&commands=r,f,f

