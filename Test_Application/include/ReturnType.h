#pragma once

#include "IReturnType.h"

namespace ns
{

   class ReturnType: public IReturnType
   {
      float getFloat() override
      {
         return 0.0;
      }
      long long getLongLong() override
      {
         return 0LL;
      }
      unsigned long long int getUnsignedLongLongInt() override
      {
         return 0ULL;
      }
      std::vector<int> & getVecRef() override
      {
         std::vector<int> xs;
         return xs;
      }
      int & getLInt() override
      {
         int x = 6;
         return x;
      }
      int * getPointerInt() override
      {
         int y = 5;
         int *x = &y;
         return x;
      }
      int ** getDoublePointerInt() override
      {
         int z = 5;
         int * y = &z;
         int ** x = &y;
         return x;
      }
      IReturnType & getSelfRef() override
      {
         return *this;
      }
      AltType getAltType() override
      {
         return AltType();
      }
   };

}
