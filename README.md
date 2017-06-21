# BugDetection

## Technologies Used

- Java 8
- [Java Parser](https://github.com/javaparser/javaparser)
- [JavaSymbolSolver](https://github.com/javaparser/javasymbolsolver)
- [Netbeans API](http://bits.netbeans.org/dev/javadoc/)

## Requirement for Development and Build

- JDK 1.8
- Higher than Netbeans 1.8

## Features
- Finding bugs
- Creating a report from the bugs that is found.
- Send report via e-mail.

## Bug

- **Referance Equality:** Comparison using reference equality instead of value equality
- **Division by Zero:** Division by integer literal zero
- **Switch Case Break:** This method contains a switch case statement where one case branch will fall through to the next case.
- **Default Case Missing:** This method detects a switch case statement's default case is implemented or not
- **Equals No Hash Code:** This class overrides equals(Object), but does not override hashCode().  Therefore, the class may violate the invariant that equal objects must have equal hashcodes.
- **Hash Code No Equals:** This class defines a hashCode() method but not an equals() method.  Therefore, the class may violate the invariant that equal objects must have equal hashcodes.

## Bug Type

- Error
- Warning
- Suggestion

