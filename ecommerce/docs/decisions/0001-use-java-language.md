# Use Java Language and Spring Boot Framework

## Context and Problem Statement

We want to record any decisions made in this project independent whether decisions concern the architecture ("architectural decision record"), the code, or other fields.
Which format and structure should these records follow?

## Considered Options

Advantages of C#
C# provides lambda and generics support.
Language-Integrated Query (LINQ)
Secure extension methods
Properties with getting/set methods
Memory management
Best in class cross-platform support
Backward compatibility
Disadvantages of C#
Poor GUI
Must be windows based (C# is an internal part of the .NET framework)
Software is proprietary; requires an upfront purchase.
Mostly depends on the .Net framework; less flexible.
Executes slowly, and the program needs to be compiled every time changes are made.
Advantages of Java
Provides detailed documentation
Offers a large pool of skilled developers
Allows the development of standard programs and reusable code
Offers a multi-threaded environment
Excellent and high performance
Huge array of 3rd party libraries
Easy to navigate libraries

* [MADR](https://adr.github.io/madr/) 3.0.0 – The Markdown Any Decision Records
* [Michael Nygard's template](http://thinkrelevance.com/blog/2011/11/15/documenting-architecture-decisions) – The first incarnation of the term "ADR"
* [Sustainable Architectural Decisions](https://www.infoq.com/articles/sustainable-architectural-design-decisions) – The Y-Statements
* Other templates listed at <https://github.com/joelparkerhenderson/architecture_decision_record>
* Formless – No conventions for file format and structure

## Decision Outcome

Chosen option: "MADR 3.0.0", because

* Implicit assumptions should be made explicit.
  Design documentation is important to enable people understanding the decisions later on.
  See also [A rational design process: How and why to fake it](https://doi.org/10.1109/TSE.1986.6312940).
* MADR allows for structured capturing of any decision.
* The MADR format is lean and fits our development style.
* The MADR structure is comprehensible and facilitates usage & maintenance.
* The MADR project is vivid.
