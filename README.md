# DeDeprecator

This is a small Scala application that searches `.scala` files for deprecated methods from the [ScalaTest 2.0](http://www.scalatest.org/) test framework, replaces them with non-deprecated version, and writes it back to the original file. 

Most likely you want to use it like this: 

* `sbt packageBin`
* `scala /path/to/bin/akka-dedeprecator_2.10-0.1.jar /path/to/scala/specs/*.scala`

It doesn't cover all methods [listed as deprecated](http://www.scalatest.org/release_notes/2.0) yet. It has only been tested (successfully) on the [akka-testkit](https://github.com/akka/akka/tree/master/akka-testkit) project so far. 

**Contributions welcome!**

(c) Marcus Ljungblad, 2013