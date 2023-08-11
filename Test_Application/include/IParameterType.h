#pragma once

#include <vector>
#include <string>

#include "AltType.h"

namespace ns
{

   struct IParameterType
   {
      virtual void refFunc(int& p) = 0;
      virtual void constFunc(const int p) = 0;
      virtual void constRefFunc(const int& p) = 0;
      virtual void vecFunc(std::vector<int> xs) = 0;
      virtual void constRefVecFunc(const std::vector<std::string>& xs) = 0;
      virtual void defaultValueFunc(int p=50) = 0;
      virtual void structFunc(AltType alt) = 0;
   };

}
