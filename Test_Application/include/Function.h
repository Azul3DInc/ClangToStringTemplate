#pragma once

#include "IFunction.h"

namespace ns
{

   class Function : public IFunction
   {
      public:
         void receive(int x) override 
         {
            return;
         }
         void receive(int x, int y) override 
         {
            return;
         }
         void noVariableNameFunc(int, int z, bool) override
         {
            return;
         }
   };

}
