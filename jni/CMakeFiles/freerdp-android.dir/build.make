# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 2.8

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list

# Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = "/Applications/CMake 2.8-12.app/Contents/bin/cmake"

# The command to remove a file.
RM = "/Applications/CMake 2.8-12.app/Contents/bin/cmake" -E remove -f

# Escaping for special characters.
EQUALS = =

# The program to use to edit the cache.
CMAKE_EDIT_COMMAND = "/Applications/CMake 2.8-12.app/Contents/bin/ccmake"

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/avi/Desktop/help/FreeRDP-master

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/avi/Desktop/help/FreeRDP-master

# Include any dependencies generated for this target.
include client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/depend.make

# Include the progress variables for this target.
include client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/progress.make

# Include the compile flags for this target's objects.
include client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/flags.make

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.o: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/flags.make
client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.o: client/Android/FreeRDPCore/jni/android_event.c
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/avi/Desktop/help/FreeRDP-master/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building C object client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.o"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -o CMakeFiles/freerdp-android.dir/android_event.c.o   -c /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_event.c

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/freerdp-android.dir/android_event.c.i"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -E /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_event.c > CMakeFiles/freerdp-android.dir/android_event.c.i

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/freerdp-android.dir/android_event.c.s"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -S /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_event.c -o CMakeFiles/freerdp-android.dir/android_event.c.s

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.o.requires:
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.o.requires

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.o.provides: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.o.requires
	$(MAKE) -f client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/build.make client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.o.provides.build
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.o.provides

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.o.provides.build: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.o

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.o: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/flags.make
client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.o: client/Android/FreeRDPCore/jni/android_freerdp.c
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/avi/Desktop/help/FreeRDP-master/CMakeFiles $(CMAKE_PROGRESS_2)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building C object client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.o"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -o CMakeFiles/freerdp-android.dir/android_freerdp.c.o   -c /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_freerdp.c

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/freerdp-android.dir/android_freerdp.c.i"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -E /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_freerdp.c > CMakeFiles/freerdp-android.dir/android_freerdp.c.i

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/freerdp-android.dir/android_freerdp.c.s"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -S /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_freerdp.c -o CMakeFiles/freerdp-android.dir/android_freerdp.c.s

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.o.requires:
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.o.requires

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.o.provides: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.o.requires
	$(MAKE) -f client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/build.make client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.o.provides.build
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.o.provides

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.o.provides.build: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.o

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.o: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/flags.make
client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.o: client/Android/FreeRDPCore/jni/android_jni_utils.c
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/avi/Desktop/help/FreeRDP-master/CMakeFiles $(CMAKE_PROGRESS_3)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building C object client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.o"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -o CMakeFiles/freerdp-android.dir/android_jni_utils.c.o   -c /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_jni_utils.c

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/freerdp-android.dir/android_jni_utils.c.i"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -E /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_jni_utils.c > CMakeFiles/freerdp-android.dir/android_jni_utils.c.i

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/freerdp-android.dir/android_jni_utils.c.s"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -S /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_jni_utils.c -o CMakeFiles/freerdp-android.dir/android_jni_utils.c.s

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.o.requires:
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.o.requires

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.o.provides: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.o.requires
	$(MAKE) -f client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/build.make client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.o.provides.build
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.o.provides

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.o.provides.build: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.o

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.o: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/flags.make
client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.o: client/Android/FreeRDPCore/jni/android_jni_callback.c
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/avi/Desktop/help/FreeRDP-master/CMakeFiles $(CMAKE_PROGRESS_4)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building C object client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.o"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -o CMakeFiles/freerdp-android.dir/android_jni_callback.c.o   -c /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_jni_callback.c

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/freerdp-android.dir/android_jni_callback.c.i"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -E /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_jni_callback.c > CMakeFiles/freerdp-android.dir/android_jni_callback.c.i

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/freerdp-android.dir/android_jni_callback.c.s"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -S /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_jni_callback.c -o CMakeFiles/freerdp-android.dir/android_jni_callback.c.s

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.o.requires:
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.o.requires

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.o.provides: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.o.requires
	$(MAKE) -f client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/build.make client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.o.provides.build
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.o.provides

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.o.provides.build: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.o

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/flags.make
client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o: client/Android/FreeRDPCore/jni/generated/android_freerdp_jni.c
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/avi/Desktop/help/FreeRDP-master/CMakeFiles $(CMAKE_PROGRESS_5)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building C object client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -o CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o   -c /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/generated/android_freerdp_jni.c

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.i"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -E /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/generated/android_freerdp_jni.c > CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.i

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.s"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -S /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/generated/android_freerdp_jni.c -o CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.s

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o.requires:
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o.requires

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o.provides: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o.requires
	$(MAKE) -f client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/build.make client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o.provides.build
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o.provides

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o.provides.build: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.o: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/flags.make
client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.o: client/Android/FreeRDPCore/jni/android_cliprdr.c
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/avi/Desktop/help/FreeRDP-master/CMakeFiles $(CMAKE_PROGRESS_6)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building C object client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.o"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -o CMakeFiles/freerdp-android.dir/android_cliprdr.c.o   -c /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_cliprdr.c

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/freerdp-android.dir/android_cliprdr.c.i"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -E /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_cliprdr.c > CMakeFiles/freerdp-android.dir/android_cliprdr.c.i

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/freerdp-android.dir/android_cliprdr.c.s"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && /Users/avi/Desktop/e4dandroid/ndk/toolchains/arm-linux-androideabi-4.8/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-gcc  $(C_DEFINES) $(C_FLAGS) -S /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/android_cliprdr.c -o CMakeFiles/freerdp-android.dir/android_cliprdr.c.s

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.o.requires:
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.o.requires

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.o.provides: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.o.requires
	$(MAKE) -f client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/build.make client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.o.provides.build
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.o.provides

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.o.provides.build: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.o

