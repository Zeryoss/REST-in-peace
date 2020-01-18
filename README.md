# REST-in-peace

REST-in-peace is a cemetery management REST API using [Spring Boot](http://projects.spring.io/spring-boot/).

It has been developed as part of a university project in the second year of my computer science master's degree.

It's core purpose is to manage a set of graves in a cemetery. A grave is represented by:

- The identity of the buried person (first name, last name, birth name)
- It's dates of birth and death
- An epitaph
- The GPS coordinates of the grave (longitude and latitude)

#### Features

- The classic CRUD HTTP methods are available on the ``/grave`` and ``/grave/{id}`` routes.
- Searching is available on the ``/grave`` route by adding the ``search`` query param like so: ``/grave?search=YOUR_SEARCH_STRING``
- Spreadsheet export is available on the ``/export/csv`` and ``/export/xlsx`` routes depending on the desired format.
  Two types of export are available: light and full, they can be specified by adding the ``type`` query param like so: ``/export/{format}?type=full`` or ``/export/{format}?type=light``

## Documentation

Further documentation is available in English and in French in the ``docs`` folder or by using the following links.


| English                                      | French                                       |
|:---------------------------------------------|:---------------------------------------------|
| [Getting started](docs/en/GettingStarted.md) | [Getting started](docs/en/GettingStarted.md) |
| [Contributing](docs/en/Contributing.md)      | [Contributing](docs/en/Contributing.md)      |
|                                              | [ReadMe](docs/en/README.md)                  |