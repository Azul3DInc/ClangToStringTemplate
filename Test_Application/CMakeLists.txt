##########################################################################################
# Project Configuration
##########################################################################################

cmake_minimum_required(VERSION 3.15 FATAL_ERROR)
project(Application)

##########################################################################################
# Global CMake Settings
##########################################################################################

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_INCLUDE_CURRENT_DIR ON)
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

##########################################################################################
# Include Directories
##########################################################################################

include_directories(${CMAKE_CURRENT_SOURCE_DIR}/include/Mocks)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/include)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/src)

##########################################################################################
# Application Target Linking and Dependencies
##########################################################################################

set(SOURCES
   src/main.cpp
   src/Foo.cpp
)

add_executable(Application ${SOURCES})

target_include_directories(Application PRIVATE ${CMAKE_CURRENT_SOURCE_DIR}/include)

##########################################################################################
# GTest Configuration
##########################################################################################

include(FetchContent)
FetchContent_Declare(
  googletest
  URL https://github.com/google/googletest/archive/03597a01ee50ed33e9dfd640b249b4be3799d395.zip
)

# For Windows: Prevent overriding the parent project's compiler/linker settings
set(gtest_force_shared_crt ON CACHE BOOL "" FORCE)
FetchContent_MakeAvailable(googletest)

include(GoogleTest)

##########################################################################################
# Testing Target Linking and Dependencies
##########################################################################################

enable_testing()

file(GLOB TEST_SOURCES "${CMAKE_CURRENT_SOURCE_DIR}/tests/*/*.cpp")

message("messages: ${TEST_SOURCES}")

foreach(TEST_SOURCE ${TEST_SOURCES})
   get_filename_component(TEST_NAME ${TEST_SOURCE} NAME_WE)

   add_executable(${TEST_NAME} ${TEST_SOURCE})
   target_include_directories(${TEST_NAME} PRIVATE ${CMAKE_CURRENT_SOURCE_DIR}/include)

   target_link_libraries(
      ${TEST_NAME}
      PRIVATE
         GTest::gtest_main
         pthread
   )

   gtest_discover_tests(${TEST_NAME})
endforeach()
