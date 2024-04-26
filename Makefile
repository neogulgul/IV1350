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

# foreground colors
ANSI_FG_RED       := "31"
ANSI_FG_GREEN     := "32"
ANSI_FG_YELLOW    := "33"
ANSI_FG_BLUE      := "34"
ANSI_FG_MAGENTA   := "35"
ANSI_FG_CYAN      := "36"

compile: clean-target
	@./colored_box_prompt.sh "Compiling main classes" $(ANSI_FG_YELLOW)
	javac -cp $(dependencies) -d $(main_target_dir) $(main_files)

	@./colored_box_prompt.sh "Compiling test classes" $(ANSI_FG_YELLOW)
	javac -cp $(dependencies):$(main_target_dir) -d $(test_target_dir) $(test_files)

	@./colored_box_prompt.sh "Running tests" $(ANSI_FG_MAGENTA)
	./test.sh

	@./colored_box_prompt.sh "Running program" $(ANSI_FG_GREEN)
	./run.sh

docs: clean-documentation
	javadoc $(main_source_dir)/*.java -d $(documentation_dir)

clean-target:
	@./colored_box_prompt.sh "Removing target directory" $(ANSI_FG_RED)
	rm -rf $(target_dir)

clean-documentation:
	@./colored_box_prompt.sh "Removing documentation directory" $(ANSI_FG_RED)
	rm -rf $(documentation_dir)

clean-all: clean-target clean-documentation
