dependencies_dir = dependencies/compilation

junit       = $(dependencies_dir)/junit-jupiter-api-5.10.2.jar
apiguardian = $(dependencies_dir)/apiguardian-api-1.1.2.jar

dependancies = .:$(junit):$(apiguardian)

source_dir = src
target_dir = bin

main_source_dir = $(source_dir)/main
main_target_dir = $(target_dir)/main

test_source_dir = $(source_dir)/test
test_target_dir = $(target_dir)/test

documentation_dir = doc

compile: clean-target
	# Compiling main classes
	javac -cp $(dependancies) $(main_source_dir)/*.java -d $(main_target_dir)
	# Compiling test classes
	javac -cp $(dependancies):$(main_source_dir) $(test_source_dir)/*.java -d $(test_target_dir)
	# Running tests
	./test.sh

docs: clean-documentation
	javadoc $(main_source_dir)/*.java -d $(documentation_dir)

clean-target:
	rm -rf $(target_dir)

clean-documentation:
	rm -rf $(documentation_dir)

clean-all: clean-target clean-documentation
