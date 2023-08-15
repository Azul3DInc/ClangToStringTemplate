#include "IFoo.h"
#include "Foo.h"
#include "FooWrapper.h"
#include "MockIFoo.h"
#include "gtest/gtest.h"

TEST(Test_Foo, run)
{
   // SETUP

   ns::MockIFoo foo; 
   ns::FooWrapper wrapper(foo);

   // EXERCISE

   wrapper.run();

   // VERIFY
   
   EXPECT_TRUE(foo.run_isCalled);
   EXPECT_EQ(foo.run_callCount, 1);
}

TEST(Test_Foo, isPositive)
{
   // SETUP

   ns::MockIFoo foo; 
   ns::FooWrapper wrapper(foo);

   // EXERCISE

   wrapper.isPositive();

   // VERIFY
   
   EXPECT_TRUE(foo.isPositive_isCalled);
   EXPECT_EQ(foo.isPositive_callCount, 1);
}

TEST(Test_Foo, getNumber)
{
   // SETUP

   ns::MockIFoo foo; 
   ns::FooWrapper wrapper(foo);

   // EXERCISE

   wrapper.getNumber();

   // VERIFY
   
   EXPECT_TRUE(foo.getNumber_isCalled);
   EXPECT_EQ(foo.getNumber_callCount, 1);
}

TEST(Test_Foo, sendNumber)
{
   // SETUP

   ns::MockIFoo foo; 
   ns::FooWrapper wrapper(foo);

   // EXERCISE

   wrapper.sendNumber(5);

   // VERIFY
   
   EXPECT_TRUE(foo.sendNumber_isCalled);
   EXPECT_EQ(foo.sendNumber_callCount, 1);
   EXPECT_EQ(foo.sendNumber_param_1[0], 5);
}

TEST(Test_Foo, getString)
{
   // SETUP

   ns::MockIFoo foo; 
   ns::FooWrapper wrapper(foo);

   // EXERCISE

   wrapper.getString();

   // VERIFY
   
   EXPECT_TRUE(foo.getString_isCalled);
   EXPECT_EQ(foo.getString_callCount, 1);
}
