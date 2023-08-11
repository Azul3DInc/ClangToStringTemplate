#pragma once

#include "IFoo.h"

namespace ns
{

   class Foo : public IFoo
   {
      public:
         void run() override
         {
            return;
         }
         int getNumber() override
         {
            return 1;
         }
         bool isPositive() override
         {
            return true;
         }
         void sendNumber(int num) override
         {
            return;
         }
         std::string getString() override
         {
            return "Foo";
         }

   };

}
