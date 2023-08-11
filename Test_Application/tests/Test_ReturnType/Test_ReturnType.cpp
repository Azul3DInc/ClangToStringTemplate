#include "IReturnType.h"
#include "ReturnType.h"
#include "ReturnTypeWrapper.h"
#include "MockIReturnType.h"
#include "gtest/gtest.h"

TEST(Test_ReturnType, getFloat)
{
   // SETUP

   ns::MockIReturnType retType;
   ns::ReturnTypeWrapper wrapper(retType);

   // EXERCISE
   
   float val = wrapper.getFloat();

   // VERIFY
   
   EXPECT_TRUE(retType.getFloat_isCalled);
   EXPECT_EQ(retType.getFloat_callCount, 1);
}

TEST(Test_ReturnType, getLongLong)
{
   // SETUP

   ns::MockIReturnType retType;
   ns::ReturnTypeWrapper wrapper(retType);

   // EXERCISE
   
   long long val = wrapper.getLongLong();

   // VERIFY
   
   EXPECT_TRUE(retType.getLongLong_isCalled);
   EXPECT_EQ(retType.getLongLong_callCount, 1);
}

TEST(Test_ReturnType, getUnsignedLongLongInt)
{
   // SETUP

   ns::MockIReturnType retType;
   ns::ReturnTypeWrapper wrapper(retType);

   // EXERCISE
   
   unsigned long long int val = wrapper.getUnsignedLongLongInt();

   // VERIFY
   
   EXPECT_TRUE(retType.getUnsignedLongLongInt_isCalled);
   EXPECT_EQ(retType.getUnsignedLongLongInt_callCount, 1);
}

TEST(Test_ReturnType, getVecRef)
{
   // SETUP

   ns::MockIReturnType retType;
   ns::ReturnTypeWrapper wrapper(retType);

   // EXERCISE
   
   wrapper.getVecRef();

   // VERIFY
   
   EXPECT_TRUE(retType.getVecRef_isCalled);
   EXPECT_EQ(retType.getVecRef_callCount, 1);
}

TEST(Test_ReturnType, getLInt)
{
   // SETUP

   ns::MockIReturnType retType;
   ns::ReturnTypeWrapper wrapper(retType);

   // EXERCISE
   
   wrapper.getLInt();

   // VERIFY
   
   EXPECT_TRUE(retType.getLInt_isCalled);
   EXPECT_EQ(retType.getLInt_callCount, 1);
}

TEST(Test_ReturnType, getPointerInt)
{
   // SETUP

   ns::MockIReturnType retType;
   ns::ReturnTypeWrapper wrapper(retType);

   // EXERCISE
   
   int * val = wrapper.getPointerInt();

   // VERIFY
   
   EXPECT_TRUE(retType.getPointerInt_isCalled);
   EXPECT_EQ(retType.getPointerInt_callCount, 1);
}

TEST(Test_ReturnType, getDoublePointerInt)
{
   // SETUP

   ns::MockIReturnType retType;
   ns::ReturnTypeWrapper wrapper(retType);

   // EXERCISE
   
   int ** val = wrapper.getDoublePointerInt();

   // VERIFY
   
   EXPECT_TRUE(retType.getDoublePointerInt_isCalled);
   EXPECT_EQ(retType.getDoublePointerInt_callCount, 1);
}

TEST(Test_ReturnType, getSelfRef)
{
   // SETUP

   ns::MockIReturnType retType;
   ns::ReturnTypeWrapper wrapper(retType);

   // EXERCISE
   
   ns::IReturnType & self = wrapper.getSelfRef();

   // VERIFY
   
   EXPECT_TRUE(retType.getSelfRef_isCalled);
   EXPECT_EQ(retType.getSelfRef_callCount, 1);
}
TEST(Test_ReturnType, getAltType)
{
   // SETUP

   ns::MockIReturnType retType;
   ns::ReturnTypeWrapper wrapper(retType);

   // EXERCISE
   
   ns::AltType alt = wrapper.getAltType();

   // VERIFY
   
   EXPECT_TRUE(retType.getAltType_isCalled);
   EXPECT_EQ(retType.getAltType_callCount, 1);
}
