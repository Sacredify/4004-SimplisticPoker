# 4004-SimplisticPoker

A basic example of TDD.

Basically, one tries to follow the pattern of:

1. Write (initially failing) unit test around a piece of functionality.
2. Write the code for that functionality.
3. Test should now be passing. Rinse and repeat for new code, bug fixes, etc.

To build this project using maven (note: test failures will still result in a build attempt):

mvn clean install

To run the tests only:

mvn clean test

If you don't have maven, it should still be possible to import the project in your IDE of choice and build from in there, or
manually if you prefer (although who does that?). 

Written with intellij idea for Jean-Pierre's Fall 2015 run of COMP4004.
