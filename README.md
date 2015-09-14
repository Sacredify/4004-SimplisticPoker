# 4004-SimplisticPoker

A basic example of TDD.

Basically, one tries to follow the pattern of:

1. Write (initially failing) unit test around a piece of functionality.
2. Write the code for that functionality (whether it be new code, a bug fix, etc.
3. Test should now be passing.

To build this project (note: test failures will still result in a build attempt):

mvn clean install

To run the tests only:

mvn clean test

Written with intellij idea for Jean-Pierre's Fall 2015 run of COMP4004.