# Object files for target freerdp-android
freerdp__android_OBJECTS = \
"CMakeFiles/freerdp-android.dir/android_event.c.o" \
"CMakeFiles/freerdp-android.dir/android_freerdp.c.o" \
"CMakeFiles/freerdp-android.dir/android_jni_utils.c.o" \
"CMakeFiles/freerdp-android.dir/android_jni_callback.c.o" \
"CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o" \
"CMakeFiles/freerdp-android.dir/android_cliprdr.c.o"

# External object files for target freerdp-android
freerdp__android_EXTERNAL_OBJECTS =

client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.o
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.o
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.o
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.o
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.o
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/build.make
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-client.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/libwinpr.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/audin-client.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/disp-client.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/echo-client.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/rdpei-client.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/rdpgfx-client.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/cliprdr-client.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/drdynvc-client.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/rail-client.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/rdpdr-client.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/rdpsnd-client.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/drive-client.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/smartcard-client.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/audin-client-opensles.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/rdpsnd-client-opensles.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/armeabi-v7a/libwinpr.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: external/openssl/lib/libssl.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: external/openssl/lib/libcrypto.a
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: /Users/avi/Desktop/e4dandroid/ndk/platforms/android-9/arch-arm/usr/lib/libz.so
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: /Users/avi/Desktop/e4dandroid/ndk/platforms/android-9/arch-arm/usr/lib/libOpenSLES.so
client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking C shared library armeabi-v7a/libfreerdp-android.so"
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/freerdp-android.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/build: client/Android/FreeRDPCore/jni/armeabi-v7a/libfreerdp-android.so
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/build

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/requires: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_event.c.o.requires
client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/requires: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_freerdp.c.o.requires
client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/requires: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_utils.c.o.requires
client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/requires: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_jni_callback.c.o.requires
client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/requires: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/generated/android_freerdp_jni.c.o.requires
client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/requires: client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/android_cliprdr.c.o.requires
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/requires

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/clean:
	cd /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni && $(CMAKE_COMMAND) -P CMakeFiles/freerdp-android.dir/cmake_clean.cmake
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/clean

client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/depend:
	cd /Users/avi/Desktop/help/FreeRDP-master && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/avi/Desktop/help/FreeRDP-master /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni /Users/avi/Desktop/help/FreeRDP-master /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni /Users/avi/Desktop/help/FreeRDP-master/client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : client/Android/FreeRDPCore/jni/CMakeFiles/freerdp-android.dir/depend

