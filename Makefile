documentation_dir := documentation

dependencies_dir  := dependencies/compilation

junit             := $(dependencies_dir)/junit-jupiter-api-5.10.2.jar
apiguardian       := $(dependencies_dir)/apiguardian-api-1.1.2.jar

dependencies      := $(junit):$(apiguardian)

source_dir        := source
target_dir        := target

main_source_dir   := $(source_dir)/main
main_target_dir   := $(target_dir)/main

test_source_dir   := $(source_dir)/test
test_target_dir   := $(target_dir)/test

main_files        := $(shell find $(main_source_dir) -type f -name *.java)
test_files        := $(shell find $(test_source_dir) -type f -name *.java)

compile: clean-target
	# ┌────────────────────────┐
	# │ Compiling main classes │
	# └────────────────────────┘
	javac -cp $(dependencies) -d $(main_target_dir) $(main_files)
	# ┌────────────────────────┐
	# │ Compiling test classes │
	# └────────────────────────┘
	javac -cp $(dependencies):$(main_target_dir) -d $(test_target_dir) $(test_files)
	# ┌───────────────┐
	# │ Running tests │
	# └───────────────┘
	./test.sh
	# ┌─────────────────┐
	# │ Running program │
	# └─────────────────┘
	./run.sh

docs: clean-documentation
	javadoc $(main_source_dir)/*.java -d $(documentation_dir)

clean-target:
	rm -rf $(target_dir)

clean-documentation:
	rm -rf $(documentation_dir)

clean-all: clean-target clean-documentation
