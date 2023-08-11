#pragma once

#include "IReturnType.h"

namespace ns
{
   class ReturnTypeWrapper
   {
      public: 
         ReturnTypeWrapper(IReturnType& retType):
            retType(retType)
      {
      }
      float getFloat()
      {
         return retType.getFloat();
      }
      long long getLongLong()
      {
         return retType.getLongLong();
      }
      unsigned long long int getUnsignedLongLongInt()
      {
         return retType.getUnsignedLongLongInt();
      }
      std::vector<int> & getVecRef()
      {
         return retType.getVecRef();
      }
      int & getLInt()
      {
         return retType.getLInt();
      }
      int * getPointerInt()
      {
         return retType.getPointerInt();
      }
      int ** getDoublePointerInt()
      {
         return retType.getDoublePointerInt();
      }
      IReturnType & getSelfRef()
      {
         return retType.getSelfRef();
      }
      AltType getAltType()
      {
         return retType.getAltType();
      }

      private:
         IReturnType& retType;

   };

}
