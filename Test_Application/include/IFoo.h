#pragma once

#include <string>

namespace ns
{

   struct IFoo
   {
      virtual void run() = 0;
      virtual bool isPositive() = 0;
      virtual int getNumber() = 0;
      virtual void sendNumber(int num) = 0;
      virtual std::string getString() = 0;
   };

}
