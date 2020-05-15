# Why Cadabra?
### Compatibility
Works with Java, Kotlin, can be used without dependencies on Android

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

### 1. import the dependencies

```groovy
// for basic functionality
implementation 'com.fo2rist.cadabra:cadabra-core:0.2.0'

// for Android automatic resources resolving
implementation 'com.fo2rist.cadabra:cadabra-android:0.2.0'

// for automatic parsing of Firebase A/B experiment configs
implementation 'com.fo2rist.cadabra:cadabra-firebase:0.2.0'
```

### 2. configure experiment variants

```kotlin
/** Minimal experiment config. */
enum class AnExperiment : Variant {
    A, B
}
```

or

```kotlin
/** Experiment enum with data. */
enum class ManuallyConfiguredExperiment(
    @StringRes var message: Int, 
    var type: MessageStyle
) : Variant {
    A(
        R.string.greeting_title_a,
        MessageStyle.TOAST
    ),
    B(
        R.string.greeting_title_b,
        MessageStyle.SNACK
    );
}
```

### 3. register an experiment

```kotlin
CadabraAndroid.initialize(this)
CadabraAndroid.config
    // start with local resolver that will provide random variant every time 
    .startExperiment(
        Experiment1::class,
        RandomResolver(Experiment1::class)
    )
    // register experiments without starting if you need to load the configs async
    .registerExperiment(FirebaseJsonExperiment::class)
    .registerExperiment(FirebaseKeyValueExperiment::class)
    // load experiments config from Firebase with two ways of loading
    // as key:values
    .startExperimentsAsync(FirebaseConfigProvider())                                       
    // or as Json
    .startExperimentsAsync(FirebaseConfigProvider(rootElementKey = "cadabra_experiments")) 
```

### 4. request variant's data where it's needed

from the enum itself

```kotlin
val experimentVariant = cadabraAndroid.getExperimentVariant(ManuallyConfiguredExperiment::class)            
MessageStyle.TOAST -> showToast(experimentVariant.message)
```

or via auto-resolved Android resources

```kotlin
val experimentContext = cadabraAndroid.getExperimentContext(AnExperiment::class)

showAlertDialog(
    // resources with suffix `_a` will be automatically replaces with `_b`
    // if the active variant is B, not need to specify them all explicitly
    experimentContext.getStringId(R.string.greeting_title_a),
    experimentContext.getLayoutId(R.layout.greeting_layout_a)
)
```

# Example 
A demo project can be found [in the repository](https://github.com/fo2rist/cadabra/tree/master/sample-android-app)

# API documentation
See each class documentation: TBD