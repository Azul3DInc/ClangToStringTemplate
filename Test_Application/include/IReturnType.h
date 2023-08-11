#pragma once

#include <vector>
#include <string>

#include "AltType.h"

namespace ns 
{

   struct IReturnType 
   {
      virtual float getFloat() = 0;
      virtual long long getLongLong() = 0;
      virtual unsigned long long int getUnsignedLongLongInt() = 0;
      virtual std::vector<int> & getVecRef() = 0;
      virtual int & getLInt() = 0;
      virtual int * getPointerInt() = 0;
      virtual int ** getDoublePointerInt() = 0;
      virtual IReturnType & getSelfRef() = 0;
      virtual AltType getAltType() = 0;
   };

}
