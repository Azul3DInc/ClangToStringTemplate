#pragma once

#include "IFoo.h"

namespace ns
{

   class FooWrapper
   {
      public:
         FooWrapper(IFoo& foo):
            foo(foo)
      {
      }
      void run()
      {
         foo.run();
      }
      bool isPositive()
      {
         return foo.isPositive();
      }
      int getNumber()
      {
         return foo.getNumber();
      }
      void sendNumber(int num)
      {
         foo.sendNumber(num);
      }
      std::string getString()
      {
         return foo.getString();
      }

      private:
         IFoo& foo;
   };

}
