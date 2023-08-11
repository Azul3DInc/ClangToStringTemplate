#pragma once

#include "../../include/Bar/Bar.h"  

namespace fs
{

   class IFoo
   {
      virtual Bar& returnBar(Bar &bar) = 0;
   };

}

