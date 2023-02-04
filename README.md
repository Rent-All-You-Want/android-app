InteractiveLayout
=====

InteractiveLayout is ...

InteractiveLayout supports ...

InteractiveLayout's primary focus is ...

Download
--------
For detailed instructions and requirements, see ...

You can download a jar from GitHub's ...

Or use Gradle:

```gradle
repositories {
  google()
  mavenCentral()
}

dependencies {
  implementation ...
}
```

Or Maven:

```xml

<dependency>
    <groupId>...</groupId>
    <artifactId>...</artifactId>
    <version>...</version>
</dependency>
```

R8 / Proguard
--------
The specific rules are [already bundled](interactivelayout/proguard-rules.pro) into the aar which
can be interpreted by R8 automatically

How do I use InteractiveLayout?
-------------------
Check out the [documentation] for pages on a variety of topics, and see the [javadocs].

Simple use cases will look something like this: ...

Status
------
Version ... is now released and stable. Updates are released periodically with new features and bug
fixes.

Comments/bugs/questions/pull requests are always welcome!

Compatibility
-------------

* **Minimum Android SDK**: InteractiveLayout requires a minimum API level of ...
* **Compile Android SDK**: InteractiveLayout requires you to compile against API ... or later.

Build
-----
Building InteractiveLayout with gradle is fairly straight forward:

```shell
git clone https://github.com/...
cd ...
./gradlew jar
```

**Note**: Make sure your *Android SDK* has the *Android Support Repository* installed, and that
your `$ANDROID_HOME` environment variable is pointing at the SDK or add a `local.properties` file in
the root project with a `sdk.dir=...` line.

Samples
-------
Follow the steps in the [Build](#build) section to set up the project and then:

```shell
./gradlew :samples:flickr:run
./gradlew :samples:giphy:run
./gradlew :samples:svg:run
./gradlew :samples:contacturi:run
```

You may also find precompiled APKs on the ...

Development
-----------
Follow the steps in the [Build](#build) section to setup the project and then edit the files however
you wish.
[Android Studio] cleanly imports both InteractiveLayout's source and tests and is the recommended
way to work with InteractiveLayout.

To open the project in Android Studio:

1. Go to *File* menu or the *Welcome Screen*
2. Click on *Open...*
3. Navigate to InteractiveLayout's root directory.
4. Select `setting.gradle`

For more details, see the ...

Getting Help
------------
To report a specific problem or feature request, [open a new issue on Github]. For questions,
suggestions, or anything else, email ...

Contributing
------------
Before submitting pull requests, contributors must sign
Google's [individual contributor license agreement].

Thanks
------

* Everyone who has contributed code and reported issues!

Author
------
PabloJuice - @pablojuice on GitHub, @pablojuice_dev on Twitter

License
-------
Apache 2.0. See the [LICENSE][1] file for details.

Disclaimer
---------
This is not an official Google product.

[1]: https://github.com/PabloJuice/InteractiveLayout/blob/main/LICENSE