binary_directory = "bin"
documentation_directory = "documentation"

compile: clean
	javac src/*.java -d $(binary_directory)

doc:
	javadoc src/*.java -d $(documentation_directory)

clean:
	rm -rf $(binary_directory) $(documentation_directory)
