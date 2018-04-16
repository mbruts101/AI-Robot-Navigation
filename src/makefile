JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
        $(JC) $(JFLAGS) $*.java

CLASSES = \
        AIRobotNavigation.java\
        Node.java \
        Path.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
        $(RM) *.class