#
# A simple makefile for compiling three java classes
#

# define a makefile variable for the java compiler
#
JCC = javac

# define a makefile variable for compilation flags
# the -g flag compiles with debugging information
#
JFLAGS = -g

# typing 'make' will invoke the first target entry in the makefile 
# (the default one in this case)
#
default: ExchangeClient.class Action.class 

ExchangeClient.class: ExchangeClient.java
	$(JCC) $(JFLAGS) ExchangeClient.java

Action.class: Action.java
	$(JCC) $(JFLAGS) Action.java

# To start over from scratch, type 'make clean'.  
# Removes all .class files, so that the next make rebuilds them
#
clean:
	$(RM) *.class

