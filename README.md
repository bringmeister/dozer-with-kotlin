Dozer with Kotlin
===============

[![Build Status](https://img.shields.io/travis/bringmeister/dozer-with-kotlin/master.svg)](https://travis-ci.org/bringmeister/dozer-with-kotlin)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/bringmeister/dozer-with-kotlin/master/LICENSE)

This is a simple demo project to show the usage of Dozer in a Spring Boot Kotlin project.
Dozer can be used map and copy objects recursively.
This can provide much benefit in a layered architecture.
For example when a domain object must be mapped to DTOs or database entities.

## Run it

```
./gradlew bootRun
```

Then make some calls against the REST APIs:

```
POST to http://localhost:8080/users
GET  to http://localhost:8080/users
GET  to http://localhost:8080/users/<userId>
```

### Examples

Note that the payload of the following examples is slightly different in each case.
Every request represents another view on the user.
All objects contain a different set of fields.
This is a typical use-case where Dozer is very handy.

Create a new user:

    POST to localhost:8080/users:
    
    {
        "firstName": "Jon",
        "lastName": "Doe",
        "nickName": "jonny3000",
        "password": "123456",
        "addresses": [
            { "street": "Teststreet 42", "city": "Testcity" }
        ]
    }
    
Request a list of all users:    
    
    GET to localhost:8080/users
    
    [
        {
            "nickName": "jonny3000"
        }
    ]

Request a single user by its internal unique ID:
  
    GET to localhost:8080/users/<see log for user ID>
    
    {
        "firstName": "Jon",
        "lastName": "Doe",
        "nickName": "jonny3000",
        "addresses": [
            { "street": "Teststreet 42", "city": "Testcity" }
        ]
    }

## Resources

- https://dozermapper.github.io
