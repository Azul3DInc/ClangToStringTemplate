#pragma once

#include "IFunction.h"

namespace ns
{

   class FunctionWrapper
   {
      public:
         FunctionWrapper(IFunction& function):
            function(function)
         {
         }
         void receive(int x) 
         {
            return function.receive(x);
         }
         void receive(int x, int y) 
         {
            return function.receive(x, y);
         }
         void noVariableNameFunc(int a, int z, bool b)
         {
            return function.noVariableNameFunc(a, z, b);
         }

      private:
         IFunction& function;
   };

}
