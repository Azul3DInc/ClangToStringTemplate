#pragma once

#include "IParameterType.h"

namespace ns
{

   class ParameterTypeWrapper
   {
      public: 
         ParameterTypeWrapper(IParameterType& paramType):
            paramType(paramType) 
      {
      }
      void refFunc(int x)
      {
         paramType.refFunc(x);
      }
      void constFunc(int x) 
      {
         paramType.constFunc(x);
      }
      void constRefFunc(int x) 
      {
         paramType.constRefFunc(x);
      }
      void vecFunc(std::vector<int> xs)
      {
         paramType.vecFunc(xs);
      }
      void constRefVecFunc(std::vector<std::string> xs)
      {
         paramType.constRefVecFunc(xs);
      }
      void defaultValueFunc()
      {
         paramType.defaultValueFunc();
      }
      void structFunc(AltType alt)
      {
         paramType.structFunc(alt);
      }

      private:
         IParameterType& paramType;
   };

}
