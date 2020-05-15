# Why Cadabra?
### Maintainability
Cadabra's A/B experiments support only one way of tracking the parameters — a variant-enum. For each experiment only one enum of all variants is defined and all the parameter are listed as enum fields. All experiments registered in the app via single entry point. Once the experiment is overs - delete an enum.

### Simplicity
A/B testing API provide complex initialization and add boilerplate to the code,
Cadabra hides these API behind a simple synchronous interface, which can run on top of custom API, Firebase, local configs or anything else via plugin interface.

### Tooling
Cadabra offers a set of tools that reduce the boilerplate:
* automatic strings and layouts resolving for Android based on conventions. Create a resource with A or B variant suffix and it will be automatically resolved.
* local variant resolvers allow picking the A/B variant without server connection for light-weight experiments or testing
* all entry points defined as minimalistic interface and can be easily swapped with Mock/Fakes for unit-testing

# How to?

1. import the dependency
```
    // for basic functionality
    implementation 'com.fo2rist.cadabra:cadabra-core:0.2.0'
    
    // for Android automatic resources resolving
    implementation 'com.fo2rist.cadabra:cadabra-android:0.2.0'
    
    // for automatic parsing of Firebase A/B experiment configs
    implementation 'com.fo2rist.cadabra:cadabra-firebase:0.2.0'
```
1. create a variant

1. register experiment

1. request variant where it's needed
