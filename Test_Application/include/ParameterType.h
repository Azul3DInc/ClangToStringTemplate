#pragma once

#include "IParameterType.h"

namespace ns
{

   class ParameterType : public IParameterType
   {
      public:
         void refFunc(int& p) override
         {
            return;
         }
         void constFunc(const int p) override
         {
            return;
         }
         void constRefFunc(const int& p) override
         {
            return;
         }
         void vecFunc(std::vector<int> xs) override
         {
            return;
         }
         void constRefVecFunc(const std::vector<std::string>& xs) override
         {
            return;
         }
         void defaultValueFunc(int p=50) override
         {
            return;
         }
         void structFunc(AltType alt) override
         {
            return;
         }
   };

}
