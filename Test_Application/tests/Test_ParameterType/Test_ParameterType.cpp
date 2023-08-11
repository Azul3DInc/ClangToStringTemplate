#include "IParameterType.h"
#include "ParameterType.h"
#include "ParameterTypeWrapper.h"
#include "MockIParameterType.h"
#include "gtest/gtest.h"

TEST(Test_ParameterType, refFunc)
{
   // SETUP

   ns::MockIParameterType paramType; 
   ns::ParameterTypeWrapper wrapper(paramType);

   // EXERCISE

   wrapper.refFunc(1);

   // VERIFY
   
   EXPECT_TRUE(paramType.refFunc_isCalled);
   EXPECT_EQ(paramType.refFunc_callCount, 1);
   EXPECT_EQ(paramType.refFunc_param_1[0], 1);
}


TEST(Test_ParameterType, constFunc)
{
   // SETUP

   ns::MockIParameterType paramType; 
   ns::ParameterTypeWrapper wrapper(paramType);

   // EXERCISE

   wrapper.constFunc(2);

   // VERIFY
   
   EXPECT_TRUE(paramType.constFunc_isCalled);
   EXPECT_EQ(paramType.constFunc_callCount, 1);
   EXPECT_EQ(paramType.constFunc_param_1[0], 2);
}


TEST(Test_ParameterType, constRefFunc)
{
   // SETUP

   ns::MockIParameterType paramType; 
   ns::ParameterTypeWrapper wrapper(paramType);

   // EXERCISE

   wrapper.constRefFunc(3);

   // VERIFY
   
   EXPECT_TRUE(paramType.constRefFunc_isCalled);
   EXPECT_EQ(paramType.constRefFunc_callCount, 1);
   EXPECT_EQ(paramType.constRefFunc_param_1[0], 3);
}

TEST(Test_ParameterType, vecFunc)
{
   // SETUP

   ns::MockIParameterType paramType; 
   ns::ParameterTypeWrapper wrapper(paramType);

   // EXERCISE

   std::vector<int> xs;
   xs.push_back(1);
   xs.push_back(2);
   xs.push_back(3);
   wrapper.vecFunc(xs);

   // VERIFY
   
   EXPECT_TRUE(paramType.vecFunc_isCalled);
   EXPECT_EQ(paramType.vecFunc_callCount, 1);
   EXPECT_EQ(paramType.vecFunc_param_1[0].size(), 3);
   EXPECT_EQ(paramType.vecFunc_param_1[0][1], 2);
}

TEST(Test_ParameterType, constRefVecFunc)
{
   // SETUP

   ns::MockIParameterType paramType; 
   ns::ParameterTypeWrapper wrapper(paramType);

   // EXERCISE

   std::vector<std::string> xs;
   xs.push_back("Hello World!");
   wrapper.constRefVecFunc(xs);

   // VERIFY
   
   EXPECT_TRUE(paramType.constRefVecFunc_isCalled);
   EXPECT_EQ(paramType.constRefVecFunc_callCount, 1);
   EXPECT_EQ(paramType.constRefVecFunc_param_1[0][0], "Hello World!");
}

TEST(Test_ParameterType, defaultValueFunc)
{
   // SETUP

   ns::MockIParameterType paramType; 
   ns::ParameterTypeWrapper wrapper(paramType);

   // EXERCISE

   wrapper.defaultValueFunc();
   wrapper.defaultValueFunc();

   // VERIFY
   
   EXPECT_TRUE(paramType.defaultValueFunc_isCalled);
   EXPECT_EQ(paramType.defaultValueFunc_callCount, 2);
   EXPECT_EQ(paramType.defaultValueFunc_param_1[0], 50);
   EXPECT_EQ(paramType.defaultValueFunc_param_1[1], 50);
}
TEST(Test_ParameterType, structFunc)
{
   // SETUP

   ns::MockIParameterType paramType; 
   ns::ParameterTypeWrapper wrapper(paramType);

   // EXERCISE

   
   wrapper.structFunc(ns::AltType());

   // VERIFY
   
   EXPECT_TRUE(paramType.structFunc_isCalled);
   EXPECT_EQ(paramType.structFunc_callCount, 1);
   EXPECT_EQ(paramType.structFunc_param_1[0].x, 1);
}
