#include "IFunction.h"
#include "Function.h"
#include "FunctionWrapper.h"
#include "MockIFunction.h"
#include "gtest/gtest.h"

TEST(Test_Function, receive_x)
{
   // SETUP

   ns::MockIFunction function; 
   ns::FunctionWrapper wrapper(function);

   // EXERCISE

   wrapper.receive(5);

   // VERIFY
   
   EXPECT_TRUE(function.receive_isCalled);
   EXPECT_EQ(function.receive_callCount, 1);
   EXPECT_EQ(function.receive_param_1[0], 5);
}

TEST(Test_Function, receive_xy)
{
   // SETUP

   ns::MockIFunction function; 
   ns::FunctionWrapper wrapper(function);

   // EXERCISE

   wrapper.receive(6, 7);

   // VERIFY
   
   EXPECT_FALSE(function.receive_isCalled);
   EXPECT_TRUE(function.receive1_isCalled);
   EXPECT_EQ(function.receive1_callCount, 1);
   EXPECT_EQ(function.receive1_param_1[0], 6);
   EXPECT_EQ(function.receive1_param_2[0], 7);
}

TEST(Test_Function, noVariableNameFunc)
{
   // SETUP

   ns::MockIFunction function; 
   ns::FunctionWrapper wrapper(function);

   // EXERCISE

   wrapper.noVariableNameFunc(1, 2, true);

   // VERIFY
   
   EXPECT_TRUE(function.noVariableNameFunc_isCalled);
   EXPECT_EQ(function.noVariableNameFunc_callCount, 1);
   EXPECT_EQ(function.noVariableNameFunc_param_1[0], 1);
   EXPECT_EQ(function.noVariableNameFunc_param_2[0], 2);
   EXPECT_EQ(function.noVariableNameFunc_param_3[0], true);
}
