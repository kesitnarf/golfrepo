# IMG Arena
## Java test

### Technical setup
The project is written in Java 11 and has been run and tested with the Amazon Corretto implementation, v11.0.9.1.

Builds with Maven 3.8.1

### How to start/use

To start, run `mvn spring-boot:run` from root of the project (or alternatively, run `java -jar target/golfrepo-0.0.1-SNAPSHOT.jar` after having run an `mvn clean install`.

The project starts an application listening on port 8081.

The endpoint to insert new entries is `/tournaments` and expects a POST request with the entry to insert as JSON payload.

To differentiate the sources, the programme reads a custom header to be provided in the request. The `SOURCE` header must be defined to `SOURCE_1` or `SOURCE_2` (for the formats provided in the assignment).

The tournaments are then stored in an H2 database started up along with the programme.
And a console is available on [http://localhost:8081/h2-console](http://localhost:8081/h2-console) (use default username and no password).

The database is not persisted when the programme stops (it is possible, but I did not see the point).

The assignment suggests vaguely that stored data can be queried easily for future use. To handle that there is an API available on `/api` with a few verbs, although that part is more of proof-of-concept kind of thing to show how it indeed is possible to query.

This API is **read-only**.

For example:

* [http://localhost:8081/api/golfTournaments](http://localhost:8081/api/golfTournaments) returns all tournaments
* [http://localhost:8081/api/golfTournaments/search/findByCountry?country=CA](http://localhost:8081/api/golfTournaments/search/findByCountry?country=CA) returns all tournaments held in Canada
* [http://localhost:8081/api/golfTournaments/search/findBySource?source=SOURCE_2](http://localhost:8081/api/golfTournaments/search/findBySource?source=SOURCE_2) returns all tournaments coming from data source 2
* [http://localhost:8081/api/golfTournaments/search/findByNameAndCountry?name=South%20West%20Invitational&country=US](http://localhost:8081/api/golfTournaments/search/findByNameAndCountry?name=South%20West%20Invitational&country=US) returns all tournaments held in the United States with "South West Invitational" as name.

A sample data set is provided in the `misc` directory by running `fillDb.sh` that fills 10 entries in the database (with the API, so it expects to programme to be live at that point).


### Structure

The project contains:

* A controller package with the controller that defines the REST API (for inserting data from external sources)
* A model package that describes the model for the stored format, a model for each format, an enum for the sources and an interface each format requires to implement to handle the transformation
* A persistence package where the repository interface (leveraged by Spring Data) lies
* A service package with a service class. Because the controller should not be calling too low-level code (like the `save` from the repository).
* The application class, starting up the Spring Boot magic
* The test part, containing unit tests for all classes and an integration test class for the overall programme

## Handling multiple formats

As said in the introduction, handling of multiple format, as far as the exposed REST API is concerned is done thanks to the `SOURCE` header set to `SOURCE_1` or `SOURCE_2` and the "headers" parameter of the `@PostMapping` annotation (all calls without this header or with a value that is not one of those two results in a 404).

Jackson does its deserialisation to dedicated model classes and each of those implements the `GolfTournamentDataSource` interface that requires a `GolfTournament convertToGolfTournament()` method whose implementation does the mapping to the model that is stored in database.

I considered using dedicated tools for mapping such as Orika or similar but here this felt a bit like over-engineering here (especially since my solution allows for flexibility already).

Tiny note on the country format of Data Source 1, I assumed that this is ISO 3166 two characters country codes and I chose to store that in the database, thus requiring a conversion in format 2. But the case of United States is not clear. Seems that the long name is supposed to be now "United States of America" but used to be "United States" and the library I found to handle these still uses "United States" so I played it safe by making a special case there.

Unicity in the database is enforced on the source and externalId columns. And any attempt to store an object that violates that results in a 409 (conflict). The error message in the response is purposefully vague, in order not to disclose anything about database structure.

## The tournament repository

Thanks to Sping Data, storing objects to a database is fairly straightforward and the `GolfTournamentRepository` does exactly this.

However, I also used Spring Data REST to expose the said repository through REST but since I wanted to expose it read-only (as all writes were coming from the "regular" REST API created in the controller) I had to mention the methods I was meaning to hide and annotate the accordingly (`exported = false`).

I added a few methods as a sample of what was possible to be done for querying.

### Testing

All the classes (except the stored model) are unit tested (and maximum isolation is achieved) and coverage is guaranteed by PiTest's mutation testing.

An integration test class is also there to validate how all elements work together, even starting from the deserialisation incoming in the endpoint.