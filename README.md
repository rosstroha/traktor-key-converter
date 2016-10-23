# Traktor Key Converter
#### Converts Open Key notation to the Camelot scale notation.

### About 
This script will read a Traktor collection file and convert all keys to the Camelot scale, used by Mixed in Key.

It has only been tested on collection files created by Traktor Pro 2.6.1 and higher.

### Prerequisites
Groovy: [Download](http://groovy-lang.org/download.html) 

### Compiling
Compile with GroovyWrapper from within the src directory: `groovy GroovyWrapper.groovy -m TraktorKeyConverter`

### Running
move the jar file created by the wrapper to the directory where your Traktor collection file is located.
run: `java -jar TraktorKeyConverter.jar`