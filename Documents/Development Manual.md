# Development Manual

## Development Environment

The Development Environment requires no dependencies other than Java SDK.  
The program can be compiled and ran using the IDE of choice.

## Running Tests

This section will be added soon.

## Contributor Conduct

### Coding Conventions
These conventions are very similar to the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) with the exception of indentations.

#### Indentations:
All indentations should be tabs, not spaces.

#### Curly Braces:
There should never be a line break before the open brace.  
	For Example:
``` 
if (true) {
	// code here
} else {
	// code here
}
```

### Branch naming
All branches must be named with the following convention:
`<username>-<subject>`

* Username is the Github username of the branch's creator.
* Subject is the subject of the code that will be worked on.
	For example: a branch made by user "foo" to work on the database should be called "foo-database"
  
### Branch Deletion
A branch should be deleted by its creator after the pull request associated with the branch is approved and merged.  
Note: This is not the responsibility of the approver.
