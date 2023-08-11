#pragma once

namespace ns
{

   struct IFunction
   {
      virtual void receive(int x) = 0;
      virtual void receive(int x, int y) = 0;
      virtual void noVariableNameFunc(int, int z, bool) = 0;
   };

}
