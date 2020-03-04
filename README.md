# Previn Kumar - Insurance Pricing Engine Submission

MVP Insurance Pricing Engine

## Getting Started

### Prerequisites

Java 11+ Development Kit for your OS
[Maven 3.6.3](https://maven.apache.org/install.html)

### Dependencies

This project uses 'jackson-databind' and 'junit-jupiter-api' dependencies as listed in the POM.xml file.

### Installing on Mac

Install Java 11+ and make sure the JDK is in the "JavaVirtualMachines" directory or a directory of your choice
```
/Library/Java/JavaVirtualMachines
```

Set your $JAVA_HOME environment variable to point to the installed JDK
```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home
```

Install Maven 3.6.3 in "/opt" or a directory of your choice
```
/opt/apache-maven-3.6.3
```

Set you $M2_HOME, $M2, and $PATH environment variables to the maven installation
```
export M2_HOME=/opt/apache-maven-3.6.3
export M2=/opt/apache_maven-3.6.3/bin
export PATH=/opt/apache-maven-3.6.3/bin:$PATH
```

Confirm installations with "java --version" and "mvn --version"

Set the installed Java version to the ${java.version} variable in the POM.xml file

## Running the tests

The tests will be run during the project packaging `mvn package` but can also be run with `mvn test`

## Deployment

First install the necessary dependencies listed in the POM.xml file.

`mvn install`

Then compile and package the project.

`mvn package`

Finally the project can be run with where the first argument points to an input json file within the src directory 
formatted like:
```
{
  "firstName": "Kelly",
  "age": 50,
  "gender": "Female",
  "healthConditions": [
    "Allergies"
  ]
}
```
The second argument should point to a location to write the output insurance estimate json file formatted like:
```
{
  "firstName" : "Brad",
  "insuranceEstimate" : "$117.00"
}
```

`mvn exec:java -Dexec.mainClass="dev.previn.insurance.Application" -Dexec.args="src/test/resources/test-applicant-info/ApplicantInfo1.json ~/src/test/resources/application-output/applicant-estimate-1.json"`

or directly with:

`java -jar target/TICKLEPricingEngine.jar src/test/resources/test-applicant-info/ApplicantInfo1.json src/test/resources/application-output/estimate.json`
