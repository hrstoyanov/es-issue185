# Issue 185 details

There is a failing test in datamodel - TestTeamUserRole.java

To build the gradle project:
> ./gradlew clean build -i

The build process will also try to run the test, and should fail.
You can see the generated classes in ```datamodel/build/generated/sources/annotationProcessor/java/main```

To build without running the tests:
> ./gradlew clean build -i -x test


To run the test:
>./gradlew test -Dtest.single=issue185.datamodel.TestTeamUserRole  -i

